package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sy.util.db;

public class result1 extends HttpServlet {


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
		String runname=request.getParameter("runname");
		Map<String,Double> plan =new HashMap<String,Double>();
		Map<String,Double> opt  =new HashMap<String,Double>();
		Connection conn=db.getConn();
		String sql="SELECT sets.`value` FROM sets where sets.type=\"vehicle\";";
		ResultSet rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				plan.put(rs.getString(1), (double) 0);
				opt.put(rs.getString(1), (double) 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="SELECT plan_cost.vehicle ,SUM(plan_cost.`value`) FROM plan_cost GROUP BY plan_cost.vehicle;";
		rs=db.executeQuery(conn, sql);
		double countplan=0;
		try {
			while(rs.next()){
				countplan+=rs.getDouble(2);
				plan.put(rs.getString(1), rs.getDouble(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="SELECT opt_cost.vehicle ,SUM(opt_cost.`value`)  FROM opt_cost GROUP BY opt_cost.vehicle;";
		rs=db.executeQuery(conn, sql);
		double countopt=0;
		try {
			while(rs.next()){
				countopt+=rs.getDouble(2);
				opt.put(rs.getString(1), rs.getDouble(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map data = new HashMap();
		List rows=new ArrayList();
		data.put("totle", plan.size());
		Set<String> keys=plan.keySet();
		DecimalFormat df=new DecimalFormat("0.##");
		for(String key:keys){
			Map tmp = new HashMap();
			tmp.put("Vehicle", key);
			tmp.put("Plan", df.format(plan.get(key)/1000000)+"$");
			tmp.put("Optimal", df.format(opt.get(key)/1000000)+"$");
			tmp.put("Change", df.format((opt.get(key)-plan.get(key))/1000000)+"$");
			tmp.put("per", plan.get(key)-opt.get(key)==0?"NA":df.format((opt.get(key)-plan.get(key))/plan.get(key)*100)+"%");
			rows.add(tmp);
		}
		List  footer=new ArrayList();
		Map tmp = new HashMap();
		tmp.put("Vehicle", "Total");
		tmp.put("Plan", df.format(countplan/1000000)+"$");
		tmp.put("Optimal", df.format(countopt/1000000)+"$");
		tmp.put("Change", df.format((countopt-countplan)/1000000)+"$");
		tmp.put("per", countplan-countopt==0?"NA":df.format((countopt-countplan)/countplan*100)+"%");
		footer.add(tmp);
		data.put("rows", rows);
		data.put("footer", footer);
		db.closeResultSet(rs);
		db.closeConn(conn);
		out.print(JSON.toJSONString(data));
		out.flush();
		out.close();
	}

}
