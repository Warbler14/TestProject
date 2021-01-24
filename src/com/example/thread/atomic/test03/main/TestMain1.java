package com.example.thread.atomic.test03.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.thread.atomic.test03.atomic_bank.DataBank;
import com.example.thread.atomic.test03.thread.DecrementThread;
import com.example.thread.atomic.test03.thread.IncrementThread;

public class TestMain1 {
	
	private final static int THREAD_COUNT = 100;
	private final static int THREAD_RUN = 1000;
	
	public static void main(String[] args) {
		/*
		java.util.concurrent.atomic
		AtomicBoolean
		AtomicInteger
		AtomicIntegerArray
		AtomicIntegerFieldUpdater<T>
		AtomicLong
		AtomicLongArray
		AtomicLongFieldUpdate<T>
		AtomicMarkablereference<T>
		AtomicReference<V>
		AtomicReferenceArray<E>
		AtomicReferenceFieldUpdater<T,V>
		AtomicStaampedReference<V>
		*/
		DataBank bank = new DataBank();
		bank.insert("volt0");
		bank.insert("volt1");
		bank.insert("volt2");
		bank.insert("volt3");
		bank.insert("volt4");
		
		String incrementMessage;
		String decrementMessage;
		
		IncrementThreadRunner( bank );
		incrementMessage = readData( bank );
		
		DecrementThreadRunner( bank );
		decrementMessage = readData( bank );
		
		System.out.println( incrementMessage );
		System.out.println( decrementMessage );
	}
	
	private static String readData( DataBank bank ) {
		StringBuilder result = new StringBuilder();
				
		String[] keyArr = bank.getKeyArr();
		for (int i = 0; i < keyArr.length; i++) {
			
			result.append( keyArr[i] ).append(":").append( bank.get(keyArr[i]).getValue() ).append(",");
		}
		
		
		
		return result.toString();
	}
	
	private static void IncrementThreadRunner( DataBank bank ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit( new IncrementThread( bank, THREAD_RUN ) );
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
	
	private static void DecrementThreadRunner( DataBank bank  ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit( new DecrementThread( bank, THREAD_RUN ) );
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
}
