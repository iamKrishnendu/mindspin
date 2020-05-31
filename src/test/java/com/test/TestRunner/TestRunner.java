package com.test.TestRunner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import com.cucumber.listener.Reporter;
import com.test.Base.BaseClass;
import com.test.StepDefinition.Hook;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * 
 * @author KRISHNENDU
 * @category TestRunner file. Centralized test runner file, responsible to trigger the execution
 * 
 * */


@CucumberOptions(
		features= {"src/test/resources/features"},
		plugin="com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
		glue= "com.test.StepDefinition", 
		monochrome=true,
		tags= {"@Test"})

public class TestRunner extends AbstractTestNGCucumberTests{

	
	static Logger log = Logger.getLogger(TestRunner.class);
	File filePath = new File(BaseClass.reportLocation);
	File source = new File (BaseClass.source);
	File destination  = new File(BaseClass.destination);
	Hook hookSteps = new Hook();
	
	@BeforeSuite
	public void setUp() {
		if(!filePath.exists()) {
			filePath.mkdir();
		}
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/log4j.properties");
		
		
	}
	
	@BeforeClass
	public void startTest() {
		hookSteps.startTestOnBrowser();
	}
	
	@AfterMethod
	public void afterEachMethod(ITestResult result) {
		BaseClass.testStatus(result);
	}

	
	@AfterClass
	
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		Reporter.setSystemInfo("Username", System.getProperty("user.name"));
		Reporter.setSystemInfo("OS", System.getProperty("os.name"));
		Reporter.setSystemInfo("TimeZone", System.getProperty("user.timezone"));
		
	}
	
	@AfterSuite
	public void moveReportToDestination() {
		try {
			FileUtils.copyFileToDirectory(source, destination);
			hookSteps.tearDown();
			
		}catch(IOException e) {
			log.error(e);
		}
	}
}
