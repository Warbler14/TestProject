package com.example.thread.atomic.test02.main;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.thread.atomic.test02.thread2.DecrementThread;
import com.example.thread.atomic.test02.thread2.IncrementThread;


public class TestMain2 {
	
	private final static int THREAD_COUNT = 1000;
	private final static int THREAD_RUN = 10000;
	
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
//		Map<String, AtomicInteger> dataMap = new HashMap<>();
		Map<String, AtomicInteger> dataMap = new ConcurrentHashMap<>();
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
			executor.submit( new IncrementThread( dataMap, THREAD_RUN ) );
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
			executor.submit( new DecrementThread( dataMap, THREAD_RUN ) );
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
}
