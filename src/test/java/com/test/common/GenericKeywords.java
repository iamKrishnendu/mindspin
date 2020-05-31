package com.test.common;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.common.PropertyFileHandler;

import com.test.Base.BaseClass;
import com.test.controller.BrowserHandler;


/**
 * 
 * @author KRISHNENDU
 * @category Contains Generic reusable methods
 * */

public class GenericKeywords {

	static PropertyFileHandler properties = new PropertyFileHandler();
	static Logger log = Logger.getLogger(GenericKeywords.class);
	public static File takeScreenshot(String screenshotName) {
		File screenshotDestination = null;
		
		try {
			
			TakesScreenshot takescreenshot = (TakesScreenshot)BrowserHandler.getDriver();
			File screenshot = takescreenshot.getScreenshotAs(OutputType.FILE);
			screenshotDestination = new File(BaseClass.screenshotPath+"/"+screenshotName+".png");
			FileUtils.copyFile(screenshot, screenshotDestination);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		return screenshotDestination;
	}
	
	public static void awaitForElementToVisible(WebElement element) {
		long maxTimeOut = Long.parseLong(properties.getPropertyValues().getProperty("TimeOut"));
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(),maxTimeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			log.error(e);
			System.exit(1);
		}
		
	}
	
	public static void pause(long pauseTime) {
		try {
			Thread.sleep(pauseTime);
		}catch(Exception e) {
			log.error(e);
		}
		
	}
	
	public static void awaitForElementToBeClickable(WebElement element) {
		long maxTimeOut = Long.parseLong(properties.getPropertyValues().getProperty("TimeOut"));
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(),maxTimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception e) {
			log.error(e);
			System.exit(1);
		}
		
	}
	
	public static void enterTextIntoTextBox(WebElement element, String value) {
		awaitForElementToVisible(element);
		element.clear();
		pause(200);
		element.sendKeys(value);
		log.info("value entered into the textbox with locator "+element+" successfully");
	}
	
	public static void switchToFrame(WebElement element) {
		BrowserHandler.getDriver().switchTo().frame(element);
		log.info("Successfully switch to iFrame with reference of "+element);
	}
	public static void switchToMainWindow() {
		BrowserHandler.getDriver().switchTo().parentFrame();
	}
	
	public static boolean elementIsPresent(WebElement element) {
		boolean elementFlag = false;
		try {
			awaitForElementToVisible(element);
			elementFlag = element.isDisplayed();
			return elementFlag;
			
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured to determine the presence of element");
		}
		return elementFlag;
	}
	
	public static void performDownArrowAndEnter(WebElement element) {
		try {
			awaitForElementToVisible(element);
			element.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while performing Enter operation on "+element);
		}
	}
	public static void performEnter(WebElement element) {
		try {
			awaitForElementToVisible(element);
			element.sendKeys(Keys.ENTER);
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while performing Enter operation on "+element);
		}
	}
	
	public static boolean verifyText(WebElement element,String textToBeValidated) {
		boolean textPresentOnElement = false;
		String actualTextFromElement = null;
		try {
			awaitForElementToVisible(element);
			actualTextFromElement = element.getText();
			if(actualTextFromElement.equalsIgnoreCase(textToBeValidated)) {
				return textPresentOnElement=true;
			}
			
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while verifing text on element as actual text is: "+actualTextFromElement);
		}
		return textPresentOnElement;
	}
	
	public static void switchToPopUpWindow() {
		String window = BrowserHandler.getDriver().getWindowHandle();
		BrowserHandler.getDriver().switchTo().window(window);
	}
	
	public static void switchToPopUp() {
		 BrowserHandler.getDriver().switchTo().alert();
		
	}
	
	public static void windowHandler(String windowType) {
		Set<String>handler = BrowserHandler.getDriver().getWindowHandles();
		Iterator<String>it = handler.iterator();
		String parentWindow = it.next();
		
		if(windowType.equalsIgnoreCase("Parent")) {
			System.out.println("Parent Window ID: "+parentWindow);
			BrowserHandler.getDriver().switchTo().window(parentWindow);
		}else if(windowType.equalsIgnoreCase("Child")) {
			String childWindow = it.next();
			System.out.println("Chile Window ID: "+childWindow);
			BrowserHandler.getDriver().switchTo().window(childWindow);
		}
		
	}
	
	public static boolean selectValueFromDropDownBasedOnOptions(WebElement element,String option) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByVisibleText(option);
			flag = true;
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while select the options:  "+option);
		}
		return flag;
		
	}
	
	public static void performClick(WebElement element) {
		try {
			  awaitForElementToVisible(element);
			  element.click();
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while performing click on the element:  "+element);
		}
	}
	
	public static void jsClick(WebElement element) {
		JavascriptExecutor executor  = (JavascriptExecutor ) BrowserHandler.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void scrollToView(WebElement element) {
		JavascriptExecutor executor  = (JavascriptExecutor ) BrowserHandler.getDriver();
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void selectDropDownJS(WebElement dropdownLocation,String option) {
		dropdownLocation.click();    
		((JavascriptExecutor)BrowserHandler.getDriver()).executeScript("arguments[0].click();", BrowserHandler.getDriver().findElement(By.xpath(option)));
	}
	
	public static void slideBarWithCoordinates(WebElement slider, int xOffset, int yOffset) {
		try {
				awaitForElementToVisible(slider);
				Actions actions = new Actions(BrowserHandler.getDriver());
				
				actions.clickAndHold(slider)
			    .moveByOffset(xOffset, yOffset)
			    .release().build().perform();
				
				slider.click();
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while sliding the slider on the page");
		}
	}
}

	