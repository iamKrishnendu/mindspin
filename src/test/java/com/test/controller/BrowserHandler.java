package com.test.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.test.common.PropertyFileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author KRISHNENDU
 * @category Responsible to handle multiple browser setup. Following SingleTon design pattern
 * 
 * */

public class BrowserHandler {

	private static WebDriver driver;
	private static BrowserHandler instanceOfBrowserHandler = null;
	PropertyFileHandler prop = new PropertyFileHandler();
	String browserName = null;
	public BrowserHandler() {
		
		browserName = System.getProperty("browser");
		ChromeOptions options = new ChromeOptions();
		BrowserOptions browser = BrowserOptions.valueOf(prop.getPropertyValues().getProperty("Browser"));
		switch(browser) {
		case Chrome :
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		
		case ChromeHeadless:
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			options.setHeadless(true);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		
		case FireFox:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
			
		case IE:
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
			
		default:
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		}
	}
	
	public  static BrowserHandler getInstanceOfBrowser() {
		if(instanceOfBrowserHandler == null) {
			instanceOfBrowserHandler = new BrowserHandler();
		}
		return instanceOfBrowserHandler;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
