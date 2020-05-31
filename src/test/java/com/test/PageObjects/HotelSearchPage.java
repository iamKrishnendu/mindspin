package com.test.PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.controller.BrowserHandler;

public class HotelSearchPage {

	public HotelSearchPage() {
		PageFactory.initElements(BrowserHandler.getDriver(), this);
	}
	
	@FindBy(how=How.CSS,using="div[class='chHeaderContainer'] li span:nth-child(2)")
	public List<WebElement> headerOptions;
	
	@FindBy(how=How.CSS,using="div[class='chHeaderContainer'] li span:nth-child(1)")
	public List<WebElement> activeHeaderOption;
	
	@FindBy(how=How.CSS,using="label[for='city'] input")
	public WebElement cityArea;
	
	@FindBy(how=How.CSS,using="div[role='combobox']>input")
	public WebElement cityInput;
	
	@FindBy(how=How.CSS,using="div[role='combobox'] li p:nth-child(1)")
	public List<WebElement>areaSuggestionList;
	
	@FindBy(how=How.CSS,using="label[for='checkin'] input")
	public WebElement checkIn;
	
	@FindBy(how=How.CSS,using="label[for='checkout'] input")
	public WebElement checkOut;
	
	@FindBy(how=How.CSS,using="div[class='DayPicker-Months']>div:nth-child(1)>div[role='heading']>div")
	public WebElement datePickerHeading;
	
	@FindBy(how=How.CSS,using="div[class='DayPicker-Months']>div:nth-child(1)>div[class='DayPicker-Body'] div[class='DayPicker-Day']")
	public List<WebElement>daysOfDatePicker;
	
	@FindBy(how=How.CSS,using="div[class='DayPicker-NavBar'] span[aria-label='Next Month']")
	public WebElement navBarNextMonth;
	
	@FindBy(how=How.CSS,using="label[for='guest'] input")
	public WebElement roomAndGuest;
	
	@FindBy(how=How.CSS,using="div[class='roomsGuests'] li")
	public List<WebElement>listOfGuests;
	
	@FindBy(how=How.CSS,using="button[class='btnAddRoom']")
	public WebElement addAnotherRoomButton;
	
	@FindBy(how=How.CSS,using="button[class='primaryBtn btnApply']")
	public WebElement applyButton;
	
	@FindBy(how=How.CSS,using="label[for='travelFor']")
	public WebElement travellingFor;
	
	@FindBy(how=How.CSS,using="ul[class='travelForPopup'] li")
	public List<WebElement>travellingForOptions;
	
	@FindBy(how=How.XPATH,using="//button[text()='Search']")
	public WebElement searchButton;
	
	@FindBy(how=How.CSS,using="div[class='input-range__slider']")
	public List<WebElement>sliderButtons;
	
	@FindBy(how=How.CSS,using="a[class='mapCont']")
	public List<WebElement> mapOnPage;
	
	@FindBy(how=How.CSS,using="span[class='mapClose']")
	public WebElement mapClose;
	
	@FindBy(how=How.CSS,using="div[class='rangeSliderWrap']>span")
	public List<WebElement>minAndmaxValue;
	
	@FindBy(how=How.XPATH,using="//div[text()='User Rating']//following::label")
	public List<WebElement>listOfUserRatings;
	
	@FindBy(how=How.CSS,using="ul[class='appliedFilters'] li>span")
	public List<WebElement>listOfAppliedFilters;
	
	@FindBy(how=How.CSS,using="div[id='hotelListingContainer'] div div>div>p[id='hlistpg_hotel_name']>span:nth-child(1)")
	public List<WebElement>listOfHotels;
	
	@FindBy(how=How.CSS,using="div[class='roomWrap'] h2")
	public List<WebElement>ListOfroomType;
	
	@FindBy(how=How.XPATH,using="//div[@class='roomWrap']//div[contains(@class,'roomTypePrice')]//span")
	public List<WebElement>priceList;
	
	@FindBy(how=How.XPATH,using="//a[contains(@class,'primaryBtn') and text()='SELECT ROOM']")
	public List<WebElement>listSelectRoomButton;
	
	@FindBy(how=How.CSS,using="div[class='_HotelReview'] h4")
	public WebElement reviewBookingHeader;
	
	@FindBy(how=How.CSS,using="#fName")
	public WebElement traveller_firstName;
	
	@FindBy(how=How.CSS,using="#lName")
	public WebElement traveller_lastName;
	
	@FindBy(how=How.CSS,using="#email")
	public WebElement traveller_email;
	
	@FindBy(how=How.CSS,using="#mNo")
	public WebElement traveller_contactInfo;
	
	@FindBy(how=How.CSS,using="div[class='_SpecialRequest'] li label")
	public List<WebElement>listOfCommonRequested;
	
	@FindBy(how=How.CSS,using="label[for='donation']")
	public WebElement forDonation;
	
	@FindBy(how=How.CSS,using="#totalPaymentRow div:nth-child(2)>span")
	public WebElement totalPayableAmount;
	
	@FindBy(how=How.XPATH,using="//a[contains(@class,'btnPayNow')]")
	public WebElement bookNowButton;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Payment Options')]")
	public WebElement paymentOptionText;
	
	@FindBy(how=How.CSS,using="div[class='summary_description'] p")
	public List<WebElement>listOfSummary;
}
