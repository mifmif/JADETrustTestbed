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
public class CalculateSpeed extends Service {
	private double d, t, speedResult;

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		Map<String, String> inputs = task.getInputs();
		String valD = inputs.get("d");
		String valT = inputs.get("t");
		if (valD == null || valT == null) {
			throw new InvalidInputParameterException();
		}
		try {
			d = Double.parseDouble("valD");
			t = Double.parseDouble("valT");
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return;
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		Result result = new Result();
		speedResult = d / t;
		result.getOutputs().put("speedResult", "" + speedResult);
		return result;
	}

}
