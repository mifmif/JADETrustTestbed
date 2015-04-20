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

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.SubstractionTask;
import com.mifmif.gefmmat.testbed.student.operation.task.MatchPatternTask.MatchPatternResult;
import com.mifmif.gefmmat.testbed.student.operation.task.SubstractionTask.SubstractionResult;

/**
 * @author y.mifrah
 *
 */
public class Substraction extends Service {
	double a, b, substractionResult;

	public Substraction() {
		setName("substraction");
	}

	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			a = ((SubstractionTask) task).getA();
			b = ((SubstractionTask) task).getB();
		} catch (Exception exception) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		SubstractionResult result = new SubstractionResult();
		substractionResult = a - b;
		result.setSubstractionResult(substractionResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			SubstractionResult curResult = (SubstractionResult) task.getResult();
			SubstractionResult validResult = (SubstractionResult) execute(task);
			return curResult.getSubstractionResult().equals(validResult.getSubstractionResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
