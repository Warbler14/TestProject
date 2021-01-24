package com.example.thread.execute;

import com.example.thread.model.DataMessageStore;
import com.example.thread.model.Message;

public class InsertTask implements Task{
	
	private DataMessageStore dataContainer;
	private long count = 0;
	
	public InsertTask( DataMessageStore dataContainer ) {
		this.dataContainer = dataContainer;
	}
	
	@Override
	public void execute() {
		count++;;
		String cntStr = String.valueOf(count);
		String time = String.valueOf(System.currentTimeMillis());
		dataContainer.putIfAbsent(cntStr, new Message(time));
		
	}


}
