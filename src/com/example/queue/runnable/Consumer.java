package com.example.queue.runnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.example.queue.model.Message;

public class Consumer implements Runnable{

	private BlockingQueue<Message> queue;
	private int sleep;
	private List<Message> messageList = new ArrayList<Message>();
	private List<List<Message>> copyedMessageList = new ArrayList<List<Message>>();
	
	public Consumer(BlockingQueue<Message> queue, int sleep) {
		this.queue = queue;
		this.sleep = sleep;
	}
	
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		try {
			while(true) {
				
				Message peekMessage = waitPeek(queue);
				List<Message> list = copyQueue(queue);
				copyedMessageList.add(list);
				
				messageList.add(peekMessage);
				
				Message msg =  queue.take();
				String message = msg.getMessage();
				
				if("exit".equals(message)) {
					break;
				}
						
				Thread.sleep(sleep);
				System.out.println(threadName + " Consumed " + msg.getMessage());
				
				
			}
			
			System.out.println(messageList.size());
			
			for (int idx = 0; idx < messageList.size(); idx++) {
				Message msg = messageList.get(idx);
				if(msg != null) {
					System.out.println(threadName + " index " + idx + " value : " + msg.getMessage());
				}
			}
			
			for (int idx1 = 0; idx1 < copyedMessageList.size(); idx1++) {
				List<Message> list = copyedMessageList.get(idx1);
				for (int idx2 = 0; idx2 < list.size(); idx2++) {
					Message msg = list.get(idx2);
					System.out.println("[" + idx1 + ":" + idx2 + "] " + threadName + " value : " + msg.getMessage());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Message waitPeek(BlockingQueue<Message> queue) {
		while(true) {
			Message message = queue.peek();
			if(message != null) {
				return message;
			}
		}
	}
	
	public List<Message> copyQueue(BlockingQueue<Message> queue) {
		List<Message> messageList = new ArrayList<>();
		synchronized (queue) {
			Iterator<Message> itr = queue.iterator();
			while (itr.hasNext()) {
				Message msg = itr.next();
				messageList.add(msg);
			}
		}
		return messageList;		
	}

}
