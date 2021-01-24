package com.example.thread.execute;

import com.example.thread.model.DataMessageStore;

public class RemoveTask implements Task{
	
	private DataMessageStore dataContainer;
	
	public RemoveTask( DataMessageStore dataContainer ) {
		this.dataContainer = dataContainer;
	}
	
	@Override
	public void execute() {
		
		String[] keyArr = dataContainer.getKeyArr();
		
		for (int idx0 = 0, end0 = keyArr.length; idx0 < end0; idx0++) {
			dataContainer.remove( keyArr[idx0] );
		}
	}


}
