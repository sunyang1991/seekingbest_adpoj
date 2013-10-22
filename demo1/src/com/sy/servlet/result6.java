package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

public class result6 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	DecimalFormat df=new DecimalFormat("0.##");
	Map data=new HashMap();
	List value=new ArrayList();
	List<String> week=new ArrayList<String>();
	List<String> vehicle=new ArrayList<String>();
	Connection conn=db.getConn();
	String sql="SELECT plan_cost.`week` FROM plan_cost GROUP BY  `week`";
	ResultSet rs=db.executeQuery(conn, sql);
	try {
		while(rs.next()){
			week.add(rs.getString(1));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	sql="SELECT plan_cost.vehicle  FROM plan_cost GROUP BY  vehicle";
	rs=db.executeQuery(conn, sql);
	try {
		while(rs.next()){
			vehicle.add(rs.getString(1));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	for(String key:vehicle){
		Map temp=new HashMap();
		temp.put("name", key);
		List tmp=new ArrayList();
		sql="SELECT plan_cost.`week`,sum(plan_cost.`value`) FROM plan_cost where vehicle='"+key+"' GROUP BY  `week`;";
		rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				tmp.add(Double.valueOf(df.format(rs.getDouble(2)/1000000)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		temp.put("data", tmp);
		value.add(temp);
	}
	data.put("value", value);
	data.put("week", week);
	out.print(JSON.toJSONString(data));
	out.flush();
	out.close();
}
}
