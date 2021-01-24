package com.example.thread.atomic.test03.atomic_bank;

public class DataBank extends DataContainer<AtomicIntegerRecord>{
	
	public void insert(final String key) {
		super.insert( key, new AtomicIntegerRecord());
	}
	
	public void delete(final String key) {
		super.delete(key);
	}
	
	public int read(final String key) {
		return super.get(key).getValue().get();
	}
	
	public void increment(final String key) {
		if( super.containsKey(key)) {
			super.get(key).getValue().incrementAndGet();
		}
	}
	
	public void decrement(final String key) {
		if( super.containsKey(key)) {
			super.get(key).getValue().decrementAndGet();
		}
	}
	
	public String[] getKeyArr() {
		return super.getKeyArr();
	}
}
