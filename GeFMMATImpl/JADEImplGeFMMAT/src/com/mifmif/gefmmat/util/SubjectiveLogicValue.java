/**
 * 
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
	double belief = 0;
	/**
	 * The belief that the associated feature of agent is untrusted
	 */
	double disbelief = 0;
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
}