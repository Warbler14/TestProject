package com.example.test;

import java.util.List;
import java.util.ArrayList;

public class DumpTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		for(int idx = 0 ; idx < 100 ; idx++ ) {
			list.add("String" + idx);
			
			
			
			showMem(0);
			if(idx % 10 == 0) {
				Runtime.getRuntime().gc();
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void showMem(int type) {
		
		Runtime runtime = Runtime.getRuntime();
		
		long heapSize = getSize(type, runtime.totalMemory());
		long heapMaxSize = getSize(type, runtime.maxMemory());
		long heapFreeSize = getSize(type, runtime.freeMemory());
		
		
		System.out.println(
				"heapMaxSize : " + heapMaxSize 
				+ ", heapSize : " + heapSize 
				+ ", heapFreeSize : "  + heapFreeSize);
		
		
	}
	
	public static long getSize(int type, long value) {
		long rst = value;
		for(int idx=0 ; idx<type ; idx++) {
			rst /= 1024;
		}
		return rst;
	}
}
