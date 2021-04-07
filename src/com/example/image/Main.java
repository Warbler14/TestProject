package com.example.image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.example.util.PathUtil;

public class Main {

	public static void main(String args[]){
		
		new Main().execute();
		
	}
	
	public void execute() {
		
		String dirPath = "";
		try {
			dirPath = PathUtil.getAbsolutePath(this) + "/data";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		SpriteImage si = new SpriteImage( 5, 5, 100, 100);
		
		BufferedImage splitImages[] = si.getSpliteImage(dirPath + "/test01.jpg");
		
		if( splitImages != null ){
			for( int i = 0 ; i<splitImages.length ; i++ ){
				try{
					//Graphics2D g =  splitImages[i].createGraphics();
					String fileName = dirPath + "/g"+ i +".jpg";
					
					mkDir(dirPath);				
					File file = new File( fileName );  
					ImageIO.write(splitImages[i], "jpeg", file);  
					
					printResult( file.exists(), "[" + i + "] " + fileName );
					
				}catch(Exception e){
					System.out.println(">>");
				}
			}
			
		}else{
			printResult( false, "" );
		}
		
		
	}
	
	
	
	public static void mkDir(String dirPath){
		File dir = new File(dirPath);		
		if(!dir.exists()) dir.mkdirs();
	}
	
	public static void printResult( boolean check, String message ){
		
		if(check){
			System.out.println(  message + " create OK");
		}else{
			System.out.println(  message + " create FAIL");
		}
	}
}
