/**
 * 
 */
package com.mifmif.gefmmat.testbed.student.operation;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;

/**
 * @author y.mifrah
 *
 */
public class ArithmeticOp extends Service {
	String arithmeticExpression;
	double arethmiticExpResult;

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		Map<String, String> inputs = task.getInputs();
		arithmeticExpression = inputs.get("arithmeticExpression");
		if (arithmeticExpression == null) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		Result result = new Result();

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		try {
			arethmiticExpResult = (Double) engine.eval(arithmeticExpression);
		} catch (ScriptException e) {
			e.printStackTrace();
			throw new TaskProcessingException();
		}
		result.getOutputs().put("arethmiticExpResult", "" + arethmiticExpResult);
		return result;
	}
}
