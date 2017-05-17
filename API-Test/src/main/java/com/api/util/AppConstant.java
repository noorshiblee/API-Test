/*
 * Noor Mohammad Shiblee
 */
package com.api.util;

/*
 * used to get the path of necessary resources 
 */
public class AppConstant {
	
	// prevents instantiation
	private AppConstant () { 
		
	} 
	
	public static final String CURRENT_DIR = System.getProperty("user.dir");
	public static final String APP_CONFIG = CURRENT_DIR+"/config/";
	public static final String SETTING_PATH = CURRENT_DIR+"/config/settings.conf";
	public static final String EXTENTREPORT_PATH = CURRENT_DIR + "/TestReport";
}
