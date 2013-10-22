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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.alibaba.fastjson.JSON;
import com.sy.hibernate.bus_item;
import com.sy.hibernate.campaign_item;
import com.sy.util.db;
import com.sy.util.myutil;

public class treegrid extends HttpServlet {

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

		doPost(request, response);
	}

	/**
	 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("name/html");
		PrintWriter out = response.getWriter();
		Configuration cfg = new AnnotationConfiguration();// 读取配置
		SessionFactory sf = cfg.configure().buildSessionFactory();// 获取工厂实现
		Session session = sf.openSession();// 获得session类
		Transaction tx = null;// 新建事务
		tx = session.beginTransaction();// 开始事务
		List<campaign_item> result = session.createQuery("from campaign_item").list();// 设置查询并获得返回
		try {
			session.getTransaction().commit();// 执行查询
		} catch (HibernateException e) {
			if (tx != null) {// 查询失败事务回滚
				tx.rollback();
			}
			throw e;
		}
		System.out.println(result.size());
		tx = session.beginTransaction();
		List<bus_item> result2 = session.createQuery("from bus_item").list();
		try {
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();// 关闭获取的类
			sf.close();
		}
		List<String> campaign = new ArrayList<String>();// 获取查询内容
		for (campaign_item i : result) {
			if (campaign.contains(i.getCampaign())) {// 去重设置
			} else {
				campaign.add(i.getCampaign());
			}
		}
		List<String> business = new ArrayList<String>();
		for (bus_item i : result2) {
			if (business.contains(i.getBusiness())) {// 去重设置
			} else {
				business.add(i.getBusiness());
			}
		}
		/*************** 加载商品价格信息 ******************/
		String sql = "SELECT DISTINCT Item FROM base_price;";//获取商品民称与广告类型
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, sql);
		Map itemmap = new HashMap();
		List itemid = new ArrayList();
		try {
			while (rs.next()) {
				Map temp = new HashMap();
				temp.put("name", rs.getString(1));
				itemid.add(temp);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		sql = "SELECT DISTINCT Vehicle FROM ad_elas;";
		rs = db.executeQuery(conn, sql);
		Map vemap = new HashMap();
		List vehicle = new ArrayList();
		try {
			while (rs.next()) {
				Map temp = new HashMap();
				temp.put("name", rs.getString(1));
				vehicle.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map> itembox=myutil.cutList(itemid);
		int nub=0;
		for(Map t:itembox){
			List l=(List) t.get("children");
			Map l1=(Map) l.get(0);
			Map l2=(Map) l.get(l.size()-1);
			t.put("name",l1.get("name")+"~"+l2.get("name"));
		}
		vemap.put("id", 4);
		vemap.put("name", "vehicle");
		vemap.put("number", vehicle.size());
		vemap.put("children", vehicle);
		vemap.put( "state","closed");
		itemmap.put("children", itembox);
		itemmap.put("number", itembox.size());
		itemmap.put("id", 5);
		itemmap.put( "state","closed");
		itemmap.put("name", "item");
		List tree = new ArrayList();
		List chillist = new ArrayList();
		List bus = new ArrayList();
		List camp = new ArrayList();
		Map treemap = new HashMap();
		Map busmap = new HashMap();
		Map campmap = new HashMap();
		for (String s : business) {
			Map temp = new HashMap();
			temp.put("name", s);
			bus.add(temp);
		}
		for (String s : campaign) {
			Map temp = new HashMap();
			temp.put("name", s);
			camp.add(temp);
		}
		busmap.put("children", bus);
		busmap.put("id", 2);
		busmap.put("number", bus.size());
		busmap.put("name", "business");
		busmap.put( "state","closed");
		campmap.put("children", camp);
		campmap.put("number", camp.size());
		campmap.put("id", 3);
		campmap.put("name", "campaign");
		campmap.put( "state","closed");
		Map show =new HashMap();
		show.put("name", "plandata");
		show.put("id", 7);
		chillist.add(show);
		chillist.add(campmap);
		chillist.add(busmap);
		chillist.add(vemap);
		//chillist.add(itemmap);
		
		treemap.put("children", chillist);
		treemap.put("number", chillist.size());
		treemap.put( "state","closed");
		treemap.put("name", "object1");
		treemap.put("id",1);
		tree.add(treemap);
		out.print(JSON.toJSONString(tree));
		out.flush();
		out.close();
	}
}
