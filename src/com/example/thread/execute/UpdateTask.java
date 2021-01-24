package com.example.thread.execute;

import com.example.thread.model.DataMessageStore;

public class UpdateTask implements Task{
	
	private DataMessageStore dataContainer;
	private String key;
	
	public UpdateTask( DataMessageStore dataContainer, String key ) {
		this.dataContainer = dataContainer;
		this.key = key;
	}
	
	@Override
	public void execute() {
		
		String time = String.valueOf(System.currentTimeMillis());
		dataContainer.get(key).setMessage(time);
		
	}


}
