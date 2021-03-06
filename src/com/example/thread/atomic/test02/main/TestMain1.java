package com.example.thread.atomic.test02.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.thread.atomic.test02.thread1.DecrementThread;
import com.example.thread.atomic.test02.thread1.IncrementThread;

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
		Map<String, AtomicInteger> dataMap = new HashMap<>();
		dataMap.put("0", new AtomicInteger (0));
		dataMap.put("1", new AtomicInteger (0));
		dataMap.put("2", new AtomicInteger (0));
		dataMap.put("3", new AtomicInteger (0));
		dataMap.put("4", new AtomicInteger (0));
		
		String incrementMessage;
		String decrementMessage;
		
		IncrementThreadRunner( dataMap );
		incrementMessage = readData( dataMap );
		
		DecrementThreadRunner( dataMap );
		decrementMessage = readData( dataMap );
		
		System.out.println( incrementMessage );
		System.out.println( decrementMessage );
	}
	
	private static String readData( Map<String, AtomicInteger> dataMap ) {
		StringBuilder result = new StringBuilder();
		Iterator<String> itr = dataMap.keySet().iterator();
		while( itr.hasNext() ) {
			String key = itr.next();
			
			result.append( key ).append(":").append( dataMap.get(key).get() ).append(",");
		}
		
		return result.toString();
	}
	
	private static void IncrementThreadRunner( Map<String, AtomicInteger> dataMap ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			
			Iterator<String> itr = dataMap.keySet().iterator();
			while( itr.hasNext() ) {
				String key = itr.next();
				
				executor.submit( new IncrementThread( dataMap.get(key), THREAD_RUN ) );
			}
			
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
	
	private static void DecrementThreadRunner( Map<String, AtomicInteger> dataMap ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			
			Iterator<String> itr = dataMap.keySet().iterator();
			while( itr.hasNext() ) {
				String key = itr.next();
				
				executor.submit( new DecrementThread( dataMap.get(key), THREAD_RUN ) );
			}
			
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
}
