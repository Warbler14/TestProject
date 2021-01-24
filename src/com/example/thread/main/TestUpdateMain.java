package com.example.thread.main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.thread.execute.ReadTask;
import com.example.thread.execute.UpdateTask;
import com.example.thread.model.DataMessageStore;
import com.example.thread.model.Message;
import com.example.thread.worker.RunloopWaitingThread;

public class TestUpdateMain {
	private final static int THREAD_COUNT = 100;
	private final static int THREAD_RUN = 10000;
	private final static int MAP_SIZE = 100;
	
	public static void main(String[] args) {
		DataMessageStore map = new DataMessageStore();
		
		initMap(map);
		
		ThreadRunner(map);
		
		
		
	}
	
	private static void initMap( DataMessageStore map ) {
		for (int idx = 0; idx < MAP_SIZE; idx++) {
			map.put(String.valueOf(idx), new Message());
		}
	}
	

	private static void ThreadRunner( DataMessageStore map) {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		Map<String, Boolean> flagMap = new HashMap<>();
		flagMap.put("isRun", false);
		
		for (int idx = 0; idx < MAP_SIZE; idx++) {
			executor.submit( new RunloopWaitingThread( new UpdateTask(map, String.valueOf(idx)),  THREAD_RUN, flagMap ) );
		}
		executor.submit( new RunloopWaitingThread( new ReadTask(map),  THREAD_RUN, flagMap ) );
		
		
		flagMap.put("isRun", true);
		
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}

//		System.out.println(map.size());
	}
//	
//	private static void RemoveThreadRunner( DataMessageStore map ) {
//		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
//		
//		
//		executor.submit( new RunloopThread( new RemoveTask(map),  THREAD_RUN ) );
//		
//		executor.shutdown();
//		
//		try {
//			executor.awaitTermination(1, TimeUnit.MINUTES);
//		} catch (InterruptedException e) {
//		}
//
////		System.out.println(map.size());
//	}
}
