package com.sy.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class myutil {
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public static List<Map> cutList(List s) {
		int totle = (s.size() % 30) == 0 ? s.size() / 30 : s.size() / 30 + 1;
		List t = new ArrayList();
		List<Map> listbox = new ArrayList<Map>();
		int i = 0;
		for (Object o : s) {
			i++;
			t.add(o);
			if (i % 30 == 0) {
				Map tep = new HashMap();
				List tepl =new ArrayList(t);
				tep.put("children", tepl);
				listbox.add((Map) new HashMap(tep));
				t.clear();
			}
		}
		if (i % 30 != 0) {
			Map tep = new HashMap();
			List tepl =new ArrayList(t);
			tep.put("children", tepl);
			listbox.add((Map) new HashMap(tep));
		}
		return listbox;
	}

	public static Map<String, Double> toConfigMap(Map<String, Double> map, int maxnub) {
		if (map.size() < maxnub) {
			return map;
		}
		List<Map.Entry<String, Double>> mappingList = null;
		mappingList = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
		Collections.sort(mappingList, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> mapping1, Map.Entry<String, Double> mapping2) {
				return mapping1.getValue().compareTo(mapping2.getValue());
			}
		});
		Map<String, Double> outmap = new HashMap<String, Double>();
		int j = 0;
		double i = 0.0;
		for (Entry<String, Double> mapping : mappingList) {
			if (++j > mappingList.size() - maxnub) {
				outmap.put(mapping.getKey(), mapping.getValue());
				System.out.println(mapping.getKey() + ":" + mapping.getValue());
			} else {
				i += mapping.getValue();
			}
		}
		outmap.put("others", i);
		return outmap;
	}
}
