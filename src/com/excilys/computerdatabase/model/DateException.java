package com.excilys.computerdatabase.model;
@SuppressWarnings("serial")

public class DateException extends Exception {

	/**
	 * 
	 */


	public DateException(){
		
	}
	
	public DateException(String message){
		super(message);
	}
	public DateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
