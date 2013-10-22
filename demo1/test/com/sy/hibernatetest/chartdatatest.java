package com.sy.hibernatetest;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.sy.charts.piechart;
import com.sy.dao.piechartdao;
import com.sy.util.myutil;

public class chartdatatest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map m=piechartdao.getchartdata("campaign");
		myutil.toConfigMap(m, 4);
		/*piechart t=new piechart();
		System.out.print(t.getChartData("拉开极度疯狂拉萨的发", "哈哈", m));
		Set<String> keys =m.keySet();
		for(String key:keys){
			   System.out.println("key="+key+",nub="+m.get(key));
		}*/
    
	}

}
