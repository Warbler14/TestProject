package com.example.thread.atomic.test01.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementThread extends Thread{
	
	private AtomicInteger count;
	private long runCount;
	
	public IncrementThread( AtomicInteger count, final long runCount) {
		this.count = count;
		this.runCount = runCount;;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName());
		
		while( runCount-- > 0 ) {
			count.incrementAndGet();
		}
	}
}
