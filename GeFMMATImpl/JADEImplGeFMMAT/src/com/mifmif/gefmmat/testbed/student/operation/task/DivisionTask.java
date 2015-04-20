package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.MathFeature;

public class DivisionTask extends Task {
	public DivisionTask() {
		addFeature(MathFeature.getInstance());
		setServiceName("division");
	}

	public double getA() throws InvalidInputParameterException {
		double a;
		String valA = getInputs().get("a");
		if (valA == null) {
			throw new InvalidInputParameterException();
		}
		try {
			a = Double.parseDouble(valA);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return a;
	}

	public double getB() throws InvalidInputParameterException {
		double b;
		String valB = getInputs().get("b");
		if (valB == null) {
			throw new InvalidInputParameterException();
		}
		try {
			b = Double.parseDouble(valB);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return b;
	}

	public void setA(double a) {
		getInputs().put("a", "" + a);
	}

	public void setB(double b) {
		getInputs().put("b", "" + b);
	}

	public static DivisionTask generate() {
		DivisionTask task = new DivisionTask();
		task.setA(Math.random());
		task.setB(Math.random());
		return task;
	}

	public static class DivisionResult extends Result {
		public void setDivisionResult(double divisionResult) {
			getOutputs().put("divisionResult", "" + divisionResult);
		}

		public String getDivisionResult() {
			return getOutputs().get("divisionResult");
		}

	}
}
