package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sy.util.db;

public class addnewcfg extends HttpServlet {

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
	    Connection conn =db.getConn();
	    String sql="select runname from runlist;";
		ResultSet rs = db.executeQuery(conn, sql);
		List str=new ArrayList();
		try {
			while(rs.next()){
				str.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map data=new HashMap();
		String runid=UUID.randomUUID().toString().replace("-", "");
		if(str.contains(runname)){
			data.put("msg", "same");
			out.print(JSON.toJSONString(data));
		}else{
		 sql="INSERT INTO runlist(runid,runname,objid) VALUES('"+runid+"','"+runname+"'," +
				"'AFE765090604F0EE585302B699985E72');";
		 try {
			if(!conn.createStatement().execute(sql)){
				 data.put("msg", "success");
				 out.print(JSON.toJSONString(data));
			 }else{
				 data.put("msg", "error");
				 out.print(JSON.toJSONString(data));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		sql="INSERT INTO  coflist(runid,cofname) VALUES(?,?);";
		PreparedStatement pstm=db.getPStmt(conn, sql);
		try {
			pstm.setString(1, runid);
			pstm.setString(2,"max-tot-ad-cost");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-print-ad");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-print-ad");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-WEEKEND");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-WEEKEND");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-VERTICAL");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-VERTICAL");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-ROP");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-ROP");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-CORE");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-CORE");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-broad-ad");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-broad-ad");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-PSTV");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-PSTV");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-PSRADIO");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-PSRADIO");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-PBTV");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-PBTV");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-PBRADIO");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-PBRADIO");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"low-ad-BSTV");
			pstm.addBatch();
			pstm.setString(1, runid);
			pstm.setString(2,"max-ad-BSTV");
			pstm.addBatch();
			pstm.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}

}
