package com.example.thread.model;

public class Message implements Record, Cloneable{

	private String message;
	
	public Message() {}
	
	public Message( final String message ) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public Message clone() throws CloneNotSupportedException{
		return (Message)super.clone();
	}
	
}
