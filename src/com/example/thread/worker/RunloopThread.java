package com.example.thread.worker;

import com.example.thread.execute.Task;

public class RunloopThread extends Thread{
	
	private Task task;
	private long runCount;
	
	public RunloopThread( Task task, final long runCount) {
		this.task = task;
		this.runCount = runCount;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName() + task.getClass().getSimpleName()+" Start" );
		
		while( runCount-- > 0 ) {
			
			task.execute();
			
		}
		
		System.out.println(this.getName() + task.getClass().getSimpleName() + " Finished" );
	}
}
