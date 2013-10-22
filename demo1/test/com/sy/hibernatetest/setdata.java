package com.sy.hibernatetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sy.util.db;

public class setdata {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		Connection  con=db.getConn();
		String sql="insert into sets(type,value) values(?,?);";
		PreparedStatement pstm=db.getPStmt(con, sql);
        File file=new File("E:\\dropbox\\SunYang\\SearsData\\SearsCanSets.dat");
        FileReader fr = new FileReader("E:\\dropbox\\SunYang\\SearsData\\SearsCanSets.dat");
        BufferedReader bf=new BufferedReader(fr);
        String style=null;
        while(bf.ready()){
        	String str=bf.readLine().toString();
        	if(str.contains("set")){
        		style=str.substring(4, str.indexOf(":="));
        		continue;
        	}else if(!str.equals(";")){
        		pstm.setString(1,style);
    			pstm.setString(2,str);
    			pstm.addBatch();
        	}
        }
        pstm.executeBatch();
        pstm.close();
        con.close();
	}

}
