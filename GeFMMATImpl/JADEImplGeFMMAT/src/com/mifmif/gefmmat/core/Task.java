/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.io.Serializable;

/**
 * 
 * This class would represent requirements needed to be done, it would also
 * include proposition information and result after processing this task by the
 * delegated agent.
 * 
 * @author y.mifrah
 *
 */
abstract public class Task implements Serializable {

	private static final long serialVersionUID = 20141220L;
	// a task will have specific requirements
	private Proposal proposal;
	private Result result;

	/**
	 * @return the proposal
	 */
	public Proposal getProposal() {
		return proposal;
	}

	/**
	 * @param proposal
	 *            the proposal to set
	 */
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}
}
