/*获取折线图的数据*/
package com.sy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sy.util.db;

public class linechartdao {
	/*通过获取sql语句，
	 * 最大显示数已经目前的页码来获取数据库存储的数据
	 * 并格式化返回给前台。
	 */
public static Map getchartdata(String title,String sql) throws SQLException{
	Connection conn=db.getConn();
	ResultSet rs=db.executeQuery(conn, sql);
	ResultSetMetaData rsmd = rs.getMetaData();
	Map linedata=new HashMap();
	List datalist=new ArrayList();
	List datelist=new ArrayList();
	while(rs.next()){
		datelist.add(rs.getObject(1).toString());
		datalist.add(Double.valueOf(rs.getObject(2).toString()));
	}
	linedata.put("categories", datelist);
	linedata.put("data", datalist);
    linedata.put("fidname", rsmd.getColumnName(2));
	return linedata;
}
}
