package com.example.httpCall;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class charset {

	public static void main(String[] args) {
		
		if( Charset.isSupported("UTF-20")) {
			System.out.println("OK");
		}else {
			System.out.println("NOT OK");
		}
		
		SortedMap<String, Charset> charSetsMap= Charset.availableCharsets();
		
		Charset charset = charSetsMap.get("utf-8");
		
		if(charset != null) {
			System.out.println( charset.name() );
		}
		
	
		Iterator<String> itr = charSetsMap.keySet().iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			System.out.println( charSetsMap.get(key).name() );
		}

	}
}
