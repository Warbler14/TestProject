package com.example.thread.atomic.test01.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.thread.atomic.test01.thread.DecrementThread;
import com.example.thread.atomic.test01.thread.IncrementThread;

public class TestMain {
	
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
		
		AtomicInteger count = new AtomicInteger (0);
		
		IncrementThreadRunner( count );
		System.out.println( count.get() );
		
		DecrementThreadRunner( count );
		System.out.println( count.get() );
	}
	
	private static void IncrementThreadRunner( AtomicInteger count ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit( new IncrementThread( count, THREAD_RUN ) );
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
	
	private static void DecrementThreadRunner( AtomicInteger count ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit( new DecrementThread( count, THREAD_RUN ) );
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
	}
}
