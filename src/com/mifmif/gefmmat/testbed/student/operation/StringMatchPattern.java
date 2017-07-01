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
import com.mifmif.gefmmat.testbed.student.operation.task.MatchPatternTask;
import com.mifmif.gefmmat.testbed.student.operation.task.MatchPatternTask.MatchPatternResult;
import com.mifmif.gefmmat.testbed.student.operation.task.MultiplicationTask.MultiplicationResult;

/**
 * @author y.mifrah
 *
 */
public class StringMatchPattern extends Service {
	String expression, pattern;

	public StringMatchPattern() {
		setName("stringMatchPattern");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		expression = ((MatchPatternTask) task).getExpression();
		pattern = ((MatchPatternTask) task).getPattern();
		if (expression == null || pattern == null) {
			throw new InvalidInputParameterException();
		}

	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		MatchPatternResult result = new MatchPatternResult();
		boolean isMatch = expression.matches(pattern);
		result.setMatchResult(isMatch);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			MatchPatternResult curResult = (MatchPatternResult) task.getResult();
			MatchPatternResult validResult = (MatchPatternResult) execute(task);
			return curResult.getMatchResult().equals(validResult.getMatchResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
