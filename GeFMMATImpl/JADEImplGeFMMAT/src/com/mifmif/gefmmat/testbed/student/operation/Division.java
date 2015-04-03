/**
 * 
 */
package com.mifmif.gefmmat.testbed.student.operation;

import java.util.Map;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;

/**
 * @author y.mifrah
 *
 */
public class Division extends Service {
	double a, b, soustractionResult;

	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		Map<String, String> inputs = task.getInputs();
		String valA = inputs.get("a");
		String valB = inputs.get("b");
		if (valA == null || valB == null) {
			throw new InvalidInputParameterException();
		}
		try {
			a = Double.parseDouble("valA");
			b = Double.parseDouble("valB");
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return;
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		Result result = new Result();
		try {
			soustractionResult = a / b;
		} catch (Exception e) {
			throw new TaskProcessingException();
		}
		result.getOutputs().put("soustractionResult", "" + soustractionResult);
		return result;
	}
}
