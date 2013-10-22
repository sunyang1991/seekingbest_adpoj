package com.sy.hibernatetest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sy.util.db;

public class test {

	/**
	 * @param args
	 * @return 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException  {
		/*
		 * ResultSet rs=db.executeQuery(db.getConn(), "SELECT `Week` FROM `tot_plan_ad` WHERE `Week`='ssss' GROUP BY `Week`;"); System.out.println(rs.wasNull()); while(rs.next()){ System.out.println("<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>  "); }
		 */
	double j=0.2,i=0.1;
	System.out.print("$"+(i-j));
	}
}
