package com.sy.log4jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bar {
	static Logger logger = LogManager.getLogger(Bar.class.getName());  
	  
    public boolean doIt() {  
      logger.entry();   //Log entry to a method  
      logger.error("Did it again!");   //Log a message object with the ERROR level  
      logger.exit();    //Log exit from a method   
      return false;  
    } 
}
