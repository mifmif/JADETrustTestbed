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

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;

/**
 * @author y.mifrah
 *
 */
public class CamouflageDishonestStudent extends Student {

	public CamouflageDishonestStudent() {
		setTaskHandler(new CamouflageDishonestTaskHandler(this));
	}

	class CamouflageDishonestTaskHandler extends HonestTaskHandler {
		Map<String, Status> proposerIdStatus = new HashMap<String, Status>();

		public CamouflageDishonestTaskHandler(Agent ownerAgent) {
			super(ownerAgent);
		}

		@Override
		public Result performTask(Task task) {
			boolean performFairly = isTimeToPerformFairly(task);
			Result result = null;
			if (performFairly) {
				result = prepareFairResult(task);
			} else {
				result = prepareUnfairResult(task);

			}
			updateProposerStatus(task, performFairly);
			return result;

		}

		private void updateProposerStatus(Task task, boolean performFairly) {
			String proposerId = task.getProposerId();
			Status status = proposerIdStatus.get(proposerId);
			if (performFairly) {
				status.incrementFairlyCount();
			} else {
				status.incrementUnfairlyCount();
			}
		}

		/**
		 * This method inform whether if the camouflageAgent could perform the task unfairly or fairly
		 * 
		 * @param task
		 * @return
		 */
		private boolean isTimeToPerformFairly(Task task) {
			String proposerId = task.getProposerId();
			Status status = proposerIdStatus.get(proposerId);
			if (status == null) {
				status = new Status();
				proposerIdStatus.put(proposerId, status);
				return true;
			}
			if (status.getFairCount() > 10)
				return false;
			// analyze status e.g. how many time did we perform a valid
			// result
			// for this agent.
			return true;
		}

	}

	static class Status {
		private long fairCount, unfairCount;// TODO we have to think about
											// another

		// structure that handle the order and the
		// services of tasks processed
		// fairly/unfairly

		public void incrementFairlyCount() {
			fairCount++;
		}

		public void incrementUnfairlyCount() {
			unfairCount++;
		}

		public long getFairCount() {
			return fairCount;
		}

		public long getUnfairCount() {
			return unfairCount;
		}
	}
}
