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

import com.mifmif.gefmmat.core.FeatureEvaluation;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.trust.ITrustMetric;
import com.mifmif.gefmmat.core.trust.TrustKnowledge;

public class ForgiveFactor implements ITrustMetric {
	private double forgivenessFactor = 0;

	public ForgiveFactor(double forgivenessFactor) {
		this.forgivenessFactor = forgivenessFactor;
	}

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
		int nbrExp = trustKnowledge.getAgentExperience().getProcessedTasks().size();
		double resultOK = 0;
		double resultNOK = 0;
		for (int i = 0; i < nbrExp; ++i) {
			Task passedTask = trustKnowledge.getAgentExperience().getProcessedTasks().get(i);
			if (passedTask.getResult().isValid()) {
				resultOK += Math.pow(forgivenessFactor, nbrExp - i);
			} else {
				resultNOK += Math.pow(forgivenessFactor, nbrExp - i);
			}
		}
		resultOK *= (1 - forgivenessFactor);
		resultOK /= (1 - Math.pow(forgivenessFactor, nbrExp));
		resultNOK *= (1 - forgivenessFactor);
		resultNOK /= (1 - Math.pow(forgivenessFactor, nbrExp));
		double b = resultOK;

		double d = resultNOK;
		double u = 1 - resultNOK - resultOK;
		for (FeatureEvaluation featureEvaluation : trustKnowledge.getFeatureEvaluations()) {
			featureEvaluation.getEvaluation().setBelief(b);
			featureEvaluation.getEvaluation().setDisbelief(d);
			featureEvaluation.getEvaluation().setUncertainty(u);
			featureEvaluation.incrementUpdateCount();
		}

	}

	@Override
	public String getTrustMetricName() {
		return "ForgiveFactor";
	}

}
