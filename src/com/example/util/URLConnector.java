package com.example.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLConnector {

	private static final Logger logger = LoggerFactory.getLogger(URLConnector.class);

	private static final String USER_AGENT = "Mozilla/5.0";
	private String charsetName = "UTF-8";
	
	private String url;
	private int conTimeout = 5000;
	private int readTimeout = 5000;
			
	public enum Methods {
		 GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE
	 };
	
	public URLConnector(Builder builder) {
		this.charsetName = builder.charsetName;
		this.url = builder.url;
		this.conTimeout = builder.conTimeout;
		this.readTimeout = builder.readTimeout;
	}

	private HttpURLConnection openHttpURLConnection(Methods methods, Map<String, String> map) {
		HttpURLConnection con = null;
		
		try{
			if(logger.isDebugEnabled()) {
				logger.debug("Call to : " + url );
			}
			URL url = new URL( this.url );
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod(methods.toString());
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", charsetName);
			con.setConnectTimeout( conTimeout );
			con.setReadTimeout( readTimeout);
			
			switch(methods) {
			case DELETE:
				break;
			case GET:
				break;
			case HEAD:
				break;
			case OPTIONS:
				break;
			case POST:
				String parameter = getParameter(map);
				con.setDoOutput(true);
				OutputStream writer = con.getOutputStream();
				if( writer != null ){
					writer.write(parameter.getBytes());
					writer.flush();
					writer.close();
				}
				break;
			case PUT:
				break;
			case TRACE:
				break;
			default:
				break;
			}
		
		}catch( Exception e ){
			logger.error( e.getMessage() );
		}finally {
			try{
				if( con != null ){
					con.disconnect();
				}				
			}catch(Exception ioe ){
				logger.error(ioe.getMessage() );
			}
		}
		
		return con;
	}
	
	private StringBuilder readHttpURLConnection(HttpURLConnection con) {
		if(con == null) {
			return null;
		}
		
		StringBuilder result = null;		
		InputStream is = null;

		try{
			
			int responseCode = con.getResponseCode();
			
			if(logger.isDebugEnabled()) {
				logger.debug("Response Code : " + responseCode );
			}
			
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
	
	public StringBuilder sendGETwithTimeCheck(){
		long start = System.currentTimeMillis();
		StringBuilder  result = sendGET();
		logger.debug("duration : " + (System.currentTimeMillis() - start));
		return result;
	}
	
	public StringBuilder sendPOSTwithTimeCheck(Map<String, String> map){
		long start = System.currentTimeMillis();
		StringBuilder  result = sendPOST(map);
		logger.debug("duration : " + (System.currentTimeMillis() - start));
		return result;
	}
	
	public StringBuilder sendGET(){		
		HttpURLConnection con = openHttpURLConnection(Methods.GET, null);
		return readHttpURLConnection(con);
	}
	
	public StringBuilder sendPOST(Map<String, String> map) {
		HttpURLConnection con = openHttpURLConnection(Methods.POST, map);
		return readHttpURLConnection(con);
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

	public static class Builder {
		private String charsetName = "UTF-8";		
		private String url;
		private int conTimeout = 5000;
		private int readTimeout = 5000;
		
		public Builder(String url) {
			this.url = url;
		}
		
		public Builder charsetName(String val) {
			if(val == null || val.length() <= 0) {
				return this;
			}
			
			if(Charset.isSupported(val)) {
				this.charsetName = val;
			}
			
			return this;
		}
		
		public Builder conTimeout(int val) {
			if(val > 0) {
				this.conTimeout = val;
			}
			return this;
		}
		
		public Builder readTimeout(int val) {
			if(val > 0) {
				this.readTimeout = val;
			}
			return this;
		}
		
		public URLConnector build() {
			return new URLConnector(this);
		}
	}
	
	
	public StringBuilder reConCallAPI(Methods methods, Map<String, String> map, int urlConnectionRetryCount , int connectionDelay){

		StringBuilder result = null;

	
			switch(methods) {
			case DELETE:
				break;
			case GET:
				result = sendGET();
				break;
			case HEAD:
				break;
			case OPTIONS:
				break;
			case POST:
				result = sendPOST(map);
				break;
			case PUT:
				break;
			case TRACE:
				break;
			default:
				break;
			}
					
			logger.info("URL Connect trial remain: " + (urlConnectionRetryCount-1) + ", wait " + connectionDelay + " msec");
		
		if (result == null && urlConnectionRetryCount > 1) {

			try {
				Thread.sleep(connectionDelay);
			} catch (InterruptedException ie) {
				logger.error(ie.getMessage());
			}

			result = reConCallAPI(methods, map, (urlConnectionRetryCount - 1), connectionDelay); // Recursion reConCallAPI(..)

		} else if (urlConnectionRetryCount <= 1) {
			logger.info("URL connection Fail!!");
		}

		return result;
	}
}
