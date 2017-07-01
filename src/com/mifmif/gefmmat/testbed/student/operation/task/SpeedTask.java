package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.PhysicFeature;

public class SpeedTask extends Task {
	public SpeedTask() {
		addFeature(PhysicFeature.getInstance());
		setServiceName("calculateSpeed");
	}

	public double getD() throws InvalidInputParameterException {
		double d;
		String valD = getInputs().get("d");
		if (valD == null) {
			throw new InvalidInputParameterException();
		}
		try {
			d = Double.parseDouble(valD);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return d;
	}

	public double getT() throws InvalidInputParameterException {
		double t;
		String valT = getInputs().get("t");
		if (valT == null) {
			throw new InvalidInputParameterException();
		}
		try {
			t = Double.parseDouble(valT);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return t;
	}

	public void setD(double d) {
		getInputs().put("d", "" + d);
	}

	public void setT(double t) {
		getInputs().put("t", "" + t);
	}

	public static SpeedTask generate() {
		SpeedTask task = new SpeedTask();
		task.setD(Math.random());
		task.setT(Math.random());
		return task;
	}

	public static class SpeedResult extends Result {
		public void setSpeedResult(double speedResult) {
			getOutputs().put("speedResult", "" + speedResult);
		}

		public String getSpeedResult() {
			return getOutputs().get("speedResult");
		}

	}
}
