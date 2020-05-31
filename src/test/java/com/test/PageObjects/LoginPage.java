package com.test.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.controller.BrowserHandler;

public class LoginPage {
	
	public LoginPage() {
		PageFactory.initElements(BrowserHandler.getDriver(), this);
	}
	
	@FindBy(how=How.CSS,using="ul[class='userSection pushRight']>li:nth-child(6) div")
	public WebElement loginOrCreateAccountButton;
	
	@FindBy(how=How.CSS,using="section[class='modalMain '] >form>div input")
	public WebElement emailTextBox;
	
	@FindBy(how=How.CSS,using="section[class='modalMain '] >form>div button")
	public WebElement continueButton;
	
	@FindBy(how=How.CSS,using="section[class='modalMain '] >form>div input")
	public WebElement passwordTextBox;
	
	@FindBy(how=How.CSS,using="section[class='modalMain '] >form>div button")
	public WebElement loginButton;
	
	@FindBy(how=How.CSS,using="p[data-cy='loggedInUser']")
	public WebElement loggedInUserName;
	
	

}
