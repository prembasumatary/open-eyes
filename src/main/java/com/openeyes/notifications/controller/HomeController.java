package com.openeyes.notifications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final String SUCCESS = "success.html";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws Exception {

        // Send a message to the DevOps  when something goes unexpectedly wrong.
    	return SUCCESS;
    }
    
    @RequestMapping(value = "/newrelic", method = RequestMethod.GET)
    public String newrelic() throws Exception {
    	//try{
    		//new Crawler().checking();
    	//}catch(Exception e){
    		//throw new Exception("Some issue detected in site");
    	//}
    	
    	return SUCCESS;
    }

    @RequestMapping(value = "/splunk", method = RequestMethod.GET)
    public String splunk() throws Exception {
    	//try{
    		//new Crawler().checking();
    	//}catch(Exception e){
    	//	throw new Exception("Some issue detected in site");
    	//}
    	
    	return SUCCESS;
    }
}
