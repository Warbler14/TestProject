package com.example.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLConnector2 {

	private static final Logger logger = LoggerFactory.getLogger(URLConnector2.class);

	private static final String USER_AGENT = "Mozilla/5.0";
	private String charsetName = "UTF-8";
	
	private String url;
	private int conTimeout = 5000;
	private int readTimeout = 5000;
		
	 public enum Methods {
		 GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE
	 };
	 
	public URLConnector2(){}

	public URLConnector2(String url) {
		super();
		this.url = url;
	}
	
	public URLConnector2(String url, final String charsetName) {
		super();
		this.url = url;
		this.charsetName = charsetName;
	}

	public URLConnector2(String url, int conTimeout, int readTimeout) {
		super();
		this.url = url;
		this.conTimeout = conTimeout;
		this.readTimeout = readTimeout;
	}

	public StringBuilder sendGET(){
		StringBuilder result = null;
		
		HttpURLConnection con = null;
		InputStream is = null;

		try{
			logger.debug("Call to : " + url );
			URL url = new URL( this.url );
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", charsetName);
			con.setConnectTimeout( conTimeout );
			con.setReadTimeout( readTimeout);

			int responseCode = con.getResponseCode();
			
			logger.debug("Response Code : " + responseCode );
			
			if(responseCode == HttpURLConnection.HTTP_OK){
				is = new BufferedInputStream(con.getInputStream());
				result = readStream( is );
			}

		}catch( IOException ie){
			logger.error( ie.getMessage() );
			result = null;
		}catch( Exception e ){
			logger.error( e.getMessage() );
			result = null;
		}finally {
			try{
				if( is != null ){
					is.close();
				}

			}catch(IOException ioe ){
				logger.error(ioe.getMessage() );
			}
		}

		return result;

	}

	public StringBuilder sendPOST( Map<String, String> map ) {

		StringBuilder result = new StringBuilder();
		String parameter = getParameter(map);
		
		HttpURLConnection con = null;
		InputStream is = null;
		OutputStream writer = null;

		logger.debug("Call to : " +  this.url );
		try{
			URL url = new URL( this.url );
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", charsetName);
			con.setConnectTimeout( conTimeout );
			con.setReadTimeout( readTimeout);	
			con.setDoOutput(true);
			
			writer = con.getOutputStream();
			writer.write(parameter.getBytes());
			writer.flush();
			
			int responseCode = con.getResponseCode();
			logger.debug("Response Code : " + responseCode);
			
			if(responseCode == HttpURLConnection.HTTP_OK){
				is = new BufferedInputStream(con.getInputStream());
				result = readStream( is );
			}

		}catch( IOException ie){
			logger.error( ie.getMessage() );
		}catch( Exception e ){
			logger.error( e.getMessage() );
		}finally {
			try{
				if( is != null ){
					is.close();
				}
				
				if( writer != null ){
					writer.close();
				}
				
				if( con != null ){
					con.disconnect();
				}
			}catch(IOException ioe ){
				logger.error( ioe.getMessage() );
			}
		}
	
		return result;
	}

	public String getParameter(Map<String, String> map) {
		String result = new String();

		if (ObjectUtil.isNotEmpty(map)) {
			StringBuffer buffer = new StringBuffer();
			int count = 0;
			
			Iterator<String> itr = map.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();

				if ((count++) > 0) {
					buffer.append('&');
				}

				buffer.append(key);
				buffer.append('=');
				buffer.append(map.get(key));

			} // end while

			if (logger.isDebugEnabled() && buffer.length() > 0) {
				logger.debug("buffer data : " + buffer.toString());
			}

			result = buffer.toString();
		}

		return result;
	}

	private StringBuilder readStream( InputStream is )throws Exception{

		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charsetName));
		String lineBuffer;

		//logger.debug("===============START==============");
		while ( ( lineBuffer = reader.readLine()) != null){

			result.append(lineBuffer);

			//logger.debug( lineBuffer );

		}//end while
		//logger.debug("===============FINISH==============");
		
		if( reader != null ){
			reader.close();
		}
		
		return result;

	}


//	public boolean reConCallAPI( String resultDatatype , int urlConnectionRetryCount , int connectionDelay){
//
//		boolean status = false;
//
//		try{
//			status = callAPI( resultDatatype );
//		}catch(Exception e){
//			LOGGER.info("URL Connect trial remain: " + (urlConnectionRetryCount-1) + ", wait " + connectionDelay + " sec");
//
//			status = false;
//		}
//
//		if( !status && urlConnectionRetryCount > 1 ){
//
//			try {
//	    	    Thread.sleep( connectionDelay * 1000 );
//	    	} catch (InterruptedException ie) {
//	    		LOGGER.error( ie.getMessage() );
//	    	}
//
//			status = reConCallAPI( resultDatatype , (urlConnectionRetryCount-1) , connectionDelay );	//Recursion reConCallAPI(..)
//
//		}else if( urlConnectionRetryCount <= 1 && !status){
//
//			LOGGER.info("URL connection Fail!!");
//
//		}
//
//		return status;
//	}
}
