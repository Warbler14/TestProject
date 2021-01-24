package com.example.thread.atomic.test03.atomic_bank;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerRecord implements Record{

	private AtomicInteger value;
	
	public AtomicIntegerRecord() {
		value =  new AtomicInteger(0);
	}

	public AtomicInteger getValue() {
		return value;
	}

	public void setValue(AtomicInteger value) {
		this.value = value;
	}
	
}
