/*
 * @Noor Mohammad Shiblee
 */
package com.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport {
	
	private static ExtentReports extentReport = null;
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	//generate report based on the execution time stamp.
	static String extentReportPath = "/"+timeStamp+"_TestExecutionReport.html";
	
	// Exists only to defeat instantiation.
	private ExtentReport() {		
	}
	
	/*
	 * get the extent report instance
	 */
	public static ExtentReports getInstance() {
		
		if(extentReport == null) {
			extentReport = new ExtentReports(AppConstant.EXTENTREPORT_PATH+extentReportPath, false);
		}
		return extentReport;
	}
}
