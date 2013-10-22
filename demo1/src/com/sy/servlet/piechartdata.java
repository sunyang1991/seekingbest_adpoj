package com.sy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.charts.linechart;
import com.sy.charts.piechart;
import com.sy.dao.linechartdao;
import com.sy.dao.piechartdao;

public class piechartdata extends HttpServlet {

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
		/*获取前台发送的表名与拼接的sql语句
		 * 调用数据获取函数获取数据并图表需要的数据返回*/
		String tabname =request.getParameter("tabname").toString();
		Map<String,Double> tmp = null;
		tmp = piechartdao.getchartdata(tabname);
		piechart t=new piechart();
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(t.getChartData(tabname,"content",tmp));
		out.flush();
		out.close();
	}

}
