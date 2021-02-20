package com.example.test;

import jdk.nashorn.internal.ir.BinaryNode;

public class CheckClassloaders {

	public static void main(String[] args) {
		
		printClassloaders();
		
		
	}
	
	private static void printClassloaders(){
	    System.out.println("Object's classloader: " + Object.class.getClassLoader());
	    System.out.println("BinaryNode's classloader: " + BinaryNode.class.getClassLoader());
	    //System.out.println("ApplicationContext's classloader" + ApplicationContext.class.getClassLoader());
	}
}
