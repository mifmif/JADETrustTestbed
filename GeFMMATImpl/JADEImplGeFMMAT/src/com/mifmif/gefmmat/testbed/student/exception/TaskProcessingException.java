package com.mifmif.gefmmat.testbed.student.exception;

public class TaskProcessingException extends Exception {
	private static final String MSG_EXCEPTION = "An error has occured while proccessing the current task";

	public TaskProcessingException() {
		super(MSG_EXCEPTION);
	}

}
