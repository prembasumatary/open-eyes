package com.openeyes.notifications.service;


import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.openeyes.notifications.domain.DevOps;

@Service
public class NotifierService {
    @SuppressWarnings("unused")
	private String filePath;
    private String json;

    public NotifierService() {
        //this.filePath = getClass().getClassLoader().getResource("administrators.json").getPath();
        json="["
        		//+"{\"name\": \"Prem\",\"phoneNumber\": \"+447765222677\"}"
        		+ "{\"name\": \"Prem\",\"phoneNumber\": \"+8989\"},"
        		+ "{\"name\": \"Satish\",\"phoneNumber\": \"+8999\"},"
        		+ "{\"name\": \"Harsha\",\"phoneNumber\": \"+8999\"},"
        		+ "{\"name\": \"Sanith\",\"phoneNumber\": \"+89898\"},"
        		+ "{\"name\": \"Anirudh\",\"phoneNumber\": \"+8999\"},"
        		+ "{\"name\": \"Jatish\",\"phoneNumber\": \"+899898\"},"
        		+ "{\"name\": \"Abhishek\",\"phoneNumber\": \"+89999\"}"
        	    + "]";
    }

    public NotifierService(String filePath) {
        this.filePath = filePath;
    }

    public DevOps [] getAllDevOps() {
        try {
            //return new Gson().fromJson(new FileReader(filePath), DevOps[].class);
        	return new Gson().fromJson(json, DevOps[].class);
        } catch (Exception e) {
            e.printStackTrace();

            return new DevOps[0];
        }
    }
}

