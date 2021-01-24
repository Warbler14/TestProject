package com.example.thread.model;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataContainer <T extends Record>{
	private Map<String, T> dataMap = new ConcurrentHashMap<>();
	
	protected T get(final String key) {
		return dataMap.get(key);
	}
	
	protected T putIfAbsent(final String key, final T value) {
		return ((ConcurrentHashMap<String,T>)dataMap).putIfAbsent(key, value);
	}
	
	protected void put(final String key, T t) {
		((ConcurrentHashMap<String,T>)dataMap).put(key, t);
	}
	
	protected void replace(final String key, T t) {
		((ConcurrentHashMap<String,T>)dataMap).replace(key, t);
	}
	
	protected int size() {
		return dataMap.size();
//		return ((ConcurrentHashMap<String,T>)dataMap).size();
	}
	
	protected void remove(final String key) {
		synchronized (dataMap) {
			dataMap.remove(key);
		}
	}
	
	protected boolean containsKey(final String key) {
		synchronized (dataMap) {
			return dataMap.containsKey(key);
		}
	}
	
	protected Map<String, T> getMap() {
		return dataMap;
	}
	
	protected Map<String, T> copy(){
		Map<String, T> copyMap = new ConcurrentHashMap<>();
		synchronized (dataMap) {
			for (Map.Entry<String, T> entry : dataMap.entrySet()) {
				copyMap.put(entry.getKey(), entry.getValue());
			}
		}
		return copyMap;
	}
	
	protected String[] getKeyArr() {
		
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
