package com.example.thread.atomic.test02.thread2;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DecrementThread extends Thread{
	
	private Map<String, AtomicInteger> dataMap;
	private long runCount;
	
	public DecrementThread( Map<String, AtomicInteger> dataMap, final long runCount) {
		this.dataMap = dataMap;
		this.runCount = runCount;;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName());
		
		while( runCount-- > 0 ) {
			Iterator<String> itr = dataMap.keySet().iterator();
			while( itr.hasNext() ) {
				String key = itr.next();
				
				dataMap.get(key).decrementAndGet();
			}
			
		}
	}
}
