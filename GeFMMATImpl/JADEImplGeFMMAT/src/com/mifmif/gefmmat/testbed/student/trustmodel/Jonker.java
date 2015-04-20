package com.mifmif.gefmmat.testbed.student.trustmodel;

import com.mifmif.gefmmat.core.Feature;
import com.mifmif.gefmmat.core.FeatureEvaluation;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.trust.ITrustMetric;
import com.mifmif.gefmmat.core.trust.TrustKnowledge;

/**
 * Jonker trust model use the last two experience with agent and decide whether if the partner is trustworthy/untrustworthy/indifferent if the two passed
 * experience are positive , then it's a trusted agent, if they are negative experiences , then it's untrusted, is one is positive and other is negative then
 * it's Indifferent
 * 
 * @author y.mifrah
 *
 */
public class Jonker implements ITrustMetric {

	@Override
	public void updateTrustKnowledge(TrustKnowledge trustKnowledge, Task task) {
		trustKnowledge.getAgentExperience().getProcessedTasks().add(task);
		boolean isResultTaskValid = task.getResult().isValid();
		for (Feature feature : task.getFeatures()) {
			FeatureEvaluation featureEvaluation = trustKnowledge.getEvaluation(feature);
			double b = featureEvaluation.getEvaluation().getBelief();
			double d = featureEvaluation.getEvaluation().getDisbelief();
			double u = featureEvaluation.getEvaluation().getUncertainty();
			if (isResultTaskValid) {
				if (d == 1) {
					b = 0;
					d = 0;
					u = 1;
				} else if (u == 1) {
					b = 1;
					d = 0;
					u = 0;
				}
			} else {
				if (b == 1) {
					b = 0;
					d = 0;
					u = 1;
				} else if (u == 1) {
					b = 0;
					d = 1;
					u = 0;
				}
			}
			featureEvaluation.getEvaluation().setBelief(b);
			featureEvaluation.getEvaluation().setDisbelief(d);
			featureEvaluation.getEvaluation().setUncertainty(u);
			featureEvaluation.incrementUpdateCount();

		}
	}

	@Override
	public double propositionEvaluation(Task task) {
		return 0;
	}

	@Override
	public double resultEvaluation(Task task) {
		return 0;
	}

	@Override
	public String getTrustMetricName() {
		return "Jonker";
	}

}
