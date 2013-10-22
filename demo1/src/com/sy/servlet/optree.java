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

public class optree extends HttpServlet {

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
	    Connection conn=db.getConn();
	    String sql="select objname from objlist ;";
	    ResultSet rs=db.executeQuery(conn, sql);
	    List toplist=new ArrayList();
	    Map objmap=new HashMap();
		Map addini=new HashMap();//添加配置选项
		Map opting=new HashMap();//正在配置
		Map opted =new HashMap();//优化完毕项
		List objchil=new ArrayList();
	    try {
			while(rs.next()){//添加配置信息分别为，已存在项目，添加项目配置，正在填写配置，已优化配置。
			
			/******************添加配置设置*******************************/
			addini.put("text", "Adding Configuration");
			Map iniattrb =new HashMap();
			iniattrb.put("url", "jsp/optimaset.jsp");
			iniattrb.put("action", "add");
			addini.put("attributes", iniattrb);
			addini.put("iconCls", "icon-add");
			objchil.add(addini);
			/******************正在配置设置*******************************/
			Map attrb1 =new HashMap();
			attrb1.put("action", "none");
			opting.put("text", "configuring");
			opting.put("attributes",attrb1 );
			List ingchil =new ArrayList();
			sql="SELECT runname,runid from runlist WHERE opt=0 and objid=(SELECT objid FROM objlist where objname='"+rs.getString(1)+"'); ";
			ResultSet run=db.executeQuery(conn, sql);
			while(run.next()){
					Map tmp =new HashMap();
					Map attrb=new HashMap();
					attrb.put("action", "config");
					attrb.put("url", "jsp/optimaset.jsp");
					attrb.put("runid", run.getString(2));
					tmp.put("text", run.getString("runname"));
					tmp.put("attributes",attrb);
					ingchil.add(tmp);
			}
			opting.put("children", ingchil);
			opting.put("state", "closed");
			objchil.add(opting);
			/******************展示优化结果*******************************/
			List edchil =new ArrayList();
			sql="SELECT runname,runid from runlist WHERE opt=1 and objid=(SELECT objid FROM objlist where objname='"+rs.getString(1)+"'); ";
			run=db.executeQuery(conn, sql);
			while(run.next()){
					Map tmp =new HashMap();
					Map attrb=new HashMap();
					attrb.put("action", "showresult");
					attrb.put("url", "jsp/showresult.jsp");
					attrb.put("runid", run.getString(2));
					tmp.put("text", run.getString("runname"));
					tmp.put("attributes",attrb);
					edchil.add(tmp);
			}
			Map attrb2 =new HashMap();
			attrb2.put("action", "none");
			opted.put("text", "show result");
			opted.put("state", "closed");
			opted.put("iconCls", "tree-folder");
			opted.put("children", edchil);
			opted.put("attributes", attrb2);
			Map attrb3 =new HashMap();
			attrb3.put("action", "none");
			objchil.add(opted);
				objmap.put("text", rs.getString(1));
				objmap.put("children", objchil);
				objmap.put("state", "closed");
				objmap.put("attributes", attrb3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    toplist.add(objmap);
	    out.print(JSON.toJSONString(toplist));
		out.flush();
		out.close();
	}

}
