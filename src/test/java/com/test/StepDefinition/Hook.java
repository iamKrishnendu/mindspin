package com.test.StepDefinition;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.test.Base.BaseClass;
import com.test.common.PropertyFileHandler;
import com.test.controller.BrowserHandler;


/**
 * 
 * @author KRISHNENDU
 * @category Common steps to execute for UI validation hence Hook steps
 * 
 * */

public class Hook extends BaseClass {

	static Logger log = Logger.getLogger(Hook.class);
	PropertyFileHandler property = new PropertyFileHandler();
	
	
	public void startTestOnBrowser() {
		log.info("Excution is Started");
		
		try {
			
			BrowserHandler.getInstanceOfBrowser();
			String appUrl = property.getPropertyValues().getProperty("Url");
			String browser = property.getPropertyValues().getProperty("Browser");
			BrowserHandler.getDriver().get(appUrl);
			BrowserHandler.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			log.info(appUrl+" is launched on "+browser);
			
		}catch(Throwable e) {
			log.error(e);
		}
	}
	
	public void tearDown() {
		log.info("Closing all active browser instances");
		BrowserHandler.getDriver().quit();
	}
}
