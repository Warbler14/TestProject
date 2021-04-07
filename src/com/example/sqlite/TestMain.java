package com.example.sqlite;

import java.util.HashMap;

public class TestMain {
	public static void main(String args[]) {
		
		//System.out.println( TestMain.class.getResource("/").getPath() );
		System.out.println( System.getProperty("user.dir") );
		
		
		
		DataManager sqlManager = new DataManager(System.getProperty("user.dir") + "/db/test.db");
		
		int seq = 0;
		
		sqlManager.open();
		
		try {
			HashMap<String, String> resultMap = sqlManager.readMeta("user" );
			
			seq = Integer.valueOf( resultMap.get("msgSeq") );
			System.out.println( "seq : " + seq );
			seq++;
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
		
		sqlManager.close();
		
		//------------------
				
		sqlManager.open();
		
		try {
			
			sqlManager.writeMeta( String.valueOf(seq), "user", "title", "data");
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
		
		sqlManager.close();
		
		
	}


}
