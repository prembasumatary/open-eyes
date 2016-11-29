package com.openeyes.notifications.domain;

import java.util.HashMap;
import java.util.Map;

public class Credentials {
    private Map<String, String> env;
 
    public Credentials() {
    	env = new HashMap<String, String>();
        env.put("TWILIO_ACCOUNT_SID", "xxxx");
        env.put("TWILIO_AUTH_TOKEN", "xxxx");
        env.put("TWILIO_PHONE_NUMBER", "+441642688627");
    }

    public String getAccountSid() {
        return env.get("TWILIO_ACCOUNT_SID");
    }

    public String getAuthToken() {
        return env.get("TWILIO_AUTH_TOKEN");
    }

    public String getPhoneNumber() {
        return env.get("TWILIO_PHONE_NUMBER");
    }
}

