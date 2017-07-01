package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.MathFeature;

public class ArithmeticOpTask extends Task {
	public ArithmeticOpTask() {
		addFeature(MathFeature.getInstance());
		setServiceName("arithmeticOp");
	}

	public String getArithmeticExpression() throws InvalidInputParameterException {
		String arithmeticExpression = null;
		arithmeticExpression = getInputs().get("arithmeticExpression");
		if (arithmeticExpression == null) {
			throw new InvalidInputParameterException();
		}
		return arithmeticExpression;
	}

	public void setArithmeticExpression(String arithmeticExpression) {
		getInputs().put("arithmeticExpression", "" + arithmeticExpression);
	}

	public static ArithmeticOpTask generate() {
		ArithmeticOpTask task = new ArithmeticOpTask();
		String arithmeticExpression = "2-4+5*8";
		// TODO generate random arithmeticExpression
		task.setArithmeticExpression(arithmeticExpression);
		return task;
	}

	public static class ArithmeticOpResult extends Result {
		public void setArethmiticExpResult(double arithmiticExpResult) {
			getOutputs().put("arithmiticExpResult", "" + arithmiticExpResult);
		}

		public String getArethmiticExpResult() {
			return getOutputs().get("arithmiticExpResult");
		}

	}
}
