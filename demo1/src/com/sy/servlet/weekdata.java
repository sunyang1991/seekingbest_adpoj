package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sy.util.db;

public class weekdata extends HttpServlet {

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
		String value1=request.getParameter("value1");
		String value2=request.getParameter("value2");
		Connection conn=db.getConn();
		List week=new ArrayList();
		String sql="SELECT tot_plan_ad.Vehicle,tot_plan_ad.`Week`,SUM(tot_plan_ad.`Value`) FROM tot_plan_ad WHERE Vehicle='"+value1+"' AND `Week`='"+value2+"' ;";
		if(value1.equals("all")){
			sql="SELECT tot_plan_ad.`Week`,SUM(tot_plan_ad.`Value`) FROM tot_plan_ad WHERE `Week`='"+value2+"' ;";
			ResultSet rs=db.executeQuery(conn, sql);
			try {
				while(rs.next()){
					Map tmp =new HashMap();
					tmp.put("vehicle", "All");
					tmp.put("week", rs.getString(1));
					tmp.put("value", rs.getString(2));
					week.add(tmp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			ResultSet rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				Map tmp =new HashMap();
				tmp.put("vehicle", rs.getString(1));
				tmp.put("week", rs.getString(2));
				tmp.put("value", rs.getString(3));
				week.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		out.print(JSON.toJSONString(week));
		out.flush();
		out.close();
	}
}
