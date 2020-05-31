package com.test.StepDefinition;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.test.StepActions.BusinessScenarioStepActions;
import com.test.controller.BrowserHandler;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MakeMyTrip_BusinessScenarios_Steps extends BusinessScenarioStepActions {

	Logger log = Logger.getLogger(MakeMyTrip_BusinessScenarios_Steps.class);
	@Given("^Browser should get open and MakeMyTrip should be launched$")
	public void browser_should_get_open_and_MakeMyTrip_should_be_launched() throws Throwable {
		validate_mmt_page_loaded_successfully();
	}

	@When("^User clicks on the \"([^\"]*)\" button$")
	public void user_clicks_on_the_button(String textOnButton) throws Throwable {
		validate_loginButton_isVisible_OnThe_homepage_and_click(textOnButton);
	}

	@Then("^Login popup should get visible$")
	public void login_popup_should_get_visible() throws Throwable {
		validate_login_popup_isvisible();
	}

	@Then("^User needs to enter valid email id and click on Continue button$")
	public void user_needs_to_enter_valid_email_id_and_click_on_Continue_button() throws Throwable {
		Assert.assertEquals(enter_emailId_and_click_on_continue(), true);
	}

	@Then("^User needs to enter password and click on login button$")
	public void user_needs_to_enter_password_and_click_on_login_button() throws Throwable {
	   Assert.assertEquals(enter_password_and_click_on_login(), true);
	}

	@Then("^Once login is successfull user should see the firstname on top right corner of the page as mentioned in the \"([^\"]*)\"$")
	public void once_login_is_successfull_user_should_see_the_firstname_on_top_right_corner_of_the_page_as_mentioned_in_the(String testDataSet) throws Throwable {
		validate_userFirstName_displayed_on_top(testDataSet);
	}
	
	@When("^User logged in then click on the \"([^\"]*)\" option from the header menu and determine that selected option is active now$")
	public void user_logged_in_then_click_on_the_option_from_the_header_menu_and_determine_that_selected_option_is_active_now(String option) throws Throwable {
			Assert.assertEquals(select_option_and_validate_the_option_is_active(option), true);
	}

	@When("^Select location as mentioned in the \"([^\"]*)\"$")
	public void select_location_as_mentioned_in_the(String testDataSet) throws Throwable {
			Assert.assertEquals(select_location(testDataSet),true);
	}
	@When("^Select CheckIn and CheckOut date as mentioned in the \"([^\"]*)\"$")
	public void select_CheckIn_and_CheckOut_date_as_mentioned_in_the(String testDataSet) throws Throwable {
		select_checkin_date_from_datePicker(testDataSet);
		select_checkout_date_from_datePicker(testDataSet);
	}

	@When("^Also select Rooms and Guests details as well from the \"([^\"]*)\"$")
	public void also_select_Rooms_and_Guests_details_as_well_from_the(String testDataSet) throws Throwable {
		Assert.assertEquals(select_rooms_guests(testDataSet),true);
	}
	
	@When("^Select Travelling for value from the page as mentioned in \"([^\"]*)\"$")
	public void select_Travelling_for_value_from_the_page_as_mentioned_in(String testDataSet) throws Throwable {
	    Assert.assertEquals(select_travelling_for_reason(testDataSet), true);
	}
	
	@When("^Click on Search$")
	public void click_on_Search() throws Throwable {
		click_on_searchButton();
	}
	
	@When("^Set the price range by sliding the slide bar$")
	public void set_the_price_range_by_sliding_the_slide_bar() throws Throwable {
		Assert.assertEquals(set_the_priceRange(), true);
	}

	@When("^From User Rating section select ratting as \"([^\"]*)\"$")
	public void from_User_Rating_section_select_ratting_as(String userRating) throws Throwable {
		provide_user_rating(userRating);
		Assert.assertEquals(captureAppliedFilters(), "Passed");
	}

	@Then("^Scroll to the (\\d+) no of the hotel from the filtered results and capture it's name$")
	public void scroll_to_the_no_of_the_hotel_from_the_filtered_results_and_capture_it_s_name(int hotelNo) throws Throwable {
		capture_specifiedHotelName_and_click_on_it(hotelNo);
	}
	
	@Then("^Once User in the new window of the selected hotel they need to navigate to the Rooms section of the page$")
	public void once_User_in_the_new_window_of_the_selected_hotel_they_need_to_navigate_to_the_Rooms_section_of_the_page() throws Throwable {
		windowHandler("child");
		log.info("Child window's title: "+BrowserHandler.getDriver().getTitle());
		Reporter.addStepLog("User navigate to the new window with selected hotel room details");
	}
	@Then("^Capture details of first room category and click on SELECT ROOM option$")
	public void capture_details_of_first_room_category_and_click_on_SELECT_ROOM_option() throws Throwable {
		capture_details_of_room_and_select();
	}
	
	@Then("^Fill Traveller Information now as per \"([^\"]*)\" and also select \"([^\"]*)\" and \"([^\"]*)\" as commonly requested option$")
	public void fill_Traveller_Information_now_as_per_and_also_select_and_as_commonly_requested_option(String testDataSet, String firstOpt, String secondOpt) throws Throwable {
		fill_traveller_information_and_select_commonly_requested_options(testDataSet,firstOpt,secondOpt);
	}

	@Then("^Uncheck donation option$")
	public void uncheck_donation_option() throws Throwable {
		uncheck_donation_option_from_page();
	}
	

	@Then("^Click on Book Now button$")
	public void click_on_Book_Now_button() throws Throwable {
		click_on_book_now_button();
	}
	
	@Then("^Validate the details from the booking summary$")
	public void validate_the_details_from_the_booking_summary() throws Throwable {
		validate_summary_details("DataSet_MMT_BusinessScenario");
		Reporter.addStepLog("Comparision is completed");
	}

}
