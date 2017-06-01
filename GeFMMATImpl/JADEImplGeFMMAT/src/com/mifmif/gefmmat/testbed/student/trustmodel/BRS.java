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

package com.mifmif.gefmmat.testbed.student.trustmodel;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Feature;
import com.mifmif.gefmmat.core.FeatureEvaluation;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.trust.ITrustMetric;
import com.mifmif.gefmmat.core.trust.TrustKnowledge;

/**
 * with BRS we have the following mapping to evaluate the trustworthiness of an agent : <br>
 * b = r/(r+s+2) <br>
 * s = r/(r+s+2) <br>
 * 2 = r/(r+s+2) <br>
 * where r is the number of positive feedback,and s the number of negative feedback
 * 
 * @author y.mifrah
 *
 */
public class BRS implements ITrustMetric {

	@Override
	public double propositionEvaluation(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double resultEvaluation(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateTrustKnowledge(TrustKnowledge trustKnowledge, Task task) {
		trustKnowledge.getAgentExperience().getProcessedTasks().add(task);
		int r = 0;
		int s = 0;

		for (Task passedTask : trustKnowledge.getAgentExperience().getProcessedTasks()) {
			if (passedTask.getResult().isValid()) {
				r++;
			} else {
				s++;
			}
		}
		double b = ((double) r / (double) (r + s + 2));
		double d = (double) s / (double) (r + s + 2);
		double u = (double) 2 / (double) (r + s + 2);
		for (FeatureEvaluation featureEvaluation : trustKnowledge.getFeatureEvaluations()) {
			featureEvaluation.getEvaluation().setBelief(b);
			featureEvaluation.getEvaluation().setDisbelief(d);
			featureEvaluation.getEvaluation().setUncertainty(u);
			featureEvaluation.incrementUpdateCount();
		}
	}

	@Override
	public String getTrustMetricName() {
		return "BRS";
	}
}
