package com.openeyes.notifications.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleAllException(Exception ex) {
    	
        String message = customMessage(ex.getMessage());
        logger.info("message to be sent as text - " + message);

        return message;
    }

    private String customMessage(String exceptionMessage) {
    	
    	if(!exceptionMessage.isEmpty()){
    		return String.format(exceptionMessage);
    	}
    	
        return String.format("Hello Ops, it seems the mobile web has issues, please check dashboards and servers." + "\n- from team One JL");
    }
}
