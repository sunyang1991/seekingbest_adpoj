package com.sy.hibernatetest;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sy.util.myutil;

public class utiltest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       List test=new ArrayList();
       for(int i=75;i>0;i--){
    	   test.add(i);
       }
       System.out.print(JSON.toJSONString(myutil.cutList(test)));
	}

}
