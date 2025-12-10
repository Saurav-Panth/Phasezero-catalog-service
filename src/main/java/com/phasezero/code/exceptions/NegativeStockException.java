package com.phasezero.code.exceptions;

public class NegativeStockException extends RuntimeException{
	public NegativeStockException(String message) {
		super(message);
	}
}
