package com.openeyes.notifications.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openeyes.notifications.domain.Client;
import com.openeyes.notifications.domain.DevOps;
import com.openeyes.notifications.service.NotifierService;

@ControllerAdvice
public class GlobalExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleAllException(Exception ex) {
    	
        String message = customMessage(ex.getMessage());
        logger.info("message to be sent as text - " + message);
        String mediaUrl = "nothing in here";

        // Send a message to the dev ops when something goes unexpectedly wrong.
        DevOps [] devops = new NotifierService().getAllDevOps();
        for (DevOps devop : devops) {
            new Client().sendMessage(devop.getPhoneNumber(), message, mediaUrl);
        }

        return message;
    }

    private String customMessage(String exceptionMessage) {
    	
    	if(!exceptionMessage.isEmpty()){
    		return String.format(exceptionMessage);
    	}
    	
        return String.format("Hello Ops, it seems the mobile web has issues, please check dashboards and servers." + "\n- from team One JL");
    }
}
