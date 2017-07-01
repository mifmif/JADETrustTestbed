/**
 * Copyright 2015 y.mifrah
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.mifmif.gefmmat.testbed.student.operation;

import java.util.Map;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.EnergyTask;
import com.mifmif.gefmmat.testbed.student.operation.task.ArithmeticOpTask.ArithmeticOpResult;
import com.mifmif.gefmmat.testbed.student.operation.task.EnergyTask.EnergyResult;

/**
 * calculate kinetic energy : formula is Ek = 1/2 x m x v²
 * 
 * @author y.mifrah
 *
 */
public class CalculateEnergy extends Service {
	private double m, v, kineticEnergyResult;

	public CalculateEnergy() {
		setName("calculateEnergy");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			m = ((EnergyTask) task).getM();
			v = ((EnergyTask) task).getV();
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return;
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		EnergyResult result = new EnergyResult();
		kineticEnergyResult = 0.5 * m * v * v;
		result.setKineticEnergyResult(kineticEnergyResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			EnergyResult curResult = (EnergyResult) task.getResult();
			EnergyResult validResult = (EnergyResult) execute(task);
			return curResult.getKineticEnergyResult().equals(validResult.getKineticEnergyResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}

}
