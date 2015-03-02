/**
 * 
 */
package com.mifmif.gefmmat.core.trust;

import com.mifmif.gefmmat.core.Task;

/**
 * @author y.mifrah
 *
 */
public class TrustMetric {
	private ITrustMetric trustMetricStrategy;

	public TrustMetric(ITrustMetric trustMetricStrategy) {
		this.trustMetricStrategy = trustMetricStrategy;
	}

	public void updateTrustKnowledge(TrustKnowledge trustKnowledge) {
		trustMetricStrategy.updateTrustKnowledge(trustKnowledge);
	}

	public double propositionEvaluation(Task task) {
		return trustMetricStrategy.propositionEvaluation(task);

	}

	public double resultEvaluation(Task task) {
		return trustMetricStrategy.resultEvaluation(task);
	}

}
