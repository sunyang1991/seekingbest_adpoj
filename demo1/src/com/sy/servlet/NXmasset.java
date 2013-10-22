package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sy.util.db;

public class NXmasset extends HttpServlet {

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
		List week=new ArrayList();
		String sql="SELECT `Week` FROM `tot_plan_ad` WHERE Vehicle='"+value1+"' AND `Week` IN(SELECT `Value` from sets WHERE type='NXmas') GROUP BY `Week`;";
		if(value1.equals("all")){
			sql="SELECT `Value` from sets WHERE type='NXmas'; ";
		}
		Connection conn=db.getConn();
		ResultSet rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				week.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print(JSON.toJSONString(week));
		out.flush();
		out.close();
	}


}
