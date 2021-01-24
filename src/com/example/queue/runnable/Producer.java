package com.example.queue.runnable;

import java.util.concurrent.BlockingQueue;

import com.example.queue.model.Message;

public class Producer implements Runnable{
	private final BlockingQueue<Message> queue;
	private int volume =0;
	private int exitCount = 0;

	public Producer(BlockingQueue<Message> queue, int volume, int exitCount) {
		this.queue = queue;
		this.volume = volume;
		this.exitCount = exitCount;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < volume; i++) {
			int data = i + 1;
			Message msg = new Message(String.valueOf(data));
			try {
				Thread.sleep(data);
				queue.put(msg);
				System.out.println("Produced " + msg.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Message msg = new Message("exit");
		try {
			while(exitCount-- >0) {
				queue.put(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	   }
}
