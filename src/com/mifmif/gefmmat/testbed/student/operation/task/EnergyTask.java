package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.MathFeature;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.PhysicFeature;

public class EnergyTask extends Task {
	public EnergyTask() {
		addFeature(PhysicFeature.getInstance());
		addFeature(MathFeature.getInstance());
		setServiceName("calculateEnergy");
	}

	public double getM() throws InvalidInputParameterException {
		double m;
		String valM = getInputs().get("m");
		if (valM == null) {
			throw new InvalidInputParameterException();
		}
		try {
			m = Double.parseDouble(valM);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return m;
	}

	public double getV() throws InvalidInputParameterException {
		double v;
		String valV = getInputs().get("v");
		if (valV == null) {
			throw new InvalidInputParameterException();
		}
		try {
			v = Double.parseDouble(valV);
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return v;
	}

	public void setM(double m) {
		getInputs().put("m", "" + m);
	}

	public void setV(double v) {
		getInputs().put("v", "" + v);
	}

	public static EnergyTask generate() {
		EnergyTask task = new EnergyTask();
		task.setM(Math.random());
		task.setV(Math.random());
		return task;
	}

	public static class EnergyResult extends Result {
		public void setKineticEnergyResult(double kineticEnergyResult) {
			getOutputs().put("kineticEnergyResult", "" + kineticEnergyResult);
		}

		public String getKineticEnergyResult() {
			return getOutputs().get("kineticEnergyResult");
		}

	}
}
