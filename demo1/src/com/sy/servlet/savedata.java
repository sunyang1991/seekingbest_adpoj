package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.util.db;

public class savedata extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String runname=request.getParameter("runname");
		String type=request.getParameter("type");
		String vehicle=request.getParameter("vehicle");
		String week=request.getParameter("week");
		String item=request.getParameter("item");
		double value=Double.valueOf(request.getParameter("value"));
		String runid=null;
		Connection conn=db.getConn();
		ResultSet rs=null;
		String sql=null;
		/***********获取runid********************/
		sql="select runid from runlist where runname='"+runname+"';";
		rs=db.executeQuery(conn, sql);
		try {
			rs.next();
			runid=rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}

}
