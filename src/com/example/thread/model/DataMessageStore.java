package com.example.thread.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataMessageStore extends DataContainer<Message> implements Record{

	public Message get(final String key) {
		return super.get(key);
	}

	public Message putIfAbsent(final String key, final Message value) {
		return super.putIfAbsent(key, value);
	}
	
	public void put(final String key, Message t) {
		super.put(key, t);
	}
	
	public void replace(final String key, Message t) {
		super.replace(key, t);
	}
	
	public int size() {
		return super.size();
	}
	
	public void remove(final String key) {
		super.remove(key);
	}
	
	public boolean containsKey(final String key) {
		return super.containsKey(key);
	}
	
	public Map<String, Message> cloneCopy() {
		Map<String, Message> copyMap = new ConcurrentHashMap<>();
		
		for (Map.Entry<String, Message> entry : super.copy().entrySet()) {
			try {
				copyMap.put(entry.getKey(), entry.getValue().clone() );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return copyMap;
	}
	
	public Map<String, Message> copy() {
		return super.copy();
	}
	
	public String[] getKeyArr() {
		return super.getKeyArr();
	}
}
