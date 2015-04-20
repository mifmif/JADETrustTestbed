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

package com.mifmif.gefmmat.util;

/**
 * @author y.mifrah
 *
 */
public class SubjectiveLogicValue {
	/**
	 * The belief that the associated feature of agent is trusted
	 */
	private double belief = 0;
	/**
	 * The belief that the associated feature of agent is untrusted
	 */
	private double disbelief = 0;
	/**
	 * The amount of uncommitted belief
	 */
	private double uncertainty = 1;
	/**
	 * is the a priori probability factor associated to the uncertainty where
	 * the expectation is = belief + baseRate * uncertainty
	 */
	double baseRate;

	/**
	 * @return the uncertainty
	 */
	public double getUncertainty() {
		return uncertainty;
	}

	/**
	 * @param uncertainty
	 *            the uncertainty to set
	 */
	public void setUncertainty(double uncertainty) {
		this.uncertainty = uncertainty;
	}

	public double getBelief() {
		return belief;
	}

	public void setBelief(double belief) {
		this.belief = belief;
	}

	public double getDisbelief() {
		return disbelief;
	}

	public void setDisbelief(double disbelief) {
		this.disbelief = disbelief;
	}
}
