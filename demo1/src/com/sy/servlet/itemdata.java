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

public class itemdata extends HttpServlet {

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
		String value3=request.getParameter("value3");
		String value4=request.getParameter("value4");
		List item=new ArrayList();
		String sql="SELECT tot_plan_ad.Vehicle,tot_plan_ad.`Week`,tot_plan_ad.Item,tot_plan_ad.`Value` FROM tot_plan_ad WHERE Vehicle='"+value1+"' AND Item='"+value2+"'AND `Week` BETWEEN '"+value3+"' AND '"+value4+"' ;";
		Connection conn=db.getConn();
		ResultSet rs=null;
		if(value3==null){
			sql="SELECT tot_plan_ad.Vehicle,tot_plan_ad.Item,sum(tot_plan_ad.`Value`) FROM tot_plan_ad WHERE Vehicle='"+value1+"' AND Item='"+value2+"' ;";
		    rs=db.executeQuery(conn, sql);
		    try {
				while(rs.next()){
					Map tmp =new HashMap();
					tmp.put("vehicle", rs.getString(1));
					tmp.put("item", rs.getString(2));
					tmp.put("value", rs.getString(3));
					item.add(tmp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				Map tmp =new HashMap();
				tmp.put("vehicle", rs.getString(1));
				tmp.put("week", rs.getString(2));
				tmp.put("item", rs.getString(3));
				tmp.put("value", rs.getString(4));
				item.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		out.print(JSON.toJSONString(item));
		out.flush();
		out.close();
	}

}
