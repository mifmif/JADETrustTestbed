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

package com.mifmif.gefmmat.core;

import jade.core.AID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mifmif.gefmmat.core.trust.TrustKnowledge;
import com.mifmif.gefmmat.core.trust.TrustMetric;
import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * This class present directional information that characterizes the relationship between a trustor and a trustee agent
 * 
 * @author y.mifrah
 *
 */
public class AgentExperience {
	private TrustKnowledge trustKnowledge;
	private TrustMetric trustMetric;
	private AID trustorAgent;
	private AID trusteeAgent;
	private List<Task> processedTasks = Collections.synchronizedList(new ArrayList<Task>());

	public AgentExperience() {
		trustKnowledge = new TrustKnowledge(this);
	}

	/**
	 * @return the trustKnowledge
	 */
	public TrustKnowledge getTrustKnowledge() {
		return trustKnowledge;
	}

	/**
	 * @param trustKnowledge
	 *            the trustKnowledge to set
	 */
	public void setTrustKnowledge(TrustKnowledge trustKnowledge) {
		this.trustKnowledge = trustKnowledge;
	}

	/**
	 * @return the processedTasks
	 */
	public List<Task> getProcessedTasks() {
		return processedTasks;
	}

	/**
	 * @param processedTasks
	 *            the processedTasks to set
	 */
	public void setProcessedTasks(List<Task> processedTasks) {
		this.processedTasks = processedTasks;
	}

	/**
	 * @return the trustMetric
	 */
	public TrustMetric getTrustMetric() {
		return trustMetric;
	}

	/**
	 * @param trustMetric
	 *            the trustMetric to set
	 */
	public void setTrustMetric(TrustMetric trustMetric) {
		this.trustMetric = trustMetric;
	}

	public AID getTrusteeAgent() {
		return trusteeAgent;
	}

	public void setTrusteeAgent(AID trusteeAgent) {
		this.trusteeAgent = trusteeAgent;
	}

	public AID getTrustorAgent() {
		return trustorAgent;
	}

	public void setTrustorAgent(AID trustorAgent) {
		this.trustorAgent = trustorAgent;
	}

	public SubjectiveLogicValue getFeatureEvaluation(Feature feature) {
		for (FeatureEvaluation fe : trustKnowledge.getFeatureEvaluations()) {
			if (fe.getFeature().equals(feature)) {
				return fe.getEvaluation();
			}
		}
		SubjectiveLogicValue evaluation = new SubjectiveLogicValue();
		FeatureEvaluation featureEvaluation = new FeatureEvaluation(feature, evaluation);
		trustKnowledge.getFeatureEvaluations().add(featureEvaluation);
		return evaluation;
	}

	@Override
	public String toString() {
		String str = trusteeAgent.getName() + "\n";
		str += trustKnowledge.toString();
		return str;
	}

	public SubjectiveLogicValue getMeanFeatureEvaluation() {
		SubjectiveLogicValue evaluation = new SubjectiveLogicValue();
		double b = 0;
		double d = 0;
		double u = 0;
		int size = trustKnowledge.getFeatureEvaluations().size();
		for (FeatureEvaluation fe : trustKnowledge.getFeatureEvaluations()) {
			b += fe.getEvaluation().getBelief();
			d += fe.getEvaluation().getDisbelief();
			u += fe.getEvaluation().getUncertainty();
		}
		b /= size;
		d /= size;
		u /= size;
		evaluation.setBelief(b);
		evaluation.setDisbelief(d);
		evaluation.setUncertainty(u);
		return evaluation;

	}
}
