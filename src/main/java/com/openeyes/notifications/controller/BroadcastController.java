package com.openeyes.notifications.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openeyes.notifications.domain.Client;
import com.openeyes.notifications.domain.Credentials;
import com.openeyes.notifications.domain.DevOps;
import com.openeyes.notifications.service.NotifierService;
import com.twilio.sdk.verbs.Hangup;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

@Controller
public class BroadcastController {
	
	private static final Logger logger = LoggerFactory.getLogger(BroadcastController.class);
	
	@SuppressWarnings("unused")
	private static final String MESSAGE = "<?xml version=\"1.0\" content-type=\"text/plain\" encoding=\"UTF-8\"?><Response><Say voice=\"woman\">"
			+ "This is an automated call for 1JL Team."
			+ "Please check servers and dashboard, we seem to have received some alerts. Thank you."+
	"</Say></Response>";
	
	
	@RequestMapping(value = "/message/{mesg}", method = RequestMethod.GET)
    @ResponseBody
    public String sendMessage(@PathVariable String mesg) throws Exception {
    	
		if(!mesg.isEmpty()){
			logger.info("Message received as " + mesg);
			throw new Exception(mesg);
		} else {
			throw new Exception();
		}
    }
	
	@RequestMapping(value = "/call/{mesg}", method = RequestMethod.GET)
    @ResponseBody
    public String playMessage(@PathVariable String mesg) throws Exception {
    	
		return getXMLPlayResponse(mesg);
    }

  /**
   * Returns the xml response that will play the recorded message for the given URL
   * @param mesg which needs playing
   * @return xml response
   */
  public String getXMLPlayResponse(final String mesg) {
    TwiMLResponse twiMLResponse = new TwiMLResponse();
    
    //Play play = new Play(MESSAGE);
    String newMesg = "<?xml version=\"1.0\" content-type=\"text/plain\" encoding=\"UTF-8\"?><Response><Say voice=\"woman\">"
    		+ mesg + "</Say></Response>";
    Say say= new Say(newMesg);

    try {
      twiMLResponse.append(say);
    } catch (TwiMLException e) {
      System.out.println("Unable to create twiml response");
    }

    return twiMLResponse.toEscapedXML();
  }

  /**
   * Method that will create the remote calls using Twilio's rest client for every number
   * in the account (mentioned in json file)
   * @param request
   */
  @RequestMapping(value="/initiatecall", method=RequestMethod.POST)
  public void broadcastSend(@PathVariable String mesg) {
    
	  DevOps[] devops = new NotifierService().getAllDevOps();
	  for (DevOps devop : devops) {
		  List<NameValuePair> params = new ArrayList<>();
	      params.add(new BasicNameValuePair("To", devop.getPhoneNumber()));
	      params.add(new BasicNameValuePair("From", new Credentials().getPhoneNumber()));
		  //params.add(new BasicNameValuePair("Url", "http://d159b3f0.ngrok.io/call/"+mesg));
	      //instead of passing voice url using the application console to set the voice url     
	      //params.add(new BasicNameValuePair("ApplicationSid", new Credentials().getAccountSid()));
		  
		  new Client().call(params);
	  }
	  //return "success.html";
  }

  /**
   * This XML response is necessary to end the call when a new recording is made
   * @return
   */
  public String getXMLHangupResponse() {
    TwiMLResponse twiMLResponse = new TwiMLResponse();

    Say say = new Say("Your recording has been saved. Good bye.");
    Hangup hangup = new Hangup();

    try {
      twiMLResponse.append(say);
      twiMLResponse.append(hangup);
    } catch (TwiMLException e) {
      System.out.println("Unable to create twiml response");
    }

    return twiMLResponse.toXML();
  }
}
