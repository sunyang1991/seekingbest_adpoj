package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.util.db;

public class optdata extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String runname=request.getParameter("name");
		String sql="select runid from runlist where runname='"+runname+"'";
		Connection conn=db.getConn();
		ResultSet rs=db.executeQuery(conn, sql);
		try {
			rs.next();
			sql="UPDATE coflist  SET `value`= '?'  WHERE runid='"+rs.getString(1).toString()+"' AND cofname='?';";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		PreparedStatement pstm=db.getPStmt(conn, sql);
		Map<String,String[]> value=request.getParameterMap();
		value.remove("name");
		value.remove("tot-ad-cost");
		value.remove("total-plan-print");
		value.remove("total-plan-WEEKEND");
		value.remove("total-plan-VERTICAL");
		value.remove("total-plan-ROP");
		value.remove("total-plan-CORE");
		value.remove("total-plan-broad ");
		value.remove("total-plan-PSTV");
		value.remove("total-plan-PSRADIO");
		value.remove("total-plan-PBTV");
		value.remove("total-plan-PBRADIO");
		value.remove("total-plan-BSTV");
		Set<String> keys=value.keySet();
		for(String key:keys){
			try {
				pstm.setDouble(1, Double.valueOf( value.get(key)[0]));
				pstm.setString(2, key);
				pstm.addBatch();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		try {
			pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print("success!");
		out.flush();
		out.close();
	}

}
