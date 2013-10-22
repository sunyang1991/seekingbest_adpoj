package com.sy.charts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class piechart {
 private Map chart=new HashMap() ;
 private List series=new ArrayList();
 private Map title=new HashMap();
public Map getChart() {
	return chart;
}
public void setChart() {//设置图表类型
	this.chart.put("defaultSeriesType", "pie");
}

public List getSeries() {
	return series;
}
public void setSeries(String name,Map<String, Double> data) {//填充数据
	Map m=new HashMap();
	m.put("name", name);
	List l=new ArrayList();
	Set<String> keys =data.keySet();
	for(String key:keys){
		List t=new ArrayList();
		t.add(key);
		t.add(data.get(key));
		l.add(t);
	}
	m.put("data", l);
	this.series.add(m);
}
public Map getTitle() {
	return title;
}
public void setTitle(String text) {//设置标题
	this.title.put("text", text);
}

public  String getChartData(String title,String name,Map<String, Double> data){//获取数据
	this.chart.put("defaultSeriesType", "pie");
	this.setTitle(title);
	this.setSeries(name, data);
	return JSON.toJSONString(this);
}
}
