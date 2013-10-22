package com.sy.hibernatetest;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.sy.util.myutil;

public class testbus_item {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	Configuration cfg = new AnnotationConfiguration();
	SessionFactory sf=cfg.configure().buildSessionFactory();
    Session session =sf.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    List result = session.createQuery("from bus_item where "+myutil.toUpperCaseFirstOne("business")+"='FFC' ").list();
    try{
    	session.getTransaction().commit();
    }
    catch (HibernateException e) {
    	   if (tx != null) {
    		   tx.rollback();
    		   }
    		   throw e;
    		  } finally {
    		   
    		 session.close();
    		 sf.close();
    		  }
    System.out.print(result.size());
	}
	

}
