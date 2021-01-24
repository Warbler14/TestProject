package com.example.math.main;

public class Math001 {
	final static int DIGIT = 3;
	
	public static void main(String args[]){
		
		double deg = 45, rad;
		
		if( deg < 90 && deg > 0){
			rad = Math.toRadians(deg);
			
			double s= Math.sin(rad);
			double c= Math.cos(rad);
			double t= Math.tan(rad);
			
			System.out.println("radius : " + rad);
			System.out.println("sin : " + s);
			System.out.println("cos : " + c);
			System.out.println("tan : " + t);
			
			
			double x = 1.0;
			double [] dataArr = get_xYR( x, rad );
			print_XYR( dataArr, DIGIT );
			dataArr = get_XyR( dataArr[1], rad );
			print_XYR( dataArr, DIGIT );
			dataArr = get_XYr( dataArr[2], rad );
			print_XYR( dataArr, DIGIT );
			
		}else{
			System.out.println( "out of degree : " + deg );
		}
		
	}
	// sinθ = y/r, cosθ = x/r, tanθ = y/x
	private static double[] get_xYR( double x, double rad ){
		double [] returnData = {0.0,0.0,0.0};
		
		returnData[0] = x;
		returnData[1] = x * Math.tan(rad);
		returnData[2] = x / Math.cos(rad);
		
		return returnData;
	}
	
	private static double[] get_XyR( double y, double rad ){
		double [] returnData = {0.0,0.0,0.0};
		
		returnData[0] = y / Math.tan(rad);
		returnData[1] = y;
		returnData[2] = y / Math.sin(rad);
		
		return returnData;
	}
	
	private static double[] get_XYr( double r, double rad ){
		double [] returnData = {0.0,0.0,0.0};
		
		returnData[0] = r * Math.cos(rad);
		returnData[1] = r * Math.sin(rad);
		returnData[2] = r;
		
		return returnData;
	}
	
	private static void print_XYR( double [] data , int digit ){
		System.out.printf("X : %."+ digit+ "f, Y : %."+ digit+ "f, R : %."+ digit+ "f \n", data[0], data[1], data[2]);
	}
}
