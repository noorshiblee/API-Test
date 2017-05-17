/*
 * @Noor Mohammad Shiblee
 */
package com.api.util;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class TestStarter {

	/*
	 * will start test execution from API-Test > config > testng.xml
	 */
	public void startTest(){
		
		// Create object of TestNG Class
		TestNG runner=new TestNG();
		
		// Create a list of String 
		List<String> suitefiles= new ArrayList<String>();
		
		// Add xml file which you have to execute
		suitefiles.add(AppConstant.APP_CONFIG+"testng.xml");
	
		// now set xml file for execution
		runner.setTestSuites(suitefiles); 
		
		// finally execute the runner using run method
		runner.run();
	}
}
