package com.sy.dataopt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sy.util.db;

public class middataopt {
	public void plan_ad_ind() throws SQLException {// 计算plan_ad_ind计算公式为 ceil((sum{c in Campaign} Plan_Ad[v,c,w])/Big_Num);
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT Vehicle,`Week`,SUM(Plan_Ad) FROM plan_ad GROUP BY Vehicle,`Week`;");
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO plan_ad_ind(Vehicle,`Week`,plan_ad_ind) VALUES(?,?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setInt(3, (int) Math.ceil(rs.getDouble(3) / 10000));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
	}

	public void Unit_Ad_Cost() throws SQLException {// 计算Unit_Ad_Cost
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT Vehicle,`Week`,SUM(Plan_Ad) FROM plan_ad GROUP BY Vehicle,`Week`;");
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO plan_ad_ind(Vehicle,`Week`,plan_ad_ind) VALUES(?,?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setInt(3, (int) Math.ceil(rs.getDouble(3) / 10000));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
	}

	public void total_ad_cost() throws SQLException {// 计算总广告花费
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT SUM(fixed_cost.Fixed_Ad_Cost*plan_ad_ind.plan_ad_ind )" + "FROM fixed_cost,plan_ad_ind WHERE fixed_cost.Vehicle=plan_ad_ind.Vehicle AND fixed_cost.`" + "Week`=plan_ad_ind.`Week`;");
		double fix = 0;
		double unit = 0;
		rs.next();
		fix = rs.getDouble(1);
		rs = db.executeQuery(conn, "SELECT SUM(Unit_Ad_Cost.Cost*plan_ad.Plan_Ad) FROM unit_ad_cost,plan_ad WHERE" + " unit_ad_cost.Vehicle=Plan_Ad.Vehicle AND  unit_ad_cost.`Week`=Plan_Ad.`Week`");
		rs.next();
		unit = rs.getDouble(1);
		double total = fix + unit;
		PreparedStatement pstm = db.getPStmt(conn, "update  objlist set val1=" + total + "where objname='object1';");
		pstm.execute();
		pstm.close();
		rs.close();
		conn.close();
	}
	public void tot_plan_ad_nobro() throws SQLException {// 计算tot_plan_ad 不是品牌的广告
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, " SELECT Vehicle,Week,Item,Value FROM (SELECT Plan_Ad.Vehicle,Plan_Ad.`Week`" +
				",Campaign_Item.Item,sum(campaign_item.Campaign_Item*plan_ad.Plan_Ad) AS Value from campaign_item, plan_ad " +
				"WHERE Campaign_Item.Campaign=Plan_Ad.Campaign   GROUP BY Plan_Ad.Vehicle,Plan_Ad.`Week`,campaign_item.Item)" +
				"as total WHERE total.Vehicle NOT IN(SELECT sets.`value`  FROM sets WHERE sets.type='BRAND')");
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO tot_plan_ad(Vehicle,`Week`,Item,Value) VALUES(?,?,?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setString(3, rs.getString(3));
			pstm.setDouble(4, rs.getDouble(4));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
	}
	public void plan_cost() throws SQLException {// 计算plan_cost
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT plan_ad.week,plan_ad.Campaign,plan_ad.Vehicle,Unit_Ad_Cost.Cost*plan_ad.Plan_Ad FROM unit_ad_cost,plan_ad WHERE unit_ad_cost.Vehicle=Plan_Ad.Vehicle AND  unit_ad_cost.`Week`=Plan_Ad.`Week`;");
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO plan_cost(week,campaign,vehicle,Value) VALUES(?,?,?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setString(3, rs.getString(3));
			pstm.setDouble(4, rs.getDouble(4));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.clearBatch();
		rs = db.executeQuery(conn, "SELECT plan_ad_ind.week,plan_ad_ind.Vehicle,fixed_cost.Fixed_Ad_Cost*plan_ad_ind.plan_ad_ind FROM fixed_cost,plan_ad_ind WHERE fixed_cost.Vehicle=plan_ad_ind.Vehicle AND fixed_cost.`Week`=plan_ad_ind.`Week`;");
		pstm = db.getPStmt(conn, "INSERT INTO plan_cost(week,campaign,vehicle,Value) VALUES(?,'all',?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setString(3, rs.getString(3));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
	}
	public void opt_cost() throws SQLException {// 计算plan_cost
		Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT opt_ad.week,opt_ad.Campaign,opt_ad.Vehicle,Unit_Ad_Cost.Cost*opt_ad.Opt_Ad FROM unit_ad_cost,opt_ad WHERE unit_ad_cost.Vehicle=opt_Ad.Vehicle AND  unit_ad_cost.`Week`=opt_Ad.`Week`;");
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO opt_cost(week,campaign,vehicle,Value) VALUES(?,?,?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setString(3, rs.getString(3));
			pstm.setDouble(4, rs.getDouble(4));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.clearBatch();
		rs = db.executeQuery(conn, "SELECT opt_ad_ind.week,opt_ad_ind.Vehicle,fixed_cost.Fixed_Ad_Cost*opt_ad_ind.opt_ad_ind FROM fixed_cost,opt_ad_ind WHERE fixed_cost.Vehicle=opt_ad_ind.Vehicle AND fixed_cost.`Week`=opt_ad_ind.`Week`;");
		pstm = db.getPStmt(conn, "INSERT INTO opt_cost(week,campaign,vehicle,Value) VALUES(?,'all',?,?);");
		while (rs.next()) {
			pstm.setString(1, rs.getString(1));
			pstm.setString(2, rs.getString(2));
			pstm.setString(3, rs.getString(3));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
	}
    public void tot_plan_ad_bra() throws SQLException{
    	Connection conn = db.getConn();
		ResultSet rs = db.executeQuery(conn, "SELECT Plan_Ad.Vehicle,Plan_Ad.`Week`,campaign_item.Item,SUM(campaign_item.Campaign_Item*0.1*Plan_Ad.Plan_Ad) FROM plan_ad ,campaign_item WHERE Vehicle IN (SELECT Value FROM sets WHERE type='BRAND')AND campaign_item.Campaign=Plan_Ad.Campaign GROUP BY Vehicle,`Week`;");
		int conut=0;
		while(rs.next()){
			conut++;
		}
		String[][] vwi=new String[conut][4];
		rs.first();
		do{
			
			vwi[--conut][0]=rs.getString(1);
			vwi[conut][1]=rs.getString(2);
			vwi[conut][2]=rs.getString(3);
			vwi[conut][3]=String.valueOf(rs.getDouble(4));
		}while(rs.next());
		rs =db.executeQuery(conn, "SELECT plan_ad.Vehicle,plan_ad.`Week`,plan_ad.Plan_Ad FROM plan_ad WHERE Vehicle='BSTV' GROUP BY Vehicle,`Week`; ");
		conut=0;
		while(rs.next()){
			conut++;
		}
		String[][] plan_ad=new String[conut][3];
		rs.first();
		do{
			
			plan_ad[--conut][0]=rs.getString(1);
			plan_ad[conut][1]=rs.getString(2);
			plan_ad[conut][2]=String.valueOf(rs.getDouble(3));
		}while(rs.next());
		PreparedStatement pstm = db.getPStmt(conn, "INSERT INTO tot_plan_ad(Vehicle,`Week`,Item,Value) VALUES(?,?,?,?);");
		for(int i=0;i<plan_ad.length;i++){
			//System.out.println(vwi[i][0]+","+vwi[i][1]+","+vwi[i][2]+","+ (Double.valueOf(vwi[i][3])+suan(plan_ad,i,i)));
			pstm.setString(1, vwi[i][0]);
			pstm.setString(2, vwi[i][1]);
			pstm.setString(3, vwi[i][2]);
			pstm.setDouble(4, (Double.valueOf(vwi[i][3])+suan(plan_ad,i,i)));
			pstm.addBatch();
		}
		pstm.executeBatch();
		pstm.close();
		rs.close();
		conn.close();
    }
    public static double suan(String[][] plan_ad,int t1,int t2){
    	if(t1<0){
    		return 0;
    	}
    	if(t2-t1<=11){
    	double t=Double.valueOf(plan_ad[t1][2])*0.1*Math.pow(0.9, t2-t1);
   		return t+suan(plan_ad,t1-1,t2);
    	}else{
    		return 0;
    	}
    }
	public static void main(String[] args) throws SQLException {
        middataopt j=new middataopt();
		//j.total_ad_cost();
       // j.tot_plan_ad_bra();
       // j.plan_cost();
        j.opt_cost();
	}

}
