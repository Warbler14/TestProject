package com.example.thread.execute;

public class WaitTask implements Task{
	
	private boolean isExit = false; 
	public WaitTask() {
	}
	
	@Override
	public void execute() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName());
				
				if(isExit) {
					break;
				}

			} catch (InterruptedException ie) {
				ie.printStackTrace();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void exit() {
		this.isExit = true;
	}

}
