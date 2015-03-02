/**
 * 
 */
package com.mifmif.gefmmat.core;

import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * @author y.mifrah
 *
 */
public class FeatureEvaluation {
	private Feature feature;
	private SubjectiveLogicValue evaluation;

	/**
	 * @return the feature
	 */
	public Feature getFeature() {
		return feature;
	}

	/**
	 * @param feature
	 *            the feature to set
	 */
	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	/**
	 * @return the evaluation
	 */
	public SubjectiveLogicValue getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation
	 *            the evaluation to set
	 */
	public void setEvaluation(SubjectiveLogicValue evaluation) {
		this.evaluation = evaluation;
	}
}
