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
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sy.util.db;

public class result5 extends HttpServlet {

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
	Map opt=new HashMap();
	Map plan=new HashMap();
	DecimalFormat df=new DecimalFormat("0.##");
	Connection conn=db.getConn();
	String	sql="SELECT plan_cost.`week` ,sum(plan_cost.`value`) FROM plan_cost GROUP BY `week`;";
	ResultSet rs=db.executeQuery(conn, sql);
	try {
		while(rs.next()){
			plan.put(rs.getString(1), Double.valueOf(df.format(rs.getDouble(2)/1000000)));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	Map data=new HashMap();
	List value=new ArrayList();
	List week=new ArrayList();
	Map planmap=new HashMap();
	List planlist=new ArrayList();
	Set<String> keys=plan.keySet();
	for(String key:keys){
		week.add(key);
		planlist.add(plan.get(key));
	}
	planmap.put("name", "Plan Ad Cost");
	planmap.put("data",planlist);
	value.add(planmap);
	data.put("value", value);
	data.put("week", week);
	out.print(JSON.toJSONString(data));
	out.flush();
	out.close();
}

}
