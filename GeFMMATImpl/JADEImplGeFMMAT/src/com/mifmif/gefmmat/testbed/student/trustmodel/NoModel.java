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

package com.mifmif.gefmmat.testbed.student.trustmodel;

import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.trust.ITrustMetric;
import com.mifmif.gefmmat.core.trust.TrustKnowledge;

public class NoModel implements ITrustMetric {

	@Override
	public double propositionEvaluation(Task task) {
		return 0;
	}

	@Override
	public double resultEvaluation(Task task) {
		return 0;
	}

	@Override
	public void updateTrustKnowledge(TrustKnowledge trustKnowledge, Task task) {
		trustKnowledge.getAgentExperience().getProcessedTasks().add(task);

	}

	@Override
	public String getTrustMetricName() {
		return "NoModel";
	}

}
