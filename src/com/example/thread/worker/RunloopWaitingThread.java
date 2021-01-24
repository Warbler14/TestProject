package com.example.thread.worker;

import java.util.Map;

import com.example.thread.execute.Task;

public class RunloopWaitingThread extends Thread{
	
	private Task task;
	private long runCount;
	private Map<String, Boolean> flagMap;
	
	public RunloopWaitingThread( Task task, final long runCount, Map<String, Boolean> flagMap) {
		this.task = task;
		this.runCount = runCount;
		this.flagMap = flagMap;
	}
	
	@Override
	public void run() {
		while(! flagMap.get("isRun")) {}
		
		System.out.println(this.getName() + task.getClass().getSimpleName()+" Start" );
		
		while( runCount-- > 0 ) {
			
			task.execute();
			
		}
		
		System.out.println(this.getName() + task.getClass().getSimpleName() + " Finished" );
	}
}
