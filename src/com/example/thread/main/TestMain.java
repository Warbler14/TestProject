package com.example.thread.main;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.thread.execute.InsertTask;
import com.example.thread.execute.RemoveTask;
import com.example.thread.model.DataMessageStore;
import com.example.thread.model.Message;
import com.example.thread.worker.RunloopThread;

public class TestMain {
	private final static int THREAD_COUNT = 100;
	private final static int THREAD_RUN = 10000;
	
	public static void main(String[] args) {
		DataMessageStore map = new DataMessageStore();
		
//		map.putIfAbsence("test", new Message("0"));
		
		
		IncrementThreadRunner(map);
		
		long Start = System.currentTimeMillis();
//		Map<String, Message> copyMap = map.cloneCopy();
		Map<String, Message> copyMap = map.copy();
		System.out.println(">>" + (System.currentTimeMillis() - Start));
		
		RemoveThreadRunner(map);
		
		System.out.println( map.size() );
		System.out.println( copyMap.size() );
		
		for (Map.Entry<String, Message> entry : copyMap.entrySet()) {
			System.out.println( entry.getKey() + "=" + entry.getValue().getMessage());
		}
		
		
	}
	

	private static void IncrementThreadRunner( DataMessageStore map ) {		
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		executor.submit( new RunloopThread( new InsertTask(map),  THREAD_RUN ) );
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}

//		System.out.println(map.size());
	}
	
	private static void RemoveThreadRunner( DataMessageStore map ) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		executor.submit( new RunloopThread( new RemoveTask(map),  THREAD_RUN ) );		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}

//		System.out.println(map.size());
	}
}
