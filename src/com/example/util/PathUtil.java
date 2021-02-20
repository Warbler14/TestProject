package com.example.util;

import java.io.File;

public class PathUtil {
	
	public static String getJavaClassPath() {
		return System.getProperty("java.class.path");
	}
	
	public static String getRunPath() {
		return System.getProperty("user.dir");
	}
	
		
	public static String getPackagePath(Object obj) throws ClassNotFoundException {
		if(obj == null) {
			throw new ClassNotFoundException();
		}
		
		return obj.getClass().getPackage().getName().replace(".", File.separator);
		
	}
	
	public static String getCanonicalName(Object obj) {
		return obj.getClass().getCanonicalName();
	}
	
	public static String getAbsolutePath(Object obj) throws ClassNotFoundException {
		return new StringBuilder()
				.append(System.getProperty("user.dir"))
				.append("/src/")
				.append(getPackagePath(obj))
				.toString();
	}

}
