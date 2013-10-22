package com.sy.hibernatetest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.alibaba.fastjson.JSON;
import com.sy.dao.datagriddao;
import com.sy.hibernate.bus_item;
import com.sy.hibernate.campaign_item;
import com.sy.util.db;
import com.sy.util.myutil;

public class datagridtest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//String tabname ="business";
	  //  System.out.println(datagriddao.getdata(tabname, "undfine"));
		Connection conn=db.getConn();
		ResultSet rs=null;
		String sql="INSERT INTO runlist(runid,runname,objid) VALUES('"+UUID.randomUUID().toString().replace("-", "")+"','"+"test2"+"'," +
					"'AFE765090604F0EE585302B699985E72');";
			System.out.print(conn.createStatement().execute(sql));
			
	}

}
