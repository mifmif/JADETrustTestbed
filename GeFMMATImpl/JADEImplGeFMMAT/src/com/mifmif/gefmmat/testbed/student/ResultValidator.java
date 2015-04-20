package com.mifmif.gefmmat.testbed.student;

import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.operation.OperationFactory;

/**
 * @author y.mifrah
 *
 */
public class ResultValidator {
	private ResultValidator() {
	}

	/**
	 * check if the result associated with this task is valid or not
	 * 
	 * @param task
	 * @return
	 */
	public static void validateTask(Task task) {
		String serviceName = task.getServiceName();
		Service service = OperationFactory.getService(serviceName);
		boolean isValid = service.isResultTaskValid(task);
		task.getResult().setValid(isValid);
	}
}
