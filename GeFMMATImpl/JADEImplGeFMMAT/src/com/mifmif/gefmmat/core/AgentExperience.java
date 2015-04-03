/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.util.List;

import com.mifmif.gefmmat.core.trust.TrustKnowledge;
import com.mifmif.gefmmat.core.trust.TrustMetric;

/**
 * This class present directional information that characterizes the
 * relationship between a trustor and a trustee agent
 * 
 * @author y.mifrah
 *
 */
public class AgentExperience {
	private TrustKnowledge trustKnowledge;
	private TrustMetric trustMetric;
	private Agent trustorAgent;
	private Agent trusteeAgent;
	private List<Task> processedTasks;

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
	 * @return the trustorAgent
	 */
	public Agent getTrustorAgent() {
		return trustorAgent;
	}

	/**
	 * @param trustorAgent
	 *            the trustorAgent to set
	 */
	public void setTrustorAgent(Agent trustorAgent) {
		this.trustorAgent = trustorAgent;
	}

	/**
	 * @return the trusteeAgent
	 */
	public Agent getTrusteeAgent() {
		return trusteeAgent;
	}

	/**
	 * @param trusteeAgent
	 *            the trusteeAgent to set
	 */
	public void setTrusteeAgent(Agent trusteeAgent) {
		this.trusteeAgent = trusteeAgent;
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
}
