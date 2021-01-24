package com.example.thread.execute;

import com.example.thread.model.DataMessageStore;

public class ReadTask implements Task{
	
	private DataMessageStore dataContainer;
	
	public ReadTask( DataMessageStore dataContainer ) {
		this.dataContainer = dataContainer;
	}
	
	@Override
	public void execute() {
		dataContainer.size();
//		System.out.println( "data size : " + dataContainer.size() );
		
	}


}
