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
 * calculate kinetic energy : formula is Ek = 1/2 x m x v²
 * 
 * @author y.mifrah
 *
 */
public class CalculateEnergy extends Service {
	private double m, v, kineticEnergyResult;

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		Map<String, String> inputs = task.getInputs();
		String valM = inputs.get("m");
		String valV = inputs.get("v");
		if (valM == null || valV == null) {
			throw new InvalidInputParameterException();
		}
		try {
			m = Double.parseDouble("valM");
			v = Double.parseDouble("valV");
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return;
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		Result result = new Result();
		kineticEnergyResult = 0.5 * m * v * v;
		result.getOutputs().put("energyResult", "" + kineticEnergyResult);
		return result;
	}

}



