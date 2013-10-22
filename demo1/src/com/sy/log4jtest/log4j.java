package com.sy.log4jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4j {
	 private static Logger logger = LogManager.getLogger(log4j.class.getName());  
	  
     public static void main(String[] args) {  

     // Set up a simple configuration that logs on the console.  

         logger.trace("Entering application.");  //Log a message object with the TRACE level.  
         Bar bar = new Bar();  
         if (!bar.doIt()) {  
             logger.error("Didn't do it.");  
         }  
         logger.trace("Exiting application.");  
     }  
}
