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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;

/**
 * @author y.mifrah
 *
 */
public class RandomDishonestStudent extends Student {

	public RandomDishonestStudent() {
		setTaskHandler(new RandomDishonestTaskHandler(this));
	}

	class RandomDishonestTaskHandler extends HonestTaskHandler {

		public RandomDishonestTaskHandler(Agent ownerAgent) {
			super(ownerAgent);
		}

		@Override
		public Result performTask(Task task) {
			Result result = null;
			boolean generateWrongResult = Math.random() > 0.5;
			if (generateWrongResult) {
				result = prepareUnfairResult(task);
			} else {
				result = prepareFairResult(task);
			}
			return result;

		}

	}
}
