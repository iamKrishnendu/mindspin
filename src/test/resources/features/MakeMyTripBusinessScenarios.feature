#Author: Krishnendu Halder
Feature: To verify hotel booking end to end flow of MakeMyTrip application

  @Test
  Scenario Outline: To validate that user is able to login into MMT web application using valid credentials
    Given Browser should get open and MakeMyTrip should be launched
    When User clicks on the "Login or Create Account" button
    Then Login popup should get visible
    And User needs to enter valid email id and click on Continue button
    And User needs to enter password and click on login button
    And Once login is successfull user should see the firstname on top right corner of the page as mentioned in the "<TestDataSet>"
    When User logged in then click on the "Hotels" option from the header menu and determine that selected option is active now
    And Select location as mentioned in the "<TestDataSet>"
    And Select CheckIn and CheckOut date as mentioned in the "<TestDataSet>"
    And Also select Rooms and Guests details as well from the "<TestDataSet>"
    And Select Travelling for value from the page as mentioned in "<TestDataSet>"
    And Click on Search
    And Set the price range by sliding the slide bar
    And From User Rating section select ratting as "4 & above (Very Good)"
    Then Scroll to the 5 no of the hotel from the filtered results and capture it's name
    And  Once User in the new window of the selected hotel they need to navigate to the Rooms section of the page
    And  Capture details of first room category and click on SELECT ROOM option
    And  Fill Traveller Information now as per "<TestDataSet>" and also select "Early check-in" and "Airport transfer" as commonly requested option
    Then Uncheck donation option
    And  Click on Book Now button
    And  Validate the details from the booking summary 

    Examples: 
      | TestDataSet                  |
      | DataSet_MMT_BusinessScenario |
