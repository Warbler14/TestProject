package com.example.thread.atomic.test01.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class DecrementThread extends Thread{
	
	private AtomicInteger count;
	private long runCount;
	
	public DecrementThread( AtomicInteger count, final long runCount) {
		this.count = count;
		this.runCount = runCount;;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName());
		
		while( runCount-- > 0 ) {
			count.decrementAndGet();
		}
	}
}
