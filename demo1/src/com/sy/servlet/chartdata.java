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

public class chartdata extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*获取前台发送的表名与拼接的sql语句
		 * 调用数据获取函数获取数据并图表需要的数据返回*/
		String tabname =request.getParameter("tabname").toString();
		String sql =request.getParameter("sql").toString();
		Map tmp = null;
		try {
			tmp = linechartdao.getchartdata(tabname,sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map  xdata=new HashMap();
		Map  ydata=new HashMap();
		xdata.put("categories", tmp.get("categories"));
		ydata.put("name", tmp.get("fidname"));
		ydata.put("data", tmp.get("data"));
		linechart t=new linechart();
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(t.getChartData(tabname,xdata,ydata));
		out.flush();
		out.close();
	}

}
