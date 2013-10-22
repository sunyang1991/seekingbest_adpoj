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

public class middata extends HttpServlet {

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
		Map data=new HashMap();
		List printlist=new ArrayList();
		List broadlist=new ArrayList();
		String sql="SELECT objlist.val1,objlist.val2,objlist.val3 FROM `objlist` ";
		Connection conn =db.getConn();
		ResultSet rs=db.executeQuery(conn, sql);
		try {
			rs.next();
			data.put("totalcost", rs.getDouble(1));
			data.put("print",  rs.getDouble(3));
			data.put("broad",  rs.getDouble(2));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="SELECT Vehicle,SUM(Value) FROM tot_plan_ad WHERE Vehicle IN(SELECT `Value` FROM sets WHERE type='broad')GROUP BY Vehicle ;";
		rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				Map tmp=new HashMap();
				tmp.put("vehicle", rs.getString(1));
				tmp.put("value", rs.getDouble(2));
				broadlist.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="SELECT Vehicle,SUM(Value) FROM tot_plan_ad WHERE Vehicle IN(SELECT `Value` FROM sets WHERE type='print')GROUP BY Vehicle ;";
		rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				Map tmp=new HashMap();
				tmp.put("vehicle", rs.getString(1));
				tmp.put("value", rs.getDouble(2));
				printlist.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		data.put("broadlist", broadlist);
		data.put("printlist", printlist);
		try {
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print(JSON.toJSONString(data));
		out.flush();
		out.close();
	}

}
