package com.api.util;

import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*
 *  generate the test execution summary and perform common action on test execution.
 */
public class CustomReporter implements ITestListener, IReporter{

	static ExtentReports report = ExtentReport.getInstance();
	ExtentTest extentTest;
	String suiteName = null;
	String testName = null;
	
	/*
	 * (non-Javadoc)
	 * @see org.testng.IReporter#generateReport(java.util.List, java.util.List, java.lang.String)
	 */
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		
		String suiteWiseSummaryReport = null;
    	String summaryReport = null;
    	int totalTCPassed = 0;
	    int totalTCSkipped = 0;
	    int totalTCFailed = 0;
	    int totalTCRun = 0;
    	
    	/* Iterating over each suite included in the test */
        for (ISuite suite : suites)
        {
		        /* get the suite name */
		        String suiteName = suite.getName();
			    /* Getting the results for the suite */
			    Map<String, ISuiteResult> suiteResults = suite.getResults();
			    
			    suiteWiseSummaryReport += "\nTests suite " + suiteName;
			    suiteWiseSummaryReport += "\n===============================================================\n";
			    
			    String testcaseName = "";
			    for (Map.Entry<String, ISuiteResult> sr: suiteResults.entrySet())
			    {	
			    	ITestContext tc = sr.getValue().getTestContext();
			    	testcaseName = tc.getName();
			    			
			    	int testWisePassed = tc.getPassedTests().getAllResults().size();
			    	int testWiseSKipped = tc.getSkippedTests().getAllResults().size();
			    	int testWiseFailed = tc.getFailedTests().getAllResults().size();
			    	int testWiseTotal = tc.getAllTestMethods().length;
			    	
			    	suiteWiseSummaryReport +="\nSummary for Test: "+testcaseName+"\n";
			    	suiteWiseSummaryReport +="-----------------------------------------------";
			    	suiteWiseSummaryReport += "\nPassed - " + testWisePassed;
			    	suiteWiseSummaryReport += "\nSkipped - " + testWiseSKipped;
			    	suiteWiseSummaryReport += "\nFailed - " + testWiseFailed;
			    	suiteWiseSummaryReport += "\nTotal test case run - " + testWiseTotal+"\n";
			    	suiteWiseSummaryReport +="-----------------------------------------------";
			        
			        //counts category wise passed, skipped, failed & total test cases
			    	totalTCPassed += testWisePassed;
			    	totalTCSkipped += testWiseSKipped;
			    	totalTCFailed += testWiseFailed;
			    	totalTCRun += testWiseTotal;
			        
			     }
			
			summaryReport += "\n====================================\n";
		    summaryReport += "Summary for test execution.";
		    summaryReport += "\n------------------------------------\n";
		    summaryReport += "Total Passed: "+totalTCPassed;
		    summaryReport += "\nTotal Skipped: "+totalTCSkipped;
		    summaryReport += "\nTotal Failed: "+totalTCFailed;
		    summaryReport += "\nTotal Run: "+totalTCRun;
		    summaryReport += "\n====================================\n";
        }
        System.out.println(summaryReport);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext testContext) {
		
		System.out.println("\n\t \t ==== Start Executing "+testContext.getName()+" category =====\n");
		suiteName = testContext.getName();		
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("---- start executing testcase: "+result.getMethod().getMethodName());
		String testcaseName = result.getMethod().getMethodName();
		extentTest=report.startTest(testcaseName).assignCategory(suiteName);
		extentTest.log(LogStatus.INFO, "TestCase : Started");
	}

	/* 
	 * this method is invoked if the test case is success.
	 * @param testresult
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		
		String testcaseName = tr.getMethod().getMethodName();
		System.out.println("\tTestCase : "+testcaseName+" is Passed\n");
		
		extentTest.log(LogStatus.PASS, "TestCase : "+testcaseName+" is Passed");
		extentTest.log(LogStatus.INFO, "TestCase : Finished");
		report.endTest(extentTest);
		report.flush();
	}
	
	/* 
	 * this method is invoked if the test case is failed.
	 * @param testresult
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		String testcaseName = tr.getMethod().getMethodName();
		System.out.println("\t*****TestCase : "+testcaseName+" is Failed.*****\n");
	
		extentTest.log(LogStatus.FAIL, "TestCase : "+testcaseName+" is Failed");
		extentTest.log(LogStatus.ERROR, "Failure Reason: <br>"+tr.getThrowable().toString());		
		extentTest.log(LogStatus.INFO, "TestCase : Finished");
		report.endTest(extentTest);
		report.flush();
	}
	 
	/* 
	 * this method is invoked if the test case is skipped.
	 * @param testresult
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		
		String testcaseName = tr.getMethod().getMethodName();
		System.out.println("\tTestCase : "+testcaseName+" is skipped");
		
		extentTest.log(LogStatus.SKIP, "TestCase : "+testcaseName+" is skipped\n");
		extentTest.log(LogStatus.INFO, "TestCase : Finished");
		report.endTest(extentTest);
		report.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("====================================\n");
	}
}
