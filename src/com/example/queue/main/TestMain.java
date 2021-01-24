package com.example.queue.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.example.queue.model.Message;
import com.example.queue.runnable.Consumer;
import com.example.queue.runnable.Producer;

public class TestMain {
	public static void main(String[] args) {
		int produceVolume = 30;
		int consumerSize = 2;
		int consumeSleep = 100;
		
		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(30);
		
		Producer producer = new Producer(queue, produceVolume, consumerSize);
		new Thread(producer).start();
		
		for (int i = 0; i < consumerSize; i++) {
			Consumer consumer = new Consumer(queue, consumeSleep);
			new Thread(consumer).start();
		}
		
		System.out.println("start");
	}
}
