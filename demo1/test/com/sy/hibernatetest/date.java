package com.sy.hibernatetest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sy.util.db;

public class date {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		Connection  con=db.getConn();
		String sql="select id ,Week from opt_ad ;";
		ResultSet rs=db.executeQuery(con, sql);
		sql="update opt_ad set Week=? where id=?;";
		PreparedStatement pstm=db.getPStmt(con, sql);
		while(rs.next()){
			
			pstm.setString(1, changedata(rs.getString(2)));
			pstm.setInt(2, rs.getInt(1));
			pstm.addBatch();
			
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		con.close();
	}

	public static String changedata(String data){
		String dd=data.substring(0, 2);
		String mm=data.substring(2, 5);
		String yy=data.substring(5, 9);
		if(mm.equals("JAN")){
			mm="01";
		}
		if(mm.equals("FEB")){
			mm="02";
		}
		if(mm.equals("MAR")){
			mm="03";
		}
		if(mm.equals("APR")){
			mm="04";
		}
		if(mm.equals("MAY")){
			mm="05";
		}
		if(mm.equals("JUN")){
			mm="06";
		}
		if(mm.equals("JUL")){
			mm="07";
		}
		if(mm.equals("AUG")){
			mm="08";
		}
		if(mm.equals("SEP")){
			mm="09";
		}
		if(mm.equals("OCT")){
			mm="10";
		}
		if(mm.equals("NOV")){
			mm="11";
		}
		if(mm.equals("DEC")){
			mm="12";
		}
		return yy+"-"+mm+"-"+dd;
	}

	

}
