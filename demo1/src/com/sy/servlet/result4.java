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

public class result4 extends HttpServlet {

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
	String vehicle=request.getParameter("vehicle");
	Map opt=new HashMap();
	DecimalFormat df=new DecimalFormat("0.##");
	Map data=new HashMap();
	List week=new ArrayList<String>();
	List plan=new ArrayList<String>();
	List optimal=new ArrayList<String>();
	Connection conn=db.getConn();
	String sql="SELECT opt_cost.`week` FROM opt_cost GROUP BY  `week`";
	ResultSet rs=db.executeQuery(conn, sql);
	try {
		while(rs.next()){
			week.add(rs.getString(1));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		sql="SELECT opt_cost.`week`,sum(opt_cost.`value`) FROM opt_cost where vehicle='"+vehicle+"' GROUP BY  `week`";
		rs=db.executeQuery(conn, sql);
		try {
			while(rs.next()){
				optimal.add(Double.valueOf(df.format(rs.getDouble(2)/1000000)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql="SELECT plan_cost.`week`,sum(plan_cost.`value`) FROM plan_cost where vehicle='"+vehicle+"' GROUP BY  `week`";
		rs=db.executeQuery(conn, sql);
		try {
			List tmp=new ArrayList();
			while(rs.next()){
				tmp.add(rs.getString(1));
			}
			rs.first();
			for(int i=0;i<week.size();i++){
			if(tmp.contains(week.get(i).toString())){
			plan.add(Double.valueOf(df.format(rs.getDouble(2)/1000000)));
			rs.next();
			}else{
				plan.add(Double.valueOf(0));
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		data.put("opt", optimal);
	data.put("plan", plan);
	data.put("week", week);
	out.print(JSON.toJSONString(data));
	out.flush();
	out.close();
}
}
