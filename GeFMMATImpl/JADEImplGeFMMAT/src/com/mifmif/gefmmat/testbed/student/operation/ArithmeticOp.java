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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.ArithmeticOpTask;
import com.mifmif.gefmmat.testbed.student.operation.task.AdditionTask.AdditionResult;
import com.mifmif.gefmmat.testbed.student.operation.task.ArithmeticOpTask.ArithmeticOpResult;

/**
 * @author y.mifrah
 *
 */
public class ArithmeticOp extends Service {
	String arithmeticExpression;
	double arithmiticExpResult;

	public ArithmeticOp() {
		setName("arithmeticOp");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		arithmeticExpression = ((ArithmeticOpTask) task).getArithmeticExpression();
		if (arithmeticExpression == null) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		ArithmeticOpResult result = new ArithmeticOpResult();

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("js");
		try {
			arithmiticExpResult = (Double) engine.eval(arithmeticExpression);
		} catch (ScriptException e) {
			e.printStackTrace();
			throw new TaskProcessingException();
		}
		result.setArethmiticExpResult(arithmiticExpResult);
		return result;
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			ArithmeticOpResult curResult = (ArithmeticOpResult) task.getResult();
			ArithmeticOpResult validResult = (ArithmeticOpResult) execute(task);
			return curResult.getArethmiticExpResult().equals(validResult.getArethmiticExpResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
