package com.sy.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class linechart {
	private Map chart = new HashMap();
	private List series = new ArrayList();
	private Map title = new HashMap();
	private Map xAxis = new HashMap();

	public Map getxAxis() {
		return xAxis;
	}

	public void setxAxis(Map xAxis) {
		this.xAxis = xAxis;
	}

	public Map getChart() {
		return chart;
	}

	public void setChart() {// 设置图表类型
		chart.put("defaultSeriesType", "spline");
	}

	public List getSeries() {
		return series;
	}

	public void setSeries(Map data) {// 填充数据
		this.series.add(data);
	}

	public Map getTitle() {
		return title;
	}

	public void setTitle(String text) {// 设置标题
		this.title.put("text", text);
	}

	public String getChartData(String title,Map xdata,Map ydata) {//获取数据
		Map data = new HashMap();
		this.title.put("text", title);
		this.series.add(ydata);
		this.xAxis=xdata;
		data.put("xAxis", this.xAxis);
		data.put("series", this.series);
		data.put("title", this.title);
		return JSON.toJSONString(data);
	}
}
