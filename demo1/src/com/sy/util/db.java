package com.sy.util;

import java.sql.*;


public class db  {
	
       static{
    	   try {
			Class.forName("com.mysql.jdbc.Driver");//������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
       }
       public static Connection getConn(){//��ȡ����
    	  Connection conn=null;
    	   try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?user=root&password=root");
		} catch (SQLException e) {

			e.printStackTrace();
		}
    	return conn;
       }
       public static void closeConn(Connection conn){//�ر�����
    	   try {
			if(conn!=null){
				   conn.close();
				   conn=null;
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
       }
       public static Statement getStmt(Connection conn){//��ȡStatement
    	   Statement stmt = null;
    	   try {
			stmt =conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	  return stmt; 
       }
       public static void closeStmt(Statement stmt){//�ر�Statement
    	   try {
			if(stmt!=null){
				   stmt.close();
				   stmt=null;
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
       }
       public static PreparedStatement getPStmt(Connection conn,String sql){//��ȡStatement
    	   PreparedStatement pstmt = null;
    	   try {
			pstmt =conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	  return pstmt; 
       }
       public  static ResultSet executeQuery(Connection conn,String sql){//��ȡResultSet
    	   ResultSet rs=null;
    	   try {
			rs=conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	   return rs;
       }
       public static void closeResultSet(ResultSet rs){//�ر�ResultSet
    	   try {
			if(rs!=null){
				   rs.close();
				   rs=null;
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
       }  
       public  db(){//���캯��  
       }
       
}

