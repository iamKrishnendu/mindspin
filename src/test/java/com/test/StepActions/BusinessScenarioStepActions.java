package com.test.StepActions;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.cucumber.listener.Reporter;
import com.test.PageObjects.HotelSearchPage;
import com.test.PageObjects.LoginPage;
import com.test.common.GenericKeywords;
import com.test.common.PropertyFileHandler;
import com.test.controller.BrowserHandler;
import com.test.utilities.Encrypter;
import com.test.utilities.ExcelParser;
import com.test.utilities.PrepareJSON;

public class BusinessScenarioStepActions extends GenericKeywords{

	Logger log = Logger.getLogger(BusinessScenarioStepActions.class);
	LoginPage login = new LoginPage();
	HotelSearchPage hotelSearch = new HotelSearchPage();
	PropertyFileHandler properties = new PropertyFileHandler();
	Encrypter decrypter = null;
	PrepareJSON json = new PrepareJSON();
	ExcelParser dataSheet = new ExcelParser();
	Map<String,Object>capturedValues = new HashMap<String,Object>();
	
	String sheetName = "TestData";


	public void validate_mmt_page_loaded_successfully() {

		try {
			Assert.assertEquals(elementIsPresent(login.loginOrCreateAccountButton), true);
			Reporter.addStepLog("MakeMyTrip homepage loaded successfully");

		}catch(Throwable e) {
			log.error(e);
			log.info("Exception occured while loading the MMT homepage");
		}

	}

	public void validate_loginButton_isVisible_OnThe_homepage_and_click(String textOnButton) {
		try {
			Assert.assertEquals(verifyText(login.loginOrCreateAccountButton, textOnButton),true);
			jsClick(login.loginOrCreateAccountButton);
			Reporter.addStepLog(textOnButton +" button is visible on the homepage and user peforms click on the button");
			log.info("Login button is clicked");

		}catch(Throwable e) {
			log.error(e);
			log.info("Exception occured while validating Login or Create Account button on the page");
		}
	}

	public void validate_login_popup_isvisible() {
		try {

			Assert.assertEquals(elementIsPresent(login.emailTextBox),true);
			log.info("Login popup is visible now");
			Reporter.addStepLog(login.emailTextBox.getAttribute("placeholder") +" is visible as well as a placeholder on Login popup");

		}catch(Throwable e) {
			log.error(e);
			log.info("Exception occured while validating Login popup is appeared or not");
		}
	}

	public boolean enter_emailId_and_click_on_continue() {

		boolean executionFlag = false;
		try {

			performClick(login.emailTextBox);
			enterTextIntoTextBox(login.emailTextBox, properties.getPropertyValues().getProperty("username"));
			pause(700);
			login.continueButton.isEnabled();
			log.info("Username entered suceessfully and Continue button is enabled");
			performClick(login.continueButton);
			log.info("Continue button is clicked now");
			Reporter.addStepLog("Username entered and Contunie button is clicked");
			return executionFlag = true;

		}catch(Throwable e) {
			log.error(e);
			log.info("Exception occured while entering email id on the specific field");
		}
		return executionFlag;
	}


	public boolean enter_password_and_click_on_login() {

		boolean executionFlag = false;
		try {
			
			decrypter = new Encrypter();
			String password = decrypter.decrypt(properties.getPropertyValues().getProperty("password"));
			pause(500);
			performClick(login.passwordTextBox);
			enterTextIntoTextBox(login.passwordTextBox,password);
			login.loginButton.isEnabled();
			log.info("Password entered suceessfully and Login button is enabled");
			performClick(login.loginButton);
			log.info("Login button is clicked now");
			Reporter.addStepLog("Password entered and Login button is clicked");
			return executionFlag = true;

		}catch(Throwable e) {
			log.error(e);
			log.info("Exception occured while entering password on the specific field");
		}
		return executionFlag;
	}
	
	public void validate_firstName_on_top() {
		
	}
	
	public void validate_userFirstName_displayed_on_top(String testDataSet) {
		try {
				String firstName = dataSheet.testData(sheetName, testDataSet, 0);
				Assert.assertEquals(verifyText(login.loggedInUserName, "Hey "+firstName), true);
				log.info(firstName +" is visible on the top");
				Reporter.addStepLog("Hey "+firstName +" is visible on the top");
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while entering password on the specific field");
		}
	}
	
	public boolean select_option_and_validate_the_option_is_active(String option) {
		boolean executionFlag = false;
		int elementIndex = 0;
		try {
				for(WebElement element : hotelSearch.headerOptions) {
					if(verifyText(element, option)==true) {
						
						performClick(element);
						hotelSearch.activeHeaderOption.get(elementIndex).getAttribute("class").contains("active");
						log.info(option+" is selected and active now");
						Reporter.addStepLog(option+" is selected and active now");
						return executionFlag = true;
					}
					elementIndex++;
				}
			
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while selecting the "+option+" from the header");
		}
		return executionFlag;
	}
	
	public boolean select_location(String testDataSet) {
		boolean executionFlag = false;
		String location = dataSheet.testData(sheetName, testDataSet, 2);
		String area = dataSheet.testData(sheetName, testDataSet, 3);
		try {
			
			performClick(hotelSearch.cityArea);
			enterTextIntoTextBox(hotelSearch.cityInput, location);
			pause(500);
			log.info(location+" is entered to search");
			
			for(WebElement element : hotelSearch.areaSuggestionList) {
					if(verifyText(element, area)==true) {
						performClick(element);
						hotelSearch.cityArea.getAttribute("value").contains(area);
						log.info(hotelSearch.cityArea.getAttribute("value")+" is selected as location");
						Reporter.addStepLog(hotelSearch.cityArea.getAttribute("value")+" is selected as location");
						return executionFlag = true;
					}
			}
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while selecting the "+location+" from the combobox");
		}
		return executionFlag;
	}
	
	public void select_checkin_date_from_datePicker(String testDataSet) {
		String date = dataSheet.testData(sheetName, testDataSet, 4);
		Assert.assertEquals(select_date_from_datePicker(date), true);
		log.info("CheckIn date selected as "+date);
		Reporter.addStepLog("CheckIn date selected as "+date);
	}
	
	public void select_checkout_date_from_datePicker(String testDataSet) {
		String date = dataSheet.testData(sheetName, testDataSet, 5);
		Assert.assertEquals(select_date_from_datePicker(date), true);
		log.info("CheckOut date selected as "+date);
		Reporter.addStepLog("CheckOut date selected as "+date);
	}
	
	public boolean select_date_from_datePicker(String date) {
		
		boolean dateSelectionFlag = false;
		String month = date.split("-")[1];
		String year = date.split("-")[2];
		String day = date.split("-")[0];
		try {
				while(elementIsPresent(hotelSearch.datePickerHeading)) {
					if(hotelSearch.datePickerHeading.getAttribute("innerText").contains(month) 
							&& hotelSearch.datePickerHeading.getAttribute("innerText").contains(year)) {
						
								for(WebElement eachday : hotelSearch.daysOfDatePicker) {
									 if(eachday.getText().equalsIgnoreCase(day)) {
										 performClick(eachday);
										 
										 return dateSelectionFlag=true;
									 }
										
								}
					}else {
						performClick(hotelSearch.navBarNextMonth);
					}
				}
				
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while selecting the date from the datepicker");
		}
		return dateSelectionFlag;
	}
	
	public boolean select_rooms_guests(String testDataSet) {
		
		boolean flag = false;
		String data = dataSheet.testData(sheetName, testDataSet, 6);
		String adults = data.split("-")[0];
		String children = data.split("-")[1];
		try {
				jsClick(hotelSearch.roomAndGuest);
			    pause(500);
			  for(WebElement eachGuest : hotelSearch.listOfGuests) {
							if(eachGuest.getAttribute("data-cy").contains("adults") &&eachGuest.getText().equals(adults)) {
									performClick(eachGuest);
							}else if(eachGuest.getAttribute("data-cy").contains("children") &&eachGuest.getText().equals(children)) {
								performClick(eachGuest);
								pause(2500);
							}
					}
					   performClick(hotelSearch.applyButton);
					   log.info("No. of Adult guests are: "+adults+" with "+children+" no of children");
					   Reporter.addStepLog("No. of Adult guests are: "+adults+" with "+children+" no of children");
					   return flag = true;		
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while selecting the room and guests details");
		}
		return flag;
	}
	
	public boolean select_travelling_for_reason(String testDataSet) {
		boolean status = false;
		String travellingFor = dataSheet.testData(sheetName, testDataSet, 7);
		
		try {
			jsClick(hotelSearch.travellingFor);
			for(WebElement reason : hotelSearch.travellingForOptions) {
					if(reason.getText().equalsIgnoreCase(travellingFor)) {
						performClick(reason);
						return status = true;
					}
			}
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while selecting the Travelling for value");
		}
		return status;
	}
	
	public void click_on_searchButton() {
		try {
				performClick(hotelSearch.searchButton);
				log.info("Search button clicked");
				Reporter.addStepLog("Search button clicked");
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while clicking on Search button");
		}
	}
	
	public boolean set_the_priceRange() {
		boolean status = false;
		try {
			pause(1500);
			
			if(!(hotelSearch.mapOnPage.size()==0)) {
				performClick(hotelSearch.mapOnPage.get(0));
				performClick(hotelSearch.mapClose);
			}
			pause(500);
			slideBarWithCoordinates(hotelSearch.sliderButtons.get(0),5,5);
			log.info("Min value is set as: "+hotelSearch.minAndmaxValue.get(0).getText());
			log.info("Max value is set as: "+hotelSearch.minAndmaxValue.get(1).getText());
			Reporter.addStepLog("Min value is set as: "+hotelSearch.minAndmaxValue.get(0).getText());
			Reporter.addStepLog("Max value is set as: "+hotelSearch.minAndmaxValue.get(1).getText());
			return status=true;
			
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while set the price range");
		}
		return status;
	}
	
	public boolean provide_user_rating(String userRating) {
		boolean statusFlag = false;
		pause(700);
		try {
				for(WebElement eachRating : hotelSearch.listOfUserRatings) {
					if(verifyText(eachRating, userRating)==true) {
						jsClick(eachRating);
						return statusFlag=true;
					}
				}
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while providing user rating");
		}
		return statusFlag;
	}
	
	public String captureAppliedFilters() {
		String status = "Failed";
		String[]fields = new String[] {"Price Range","User Rating","Location"};
		int count = 0; 
		
		try {
				for(WebElement appliedFilter : hotelSearch.listOfAppliedFilters) {
					capturedValues.put(fields[count], appliedFilter.getText());
					log.info("Captured value: "+fields[count]+":"+appliedFilter.getText());
					count++;
				}
				json.writeMultipleValuesIntoJson(capturedValues);
				Reporter.addStepLog("Applied Filtered values are captured ");
				return status = "Passed";
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while capturing applied filters details");
		}
		return status;
	}
	
	public void capture_specifiedHotelName_and_click_on_it(int hotelNo) {
		try {
			
			pause(1000);
			awaitForElementToBeClickable(hotelSearch.listOfHotels.get(hotelNo-1));
			capturedValues.put("Hotel", hotelSearch.listOfHotels.get(hotelNo-1).getText());
			json.writeMultipleValuesIntoJson(capturedValues);
			log.info(hotelSearch.listOfHotels.get(hotelNo-1).getText()+ " is the"+ hotelNo+" hotel from the list");
			Reporter.addStepLog(hotelSearch.listOfHotels.get(hotelNo-1).getText()+ " is the"+ hotelNo+" hotel from the list");
			
			scrollToView(hotelSearch.listOfHotels.get(hotelNo-1));
			jsClick(hotelSearch.listOfHotels.get(hotelNo-1));
			log.info("Clicked on hotel name");
			Reporter.addStepLog(hotelNo+ " is selected from the list and clicked on it");
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while capturing Hotel name");
		}
	}
	
	public boolean capture_details_of_room_and_select() {
		boolean executionFlag = false;
		
		try {
			    pause(1000);
				awaitForElementToBeClickable(hotelSearch.ListOfroomType.get(0));
				scrollToView(hotelSearch.ListOfroomType.get(0));
				capturedValues.put("Room Type", hotelSearch.ListOfroomType.get(0).getText());
				capturedValues.put("Price", hotelSearch.priceList.get(0).getText());
				json.writeMultipleValuesIntoJson(capturedValues);
				jsClick(hotelSearch.listSelectRoomButton.get(0));
				elementIsPresent(hotelSearch.reviewBookingHeader);
				log.info("User on the Review Booking page");
				return executionFlag=true;
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while capturing room details");
		}
		return executionFlag;
	}
	
	public boolean fill_traveller_information_and_select_commonly_requested_options(String testDataSet, String firstCommonOpt, String secondCommonOpt) {
		boolean testStatus = false;
		String firstName = dataSheet.testData(sheetName, testDataSet, 0);
		String lastName = dataSheet.testData(sheetName, testDataSet, 1);
		//String email = dataSheet.testData(sheetName, testDataSet, 8);
		//String phone = dataSheet.testData(sheetName, testDataSet, 9);
		
		try {
			    pause(1000);
				scrollToView(hotelSearch.traveller_firstName);
				enterTextIntoTextBox(hotelSearch.traveller_firstName, firstName);
				enterTextIntoTextBox(hotelSearch.traveller_lastName, lastName);
			//	enterTextIntoTextBox(hotelSearch.traveller_email, email);
			//	enterTextIntoTextBox(hotelSearch.traveller_contactInfo, phone);
				pause(500);
				for(WebElement eachOption : hotelSearch.listOfCommonRequested) {
						if(verifyText(eachOption, firstCommonOpt)==true || (verifyText(eachOption, secondCommonOpt)==true )){
							scrollToView(eachOption);
							performClick(eachOption);
							log.info("Option selected as: "+eachOption.getText());
							Reporter.addStepLog("Option selected as: "+eachOption.getText());
							
						}
						return testStatus = true;
				}
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while filling traveller information");
		}
		return testStatus;
	}
	
	public boolean uncheck_donation_option_from_page() {
		boolean testStatus = false;
		
		try {
			    pause(500);
				scrollToView(hotelSearch.forDonation);
				performClick(hotelSearch.forDonation);
				log.info("Donation checkbox is unchecked now");
				return testStatus = true;
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while uncheck the donation checkbox");
		}
		return testStatus;
	}
	
	public boolean click_on_book_now_button() {
		boolean testStatus = false;
		
		try {
				pause(500);
				capturedValues.put("Total Payable", hotelSearch.totalPayableAmount.getText());
				json.writeMultipleValuesIntoJson(capturedValues);
				log.info("Total payable amount "+hotelSearch.totalPayableAmount.getText());
				performClick(hotelSearch.bookNowButton);
				log.info("Book Now button is clicked now");
				return testStatus = true;
		}catch(Exception e) {
			log.error(e);
			log.info("Exception occured while click on the Book Now button");
		}
		return testStatus;
	}
	
	public void validate_summary_details(String testDataSet) {
		try {
			    pause(500);
			    awaitForElementToVisible(hotelSearch.paymentOptionText);
				int index = 0;
				String hotelname = json.readJson("Hotel");
				String payable = json.readJson("Total Payable");
				String email = dataSheet.testData(sheetName, testDataSet, 8);
				String phone = dataSheet.testData(sheetName, testDataSet, 9);
				
				String[]data = new String[] {hotelname,phone,email,payable};
				
				for(WebElement eachValue : hotelSearch.listOfSummary) {
					
						if(eachValue.getText().contains(data[index])) {
							Assert.assertTrue(true);
							
							index++;
						}
				}
			
		}catch(Exception e){
			log.error(e);
			log.info("Exception occured while comparing the summary values");
		}
	}
}

