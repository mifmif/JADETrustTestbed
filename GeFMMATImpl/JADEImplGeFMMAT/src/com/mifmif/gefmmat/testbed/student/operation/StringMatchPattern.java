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
public class StringMatchPattern extends Service {
	String str, pattern;

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		Map<String, String> inputs = task.getInputs();
		str = inputs.get("str");
		pattern = inputs.get("pattern");
		if (str == null || pattern == null) {
			throw new InvalidInputParameterException();
		}

	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		Result result = new Result();
		boolean isMatch = str.matches(pattern);
		result.getOutputs().put("isMatch", "" + isMatch);
		return result;
	}
}
