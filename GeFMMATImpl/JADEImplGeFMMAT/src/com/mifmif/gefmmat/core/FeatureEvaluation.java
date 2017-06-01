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

import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * @author y.mifrah
 *
 */
public class FeatureEvaluation {
	private Feature feature;
	private SubjectiveLogicValue evaluation;
	private int updateCount = 0;

	public FeatureEvaluation(Feature feature, SubjectiveLogicValue evaluation) {
		this.feature = feature;
		this.evaluation = evaluation;
	}

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

	@Override
	public String toString() {
		return (getFeature().getName() + "	[" + updateCount + "]		:	" + getEvaluation().getBelief() + "	  "
						+ getEvaluation().getDisbelief() + "		" + getEvaluation()
						.getUncertainty());
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public void incrementUpdateCount() {
		updateCount++;
	}
}
