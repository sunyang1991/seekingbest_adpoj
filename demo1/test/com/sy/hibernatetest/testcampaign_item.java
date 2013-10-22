package com.sy.hibernatetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.alibaba.fastjson.JSON;
import com.sy.hibernate.bus_item;
import com.sy.hibernate.campaign_item;

public class testcampaign_item {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Configuration cfg = new AnnotationConfiguration();
		    SessionFactory sf=cfg.configure().buildSessionFactory();
		    Session session =sf.openSession();
		    Transaction tx = null;
		    tx = session.beginTransaction();
		    List<campaign_item> result = session.createQuery("from campaign_item").list();
		    try{
		    	session.getTransaction().commit();
		    }
		    catch (HibernateException e) {
		    	   if (tx != null) {
		    		   tx.rollback();
		    		   }
		    		   throw e;
		    		  }
		    System.out.println(result.size());
		    tx=session.beginTransaction();
		    List<bus_item> result2 = session.createQuery("from bus_item").list();
		    try{
		    	session.getTransaction().commit();
		    }
		    catch (HibernateException e) {
		    	   if (tx != null) {
		    		   tx.rollback();
		    		   }
		    		   throw e;
		    		  }finally {
		    		 session.close();
		    		 sf.close();
		    		  }
		    List<String> campaign=new ArrayList<String>();//获取查询内容
			for(campaign_item i:result){
				if(campaign.contains(i.getCampaign())){//去重设置
				}else{
					campaign.add(i.getCampaign());
					System.out.println(i.getCampaign());
				}
			}
			List<String> business=new ArrayList<String>();
			for(bus_item i:result2){
				if(business.contains(i.getBusiness())){//去重设置
				}else{
					business.add(i.getBusiness());
					System.out.println(i.getBusiness());
				}
			}
			List tree=new ArrayList();
			List chillist=new ArrayList();
			List bus=new ArrayList();
			List camp=new ArrayList(); 
			Map  treemap=new HashMap();
			Map  busmap=new HashMap();
			Map  campmap=new HashMap();
			for(String s:business){
				Map  temp=new HashMap();	
				temp.put("text", s);
				temp.put("checked", true);
				bus.add(temp);
			}
			for(String s:campaign){
				Map  temp=new HashMap();	
				temp.put("text", s);
				temp.put("checked", true);
				camp.add(temp);
			}
			busmap.put("children", bus);
			busmap.put("text", "business");
			busmap.put("checked", false);
			busmap.put("state", "closed");
			campmap.put("children", camp);
			campmap.put("text", "campaign");
			campmap.put("checked", false);
			campmap.put("state", "closed");
			chillist.add(campmap);
			chillist.add(busmap);
			treemap.put("children", chillist);
			treemap.put("text", "object1");
			treemap.put("checked", false);
			tree.add(treemap);
			System.out.print(JSON.toJSONString(tree));
			
	}

}
