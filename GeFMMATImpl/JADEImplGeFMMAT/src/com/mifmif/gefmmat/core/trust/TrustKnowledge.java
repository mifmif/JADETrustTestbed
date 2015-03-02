/**
 * 
 */
package com.mifmif.gefmmat.core.trust;

import java.util.List;

import com.mifmif.gefmmat.core.FeatureEvaluation;

/**
 * @author y.mifrah
 *
 */
public class TrustKnowledge {
	private List<FeatureEvaluation> featureEvaluations;

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
}
