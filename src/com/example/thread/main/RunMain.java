package com.example.thread.main;

import java.awt.Desktop;
import java.io.File;

public class RunMain {
	public static void main(String[] args) {
		
		String bat = "mls_client_run.bat";
		int delay = 10000;
		int count = 99999999;
		
		for(int i = 0; i < args.length; i++) {
			
			String[] splitOption = args[i].split("=");
			String option = splitOption[0];
			String value = splitOption[1];
			
			switch( option ) {
			case "bat" : 
				bat = value;
				break;
			case "delay" : 
				delay = Integer.valueOf(value);
				break;
			case "count" : 
				count = Integer.valueOf(value);
				break;
			}
		}
		
		StringBuilder pathBuild = new StringBuilder();
		pathBuild.append( System.getProperty("user.dir") );
		pathBuild.append("\\");
		pathBuild.append(bat);
		
		while( (count--) > 0) {
		
			try {
				File file = new File( pathBuild.toString() ); 
				Desktop.getDesktop().open(file); 
				
				System.out.println("start (remain" + count +") wait " + delay);

				Thread.sleep(delay);
			} catch (Exception e) {
				System.err.println(e);
			}
		
		}//end while
		System.out.println("jobs done");

	}
}
