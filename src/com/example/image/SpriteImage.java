package com.example.image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
public class SpriteImage {
    int row,col,width,height; //이미지의 행값,열값,가로,세로
    BufferedImage bigImg;     //자를 이미지
    
    public SpriteImage(int row,int col,int width,int height) {
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
    }
    
    public BufferedImage[] getSpliteImage( String fileName ){
    	//자른 이미지를 담을수 있는 배열 생성[행 * 열] = 갯수
    	BufferedImage splitImages[] = null;
    	
        try {
        	
        	File file = new File( fileName );
        	
        	if( file.exists() ){
        		splitImages = new BufferedImage[row * col];

        		//ImageIO 클래스를 이용하여 이미지를 불러온다.
                bigImg = ImageIO.read( file );
                
                for(int i = 0 ; i < row ; i++){ 
                	for(int j = 0 ; j < col ; j++){
                		//getSubimage 메소드를 이용하여 자른다.
                		//앞파라미터 부터 시작 x,y ,가로,세로
                		splitImages[i*col + j] = bigImg.getSubimage(j* width, i * height, width, height);
                	}
                }
        	} else {
        		System.out.println("no such file or directory:" + fileName);
        	}
        	            
        } catch (Exception e) {
            e.printStackTrace();
            splitImages = null;
        }
        
        //잘라진 이미지 배열을 반환.
        return splitImages;
    }
     
 
}