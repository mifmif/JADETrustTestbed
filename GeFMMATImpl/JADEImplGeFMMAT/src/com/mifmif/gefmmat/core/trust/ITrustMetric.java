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

package com.mifmif.gefmmat.core.trust;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Task;

/**
 * Trust metric interface implemented by class that will be passed to the trust metric class used by the agent to evaluate their partners
 * 
 * @author y.mifrah
 *
 */
public interface ITrustMetric {
	/**
	 * This method will be called to update a trustor agent's knowledge about a trustee agent. it will be called after evaluating result of a task.
	 * 
	 * @param trustKnowledge
	 * @param isResultTaskValid
	 * @param task
	 */
	void updateTrustKnowledge(TrustKnowledge trustKnowledge, Task task);

	/**
	 * Return an expectation value about the evidence of the proposition associated to the task.
	 * 
	 * @param task
	 * @return
	 */
	double propositionEvaluation(Task task);

	/**
	 * Evaluate the result obtained from the trustee agent and check whether if the result match what was proposed or not
	 * 
	 * @param task
	 */
	double resultEvaluation(Task task);

	String getTrustMetricName();
}
