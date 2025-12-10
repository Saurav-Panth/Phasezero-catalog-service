package com.phasezero.code.dto;

import com.phasezero.code.enums.Message;

import lombok.Getter;
import lombok.Setter;


public class ResponseStructure<T> {

	private int stausCode;
	
	private Message message;
	
	private T data;

	public int getStausCode() {
		return stausCode;
	}

	public void setStausCode(int stausCode) {
		this.stausCode = stausCode;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
