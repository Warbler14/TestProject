package com.example.thread.atomic.test03.thread;

import com.example.thread.atomic.test03.atomic_bank.DataBank;

public class IncrementThread extends Thread{
	
	private DataBank bank;
	private long runCount;
	
	public IncrementThread( DataBank bank, final long runCount) {
		this.bank = bank;
		this.runCount = runCount;;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName());
		
		while( runCount-- > 0 ) {
			
			String[] keyArr = bank.getKeyArr();
			for (int i = 0; i < keyArr.length; i++) {
				bank.increment(keyArr[i]);
			}
		}
	}
}
