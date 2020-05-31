# HeadSpin QA HackaThon
This repository has been created to store and share project code as part QA hackathon

## Project Description
  This project contains an automated test script of MakeMyTrip business scenario flow.
  
## Tools and Technology
  Project is created with:
* Selenium with Java for UI automation and writting business logics
* Maven for build control
* Applied BDD approach with Cucumber
* Cucumber extent report to generate test reports
* TestNG framework used as runner
* Log4j to generate logs 
* Git for version control

## Project Location
 Please clone https://github.com/iamKrishnendu/mindspin.git using  ```git clone ``` command using git bash to get this project in your local

## Project Dependensies
 * JDK should be installed (Compatible with JDK 8)
 * Maven should be install (create on version 3.6.2)
 * Developed on Windows 10 platform 
 * To check installed jdk version run command ```java --version``` from command prompt
 * To check maven version run command ```mvn --version ``` from command prompt
 
 ## Project Setup
 * First take a clone of this repository (repository link and command mentioned above)
 * Go to the project location and launch command prompt (for windows) 
 * Type command ```mvn install``` 
 
 ## Reporting
   Once execution is completed, go to root directory and open a folder called ```Test-Report``` and open the latest folder created with current date and time instance
  
 ## Test Artifacts
   Captured details during the execution can be find in a file called ```data.json``` which will be created on run time and should be placed under ```target-->cucumber-report-->data.json```
   
## Test Data
   Maintaining an excel file called ```DataMappingSheet.xlsx``` which is available under ```Test-Data``` folder. Here in this file multiple data set can be added, make sure that ```TestDataSet``` name should be unique all the time.
   
 ## Screenshot
   In case test execution is failed, screenshot of the failed step will be available under ```root directory --> Screenshot``` and it can be found with the test report it self. At the last failed step there should be an image icon by clicking on that icon failed step's image can be visible.
   
## Configuration File
 In the root folder one property file called ```config.properties``` is avialble. In case a set of configurations like browser name, data sheet path, user email address for login needs to be modified then modify there it self. Framework will take it up from there.
 *Note: Currently framework having support with chrome, headless-chrome, mozila firefox and IE browser support. But Chrome is preferable to use

## Locator repository
 As this project is developed by following Page Object Model design pattern hence all the locators are maintained in separate folder called ```com.test.PageObjects```. For any type of changes or modification specific class file can be refered. 
 
## Random Issues 
 As MakeMyTrip is being used by so many people globally so it can be possible that during first execution script might fail for sync issue though all the standared methods has been followed to handle this but in case this situation, give a second try.
