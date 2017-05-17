/*
 * @Noor Mohammad Shiblee
 */

package com.api.util;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class TestHelper {
	
	public Properties prop = null;
	
	/*
	 * get the json object for assertion or any other purpose
	 * @param String JSONString
	 * return JSON object
	 */
	public JSONObject getJSONObject(String JSONString){
		
		JSONObject jsonObject = null;
		try {
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(JSONString);
			jsonObject = (JSONObject) obj;
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem found in getJSONObject() method");
			e.printStackTrace();
		}		
		return jsonObject;
	}
	
	/*
	 * Get JSON Key value
	 * @param String JSONObject
	 * @param Sting  JSONKey
	 * return the json key value
	 */
	public String getJSONKeyValue(String JSONString, String JSONKey){
		
		String keyValue = null;
		try {
			
			JSONObject jsonObject = getJSONObject(JSONString);
	        keyValue = (String) jsonObject.get(JSONKey);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem found in getJSONKeyValue() method");
			e.printStackTrace();
		}		
		return keyValue;
	}
	
	/*
	 * get api response body
	 * @param String parameterName
	 * @param String paramValue
	 * @param String url
	 * return api response body as String
	 */
	public String getAPIResponse(String parameterName, String paramValue, String url){
		
		Response res = null;
		try {
			res = 	RestAssured.given()
					.parameter(parameterName, paramValue)
					.get(url);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("problem found in getAPIResponse() method");
			e.printStackTrace();
		}			
		return res.body().asString();
	}
	
	/*
	 * Get properties from settings.conf file
	 * @param String key
	 * retun the value of the key
	 */
	public String getPropertyValue(String key){
		
		 prop = new Properties();
		 try {
			prop.load(new FileInputStream(AppConstant.SETTING_PATH));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem found in getPropertyValue() method");
			e.printStackTrace();
		}		 
		return prop.getProperty(key); 
	}
	
	/*
	 * generate a md5 hash for a string and return it.
	 * @param String originalText
	 * return the md5 hash of the inputted string.
	 */
	public String generateMD5Hash(String origianalText){

		String md5HashValue = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(origianalText));
			md5HashValue =  String.format("%032x", new BigInteger(1, md5.digest()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem found on generateMD5Hash() method.");
			e.printStackTrace();
		}		
		return md5HashValue;		
	}
}
