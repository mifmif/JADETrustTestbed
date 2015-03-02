/**
 * 
 */
package com.mifmif.gefmmat.core.trust;

import com.mifmif.gefmmat.core.Task;

/**
 * Trust metric interface implemented by class that will be passed to the trust
 * metric class used by the agent to evaluate their partners
 * 
 * @author y.mifrah
 *
 */
public interface ITrustMetric {
	/**
	 * This method will be called to update a trustor agent's knowledge about a
	 * trustee agent. it will be called after evaluating result of a task.
	 * 
	 * @param trustKnowledge
	 */
	void updateTrustKnowledge(TrustKnowledge trustKnowledge);

	/**
	 * Return an expectation value about the evidence of the proposition
	 * associated to the task.
	 * 
	 * @param task
	 * @return
	 */
	double propositionEvaluation(Task task);

	/**
	 * Evaluate the result obtained from the trustee agent and check whether if
	 * the result match what was proposed or not
	 * 
	 * @param task
	 */
	double resultEvaluation(Task task);
}
