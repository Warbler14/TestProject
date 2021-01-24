package com.example.thread.main;

import java.util.HashMap;
import java.util.Map;

import com.example.thread.model.Message;

public class WeakCopyTest {
	private final static String WEAK = "weak";
	private final static String DEEP = "deep";
	
	public static void main(String[] args) {
		String testCase = WEAK;
//		String testCase = DEEP;
		
		Map<String, Map<String, Message>> gridMap = new HashMap<>();
		
		gridMap.put("1", new HashMap<String, Message>());
		gridMap.put("2", new HashMap<String, Message>());
		
		Map<String, Message> coreMap = new HashMap<>();
		
		initMap(coreMap);
		
		switch(testCase) {
		case WEAK : 
			initMapWeakA( gridMap.get("1"), coreMap);
			initMapWeakB( gridMap.get("2"), coreMap);
			coreMap.get("a").setMessage("AAA");
			break;
		case DEEP :
			initMapDeepA( gridMap.get("1"), coreMap);
			initMapDeepB( gridMap.get("2"), coreMap);
			gridMap.get("1").get("a").setMessage("AAA");
			break;
		}
		
//		clean(gridMap, coreMap, "a");
		
		printMap(coreMap);
		System.out.println("-------------");
		printMap(gridMap.get("1"));
		System.out.println("-------------");
		printMap(gridMap.get("2"));
		System.out.println("==========");
		
		
	}
	
	public static void clean( Map<String, Map<String, Message>> gridPointMap, Map<String, Message> map, String key ) {
		for (Map.Entry<String, Map<String, Message>> entry : gridPointMap.entrySet()) {
			if( remove(entry.getValue(), key) ) {
				break;
			}
		}
		
		remove(map, key);
	}
	
	public static boolean remove( Map<String, Message> map, String key ) {
		for (Map.Entry<String, Message> entry : map.entrySet()) {
			if(key.equals(entry.getKey())) {
				System.out.println( "Remove start "+ entry.getKey() + "=" + entry.getValue().getMessage());
				map.remove(key);
				return true;
			}
		}
		return false;
	}
	
	public static void printMap(Map<String, Message> map) {
		for (Map.Entry<String, Message> entry : map.entrySet()) {
			System.out.println( entry.getKey() + "=" + entry.getValue().getMessage());
		}
	}
	
	private static void initMap( Map<String, Message> map ) {
		
		map.put("a", new Message("a"));
		map.put("b", new Message("b"));
		map.put("c", new Message("c"));
		map.put("d", new Message("d"));
		map.put("e", new Message("e"));
		map.put("f", new Message("f"));
		map.put("g", new Message("g"));
		map.put("h", new Message("h"));
		map.put("i", new Message("i"));
		map.put("j", new Message("j"));
		
	}
	
	private static void initMapWeakA( Map<String, Message> gridPointMap, Map<String, Message> map ) {
		
		gridPointMap.put("a", map.get("a"));
		gridPointMap.put("b", map.get("b"));
		gridPointMap.put("c", map.get("c"));
		gridPointMap.put("d", map.get("d"));
		gridPointMap.put("e", map.get("e"));
		
	}
	
	private static void initMapWeakB( Map<String, Message> gridPointMap, Map<String, Message> map ) {
		
		gridPointMap.put("f", map.get("f"));
		gridPointMap.put("g", map.get("g"));
		gridPointMap.put("h", map.get("h"));
		gridPointMap.put("i", map.get("i"));
		gridPointMap.put("j", map.get("j"));
		
	}
	
	private static void initMapDeepA( Map<String, Message> gridPointMap, Map<String, Message> map ) {
		
		gridPointMap.put("a", new Message(map.get("a").getMessage()));
		gridPointMap.put("b", new Message(map.get("b").getMessage()));
		gridPointMap.put("c", new Message(map.get("c").getMessage()));
		gridPointMap.put("d", new Message(map.get("d").getMessage()));
		gridPointMap.put("e", new Message(map.get("e").getMessage()));
		
	}
	
	private static void initMapDeepB( Map<String, Message> gridPointMap, Map<String, Message> map ) {
		
		gridPointMap.put("f", new Message(map.get("f").getMessage()));
		gridPointMap.put("g", new Message(map.get("g").getMessage()));
		gridPointMap.put("h", new Message(map.get("h").getMessage()));
		gridPointMap.put("i", new Message(map.get("i").getMessage()));
		gridPointMap.put("j", new Message(map.get("j").getMessage()));
		
	}
}
