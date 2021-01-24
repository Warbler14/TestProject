package com.example.thread.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.thread.execute.WaitTask;
import com.example.thread.worker.RunloopThread;

public class WaitTestMain {
	private final static int THREAD_COUNT = 100;
	private final static int THREAD_RUN = 1;
	
	public static void main(String[] args) {
		
		infiniteTest(5, TimeUnit.SECONDS, 5);
		
	}
	

	private static void infiniteTest(long timeout, TimeUnit unit, long count) {
		WaitTask task = new WaitTask();
		
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
		executor.submit( new RunloopThread( task,  THREAD_RUN ) );
		executor.shutdown();
		
		try {
			do {
				boolean isExecutorTerminated =  executor.isTerminated() ;
				System.out.println(count + " isExecutorTerminated : "  + isExecutorTerminated );
				
				if(isExecutorTerminated) {
					System.out.println("executor is terminated");
					executor.shutdownNow();
				}
				
				if( count-- <= 0 ) {
					task.exit();
				}
				
			} while(! executor.awaitTermination(timeout, unit));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
