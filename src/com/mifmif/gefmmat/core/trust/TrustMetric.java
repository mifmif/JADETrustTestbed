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

package com.mifmif.gefmmat.core.trust;

import java.util.List;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Feature;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * @author y.mifrah
 *
 */
public class TrustMetric {
	private ITrustMetric trustMetricStrategy;

	public TrustMetric(ITrustMetric trustMetricStrategy) {
		this.trustMetricStrategy = trustMetricStrategy;
	}

	public void updateTrustKnowledge(TrustKnowledge trustKnowledge, Task task) {
		trustMetricStrategy.updateTrustKnowledge(trustKnowledge, task);
	}

	public double propositionEvaluation(Task task) {
		return trustMetricStrategy.propositionEvaluation(task);

	}

	public double resultEvaluation(Task task) {
		return trustMetricStrategy.resultEvaluation(task);
	}

	public String getTrustMetricName() {
		return trustMetricStrategy.getTrustMetricName();
	}

	/**
	 * if the agent with exprience1 is more trustworthy than agent with experience2 then return a positive value ,otherwise return a
	 * negative value
	 * 
	 * @param experience1
	 * @param experience2
	 * @param taskFeatures
	 * @return
	 */
	public int compareAgentExperience(AgentExperience experience1, AgentExperience experience2, List<Feature> taskFeatures) {
		double b_diff = 0;
		double d_diff = 0;
		double u_diff = 0;
		for (Feature feature : taskFeatures) {
			SubjectiveLogicValue evaluation1 = experience1.getFeatureEvaluation(feature);
			SubjectiveLogicValue evaluation2 = experience2.getFeatureEvaluation(feature);
			b_diff += evaluation1.getBelief() - evaluation2.getBelief();
			d_diff += evaluation1.getDisbelief() - evaluation2.getDisbelief();
			u_diff += evaluation1.getUncertainty() - evaluation2.getUncertainty();
		}
		if (b_diff > 0 || b_diff == 0 && d_diff < 0)
			return 1;
		if (u_diff > d_diff)
			return 1;

		return -1;
	}

}
