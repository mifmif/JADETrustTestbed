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

package com.mifmif.gefmmat.testbed.student;

import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

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
public class WhitewashingDishonestStudent extends Student {
	private boolean toWhiteWash = false;

	public WhitewashingDishonestStudent() {
		setTaskHandler(new WhitewashingDishonestTaskHandler(this));

	}

	public boolean isToWhiteWash() {
		return toWhiteWash;
	}

	void setToWhiteWash(boolean toWhiteWash) {
		this.toWhiteWash = toWhiteWash;
	}

	class IdentityUpdater extends TickerBehaviour {
		WhitewashingDishonestStudent student;

		public IdentityUpdater(WhitewashingDishonestStudent student, long period_ms) {
			super(student, period_ms);
			this.student = student;
		}

		@Override
		protected void onTick() {
			boolean whiteWash = student.isToWhiteWash();
			if (whiteWash) {
				student.takeDown();
				System.out.println(student.getAID().getName() + "  leave the system");
				Object[] arguments = student.getArguments();
				System.out.println(student.getAID().getName() + "  joining the system the second time..");
				AgentController newWhiteWhashingStudent;
				try {
					newWhiteWhashingStudent = MainContainer.getContainer().createNewAgent(student.getAID().getName(),
							"com.mifmif.gefmmat.testbed.student.WhitewashingDishonestStudent", arguments);
					newWhiteWhashingStudent.start();
					System.out.println(student.getAID().getName() + "  joined the system ");
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}

			}
		}
	}

	class WhitewashingDishonestTaskHandler extends HonestTaskHandler {
		Map<String, Status> proposerIdStatus = new HashMap<String, Status>();

		public WhitewashingDishonestTaskHandler(Agent ownerAgent) {
			super(ownerAgent);
		}

		@Override
		public Result performTask(Task task) {
			isTimeToWhiteWash();
			Result result = null;
			boolean performFairly = Math.random() < 0.5;
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
			if (status == null) {
				status = new Status();
			}
			if (performFairly) {
				status.incrementFairlyCount();
			} else {
				status.incrementUnfairlyCount();
			}
		}

		/**
		 * This method inform whether if the agent need to leave the system and joint it again to clean it's bad reputation
		 * 
		 * @param task
		 * @return
		 */
		private boolean isTimeToWhiteWash() {
			long fairCount = 0, unfairCount = 0;

			for (Entry<String, Status> entry : proposerIdStatus.entrySet()) {

				Status status = entry.getValue();
				fairCount += status.getFairCount();
				unfairCount += status.getUnfairCount();
			}
			if (unfairCount > 10 && ((double) fairCount / (double) (unfairCount) < 0.5)) {
				return true;
			} else {
				return false;
			}
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
