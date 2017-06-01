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

package com.mifmif.gefmmat.testbed.student;

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;

/**
 * @author y.mifrah
 *
 */
public class ConstantDishonestStudent extends Student {

	public ConstantDishonestStudent() {
		setTaskHandler(new ConstantDishonestTaskHandler(this));
	}

	class ConstantDishonestTaskHandler extends HonestTaskHandler {
		public ConstantDishonestTaskHandler(Agent ownerAgent) {
			super(ownerAgent);
		}

		@Override
		public Result performTask(Task task) {
			Result result = prepareUnfairResult(task);
			return result;

		}

	}
}
