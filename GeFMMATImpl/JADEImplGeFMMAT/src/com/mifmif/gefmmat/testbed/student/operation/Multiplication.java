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

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.AdditionTask;
import com.mifmif.gefmmat.testbed.student.operation.task.MultiplicationTask;
import com.mifmif.gefmmat.testbed.student.operation.task.DivisionTask.DivisionResult;
import com.mifmif.gefmmat.testbed.student.operation.task.MultiplicationTask.MultiplicationResult;

/**
 * @author y.mifrah
 *
 */
public class Multiplication extends Service {
	double a, b, multiplicationResult;

	public Multiplication() {
		setName("multiplication");
	}

	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			a = ((MultiplicationTask) task).getA();
			b = ((MultiplicationTask) task).getB();
		} catch (Exception exception) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		MultiplicationResult result = new MultiplicationResult();
		multiplicationResult = a * b;
		result.setMultiplicationResult(multiplicationResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			MultiplicationResult curResult = (MultiplicationResult) task.getResult();
			MultiplicationResult validResult = (MultiplicationResult) execute(task);
			return curResult.getMultiplicationResult().equals(validResult.getMultiplicationResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
