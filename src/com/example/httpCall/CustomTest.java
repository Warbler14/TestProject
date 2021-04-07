package com.example.httpCall;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.util.URLConnector;


public class CustomTest {

	private static final Logger logger = LoggerFactory.getLogger(CustomTest.class);

	
	public static void main(String[] args) {

		myGet();
		
		//testGet();
		
		//testPost();
		
		//testGetRecon();	
		
		//testPostRecon();
	}
	
	private static void myGet() {

		String url = "https://fast.com/ko/";
		
		URLConnector con = new URLConnector.Builder(url).build();

		StringBuilder result = con.sendGET();
		
		//logger.debug(result.toString());
		System.out.println(result.toString());
	}
	
	private static void testGet() {
		int conTimeout = 2000;
		int readTimeout = 2000;

		String url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
		
		URLConnector con = new URLConnector.Builder(url)
											.conTimeout(conTimeout)
											.readTimeout(readTimeout)
											.build();
		
//		StringBuilder result = con.sendGET();
		StringBuilder result = con.sendGETwithTimeCheck();
		
		logger.debug(result.toString());
	}
	
	private static void testPost() {
		int conTimeout = 2000;
		int readTimeout = 2000;
		
		String url = "https://postman-echo.com/post";
		Map<String, String> params = new HashMap<String, String>();
		params.put("foo1", "bar1");
		params.put("foo2", "bar2");
		
		URLConnector con = new URLConnector.Builder(url)
											.conTimeout(conTimeout)
											.readTimeout(readTimeout)
											.build();
		
//		StringBuilder result =  con.sendPOST(params);
		StringBuilder result =  con.sendPOSTwithTimeCheck(params);
		
		logger.debug(result.toString());
	}
	
	private static void testGetRecon() {
		int conTimeout = 100;
		int readTimeout = 100;

		String url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
		
		URLConnector con = new URLConnector.Builder(url)
											.conTimeout(conTimeout)
											.readTimeout(readTimeout)
											.build();
		
//		StringBuilder result = con.sendGET();
		StringBuilder result = con.reConCallAPI(URLConnector.Methods.GET, null, 3, 1000);
		
		try {
			logger.debug(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPostRecon() {
		int conTimeout = 500;
		int readTimeout = 500;
		
		String url = "https://postman-echo.com/post";
		Map<String, String> params = new HashMap<String, String>();
		params.put("foo1", "bar1");
		params.put("foo2", "bar2");
		
		URLConnector con = new URLConnector.Builder(url)
											.conTimeout(conTimeout)
											.readTimeout(readTimeout)
											.build();
		
//		StringBuilder result =  con.sendPOST(params);
		StringBuilder result = con.reConCallAPI(URLConnector.Methods.POST, params, 3, 1000);
		
		try {
			logger.debug(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
