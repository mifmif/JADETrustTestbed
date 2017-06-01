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
import com.mifmif.gefmmat.testbed.student.operation.task.SpeedTask;
import com.mifmif.gefmmat.testbed.student.operation.task.EnergyTask.EnergyResult;
import com.mifmif.gefmmat.testbed.student.operation.task.SpeedTask.SpeedResult;

/**
 * @author y.mifrah
 *
 */
public class CalculateSpeed extends Service {
	private double d, t, speedResult;

	public CalculateSpeed() {
		setName("calculateSpeed");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			d = ((SpeedTask) task).getD();
			t = ((SpeedTask) task).getT();
		} catch (NumberFormatException exception) {
			throw new InvalidInputParameterException();
		}
		return;
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		SpeedResult result = new SpeedResult();
		speedResult = d / t;
		result.setSpeedResult(speedResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			SpeedResult curResult = (SpeedResult) task.getResult();
			SpeedResult validResult = (SpeedResult) execute(task);
			return curResult.getSpeedResult().equals(validResult.getSpeedResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
