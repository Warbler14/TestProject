package com.example.secure_random;

import java.security.SecureRandom;

public class TestMain {
	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println("----------------------------------");
		run("1");
		run("10");
		run("100");
		run("a");
		run("ab");
		run("abc");
		run("seed");
		run("seed");
		run("longlonglonglongseed");	//5905418479965907147 - 5905418479965907147
		
	}
	
	private static void run(final String seedStr) {
		byte[] seedBytes = seedStr.getBytes();
		SecureRandom secureRandom = new SecureRandom(seedBytes);
		
		System.out.println(secureRandom.getAlgorithm());
		System.out.println(secureRandom.nextLong());
		System.out.println(secureRandom.nextInt());
		
	}
}
