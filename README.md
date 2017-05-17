@Noor Mohammad Shiblee

This is a java Maven project. Rest-Assuered library is used to test api's and json-simple is used to parse data from json object.

#) How To Run:

	- Go to API-Test > src/main/java > com.assignment.main
	- Right click on Main.java
	- Click on Run As > Java Application
	
#) Test Execution Report:

	- API-Test > test-output > emailable-report.html (default testng generated)
	or
	- API-Test > TestReport > *_TestExecutionReport.html (html report generated by ExtentReport.)Reports are 
	generated based on the execution time.

#) TestCases Details:

	Total 7 test cases in API-Test > src/main/java > com.assignment.test > Test_ServiceForMD5Hash class.
		- check the service response with valid parameter
		- check the service response with invalid parameter
		- check the service response without parameter
		- check the service response with blank parameter value.
		- check the that returned md5 hash for blank param value is in valid format.
		- check the original text returned by service.
		- As md5 remains same for same string all time, cross check service returned md5 with java code returned md5.

#) Details about API-Test > config folder:

	-settings.conf contains the url, param and paramValue which is used as input for service test cases.
	-testng.xml contains all the test category and test cases in a proper format to run the test execution.
			
#) Package Details in API-Test > src/main/java:

	-com.assignment.main contains the main class to start the test execution
	-com.assignment.test contains the test class where all test cases are written. One or more test class can be added here.
	-com.assingment.util contains the necessary helper classes.
	
