/**
 * Copyright 2015 y.mifrah
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mifmif.gefmmat.testbed.student.operation;

import java.util.Map;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.AdditionTask;
import com.mifmif.gefmmat.testbed.student.operation.task.AdditionTask.AdditionResult;

/**
 * Addition service implementation
 * 
 * @author y.mifrah
 *
 */
public class Addition extends Service {
	double a, b, additionResult;

	public Addition() {
		setName("addition");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			a = ((AdditionTask) task).getA();
			b = ((AdditionTask) task).getB();
		} catch (Exception exception) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		AdditionResult result = new AdditionResult();
		additionResult = a + b;
		result.setAdditionResult(additionResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			AdditionResult curResult = (AdditionResult) task.getResult();
			AdditionResult validResult = (AdditionResult) execute(task);
			return curResult.getAdditionResult().equals(validResult.getAdditionResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
