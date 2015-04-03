/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.io.Serializable;
import java.util.Map;

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
	private Map<String, String> inputs;

	// a task will have specific requirements
	private Proposal proposal;// this is the proposal of the choosed agent to
								// process this task , at the end the initiator
								// agent will compare this proposal with the
								// final result to evaluate the agent
								// trustworthiness
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

	public Map<String, String> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, String> inputs) {
		this.inputs = inputs;
	}
}
