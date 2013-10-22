/*作用：获取datagrid数据*/

package com.sy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class datagriddao {
	/*通过获取sql语句，
	 * 最大显示数已经目前的页码来获取数据库存储的数据
	 * 并格式化返回给前台。
	 */
	public static String getdata(String sql,int rowes,int page) throws SQLException {
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, sql+" limit  "+ (page-1)*rowes +","+ rowes +"  ;");
		ResultSetMetaData rsmd = rs.getMetaData();
		int total = 0;
		int colnub = rsmd.getColumnCount();
		Map data = new HashMap();
		List rows = new ArrayList();
		while (rs.next()) {
			total++;
			Map tmp = new HashMap();
			for (int i = 0; i < colnub; i++) {
				if (rsmd.getColumnTypeName(i + 1).equals("FLOAT")) {
					tmp.put(rsmd.getColumnName(i + 1), rs.getDouble(i + 1));
				} else {
					tmp.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
				}
			}
			rows.add(tmp);
		}
		data.put("total", total);
		data.put("rows", rows);
		return JSON.toJSONString(data);
	}
}
