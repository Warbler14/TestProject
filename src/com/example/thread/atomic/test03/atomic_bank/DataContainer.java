package com.example.thread.atomic.test03.atomic_bank;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataContainer<T extends Record> {
	private Map<String, T> dataMap = new ConcurrentHashMap<>();
	
	public T get(final String key) {
		return dataMap.get(key);
	}
	
	public void insert(final String key, final T t) {
		if( ! dataMap.containsKey(key)) {
			dataMap.put( key, t);
		}
	}
	
	public void delete(final String key) {
		synchronized (dataMap) {
			dataMap.remove(key);
		}
	}
	
	public boolean containsKey(final String key) {
		synchronized (dataMap) {
			return dataMap.containsKey(key);
		}
	}
	
	public String[] getKeyArr() {
		
		String[] dataArr = null; 
		synchronized (dataMap) {
			dataArr = new String[dataMap.size()];
			int index = 0;
			
			Iterator<String> itr = dataMap.keySet().iterator();
			while( itr.hasNext() ) {
				dataArr[index++] = itr.next();
			}
		}
		return dataArr;
	}
}
