package com.example.console;

import java.util.Collections;

public class TestConsole {

	//javac TestConsole.java
	//java com.example.console.TestConsoe
	
	public static void main(String[] args) throws Exception{
//		System.out.print("Everything on the console will cleared");
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        
//      int count = 1; 
//      System.out.print(String.format("\033[%dA",count)); // Move up
//      System.out.print("\033[2K"); // Erase line content
		
		
		
		
//		String message = "hello";
//        System.out.print(message);
//        Thread.sleep(3000); // Just to give the user a chance to see "hello".        
//        reprint(message, "world");
        
        
		countPrint(10000, 100);
        
        
        
        
        System.out.println("");
                
	}
	
	public static void countPrint(final int count, final int sleep) {
		
		
		String previous = "";
		String next = "";
		
		for (int idx = 0; idx < count; idx++) {
			
			next = String.format("%05d", idx);
			
			try {
				Thread.sleep(sleep);
			} catch (Exception e) {}
			
			reprint(previous, next);
			
			previous = next;
		}
		
		
	}
	
	
	public static void reprint(final String previous, final String next) {
		if(previous != null && previous.length() > 0) {
			System.out.print(String.join("", Collections.nCopies(previous.length(), "\b")));
		}
		
		System.out.print(next);
	}
}
