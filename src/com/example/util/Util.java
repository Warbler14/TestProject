package com.example.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Util {

	public StringBuffer sendPost( String urls, HashMap<String, String> map ) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		Set<String> set = map.keySet();
		Iterator<String> itr = set.iterator();
		int count = 0;
		while( itr.hasNext() ){
			 String key = itr.next();
			 
			 if( (count++) > 0 ){
				 buffer.append(  '&'  );
			 }
			 
			buffer.append( key );
			buffer.append( '=' );
			buffer.append( map.get(key) );
			
		}//end while
		
		/*
		if( buffer == null || buffer.length() <= 0  ){
			System.out.println("data is empty");
			return null;
		}
		*/
		
		System.out.println( "buffer data : " + buffer.toString()  );
		StringBuffer response = new StringBuffer();
		 
		System.out.println("Call to : " + urls  );
		URL url = new URL( urls );
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		if(conn != null){
			OutputStream writer = null;
			BufferedReader reader = null;
			try{
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Accept-Language", "UTF-8");
		 
				conn.setDoOutput(true);
				writer = conn.getOutputStream();
				writer.write(  (buffer.toString()).getBytes()  );
				writer.flush();
		 
				int responseCode = conn.getResponseCode();
				System.out.println("Response Code : " + responseCode);
		 
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
		 
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
				
			}catch( IOException ie){
				System.out.println(  ie.getMessage() );
			}catch( Exception e ){
				System.out.println(  e.getMessage() );
			}finally {
				try{
					if( reader != null ){
						reader.close();
					}
					if( writer != null ){
						writer.close();
					}
					System.out.println( "URL connection close");
					
				}catch(IOException ioe ){
					System.out.println(  ioe.getMessage() );
				}
			}
		}
		return response;
	}
}
