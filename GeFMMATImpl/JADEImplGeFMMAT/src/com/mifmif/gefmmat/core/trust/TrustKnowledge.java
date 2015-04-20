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

import java.util.ArrayList;
import java.util.List;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Feature;
import com.mifmif.gefmmat.core.FeatureEvaluation;
import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * @author y.mifrah
 *
 */
public class TrustKnowledge {
	private List<FeatureEvaluation> featureEvaluations = new ArrayList<FeatureEvaluation>();
	private AgentExperience agentExperience;

	public TrustKnowledge(AgentExperience agentExperience) {

		this.setAgentExperience(agentExperience);
	}

	public FeatureEvaluation getEvaluation(Feature feature) {
		for (FeatureEvaluation fe : featureEvaluations) {
			if (fe.getFeature().equals(feature))
				return fe;
		}
		FeatureEvaluation fe = new FeatureEvaluation(feature, new SubjectiveLogicValue());
		featureEvaluations.add(fe);
		return fe;
	}

	/**
	 * @return the featureEvaluations
	 */
	public List<FeatureEvaluation> getFeatureEvaluations() {
		return featureEvaluations;
	}

	/**
	 * @param featureEvaluations
	 *            the featureEvaluations to set
	 */
	public void setFeatureEvaluations(List<FeatureEvaluation> featureEvaluations) {
		this.featureEvaluations = featureEvaluations;
	}

	@Override
	public String toString() {
		String str = "featureName  		|   belief   | disbelief   | uncertainty   |\n";
		for (FeatureEvaluation featureEvaluation : featureEvaluations) {
			str += featureEvaluation.toString() + "\n";
		}
		return str;
	}

	public AgentExperience getAgentExperience() {
		return agentExperience;
	}

	public void setAgentExperience(AgentExperience agentExperience) {
		this.agentExperience = agentExperience;
	}
}
