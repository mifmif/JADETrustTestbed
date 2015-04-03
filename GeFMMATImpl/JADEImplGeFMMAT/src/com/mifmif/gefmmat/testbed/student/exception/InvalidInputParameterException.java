package com.mifmif.gefmmat.testbed.student.exception;

public class InvalidInputParameterException extends Exception {
	private static final String MSG_EXCEPTION = "Input parameters not nound or invalid";

	public InvalidInputParameterException() {
		super(MSG_EXCEPTION);
	}

}
