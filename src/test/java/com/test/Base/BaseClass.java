package com.test.Base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.cucumber.listener.Reporter;
import com.test.common.GenericKeywords;

public class BaseClass {

	public static Date date = new Date();
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("MMddyyyy_HHmmss");
	public static String startDate = dateFormatter.format(date);
	
	public static String reportLocation = System.getProperty("user.dir")+"/Test-Report/";
	public static String screenshotLocation = System.getProperty("user.dir")+"/Screenshot/";
	
	public static String source = System.getProperty("user.dir")+"/target/cucumber-reports/report.html";
	public static String destination = reportLocation+startDate;
	public static String screenshotPath = screenshotLocation+startDate;
	static Logger log = Logger.getLogger(BaseClass.class);
	
	public static void testStatus(ITestResult result) {
		if(result.getStatus()==result.FAILURE) {
			try {
				String failedScreenshotPath = GenericKeywords.takeScreenshot("FailedStep").toString();
				log.info("Failed step screenshot placed under-->"+failedScreenshotPath);
				Reporter.addScreenCaptureFromPath(failedScreenshotPath);
			}catch(IOException e) {
				log.error(e);
			}
		}else if(result.getStatus()==result.SKIP) {
			try {
				String failedScreenshotPath = GenericKeywords.takeScreenshot("FailedStep").toString();
				log.info("Failed step screenshot placed under-->"+failedScreenshotPath);
				Reporter.addScreenCaptureFromPath(failedScreenshotPath);
			}catch(IOException e) {
				log.error(e);
			}
		}
	}
}
