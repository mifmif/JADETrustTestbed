package com.mifmif.gefmmat.util;

import java.util.List;

import com.mifmif.gefmmat.core.AgentExperience;

/**
 * @author y.mifrah
 *
 */
public class Measure {
	private Measure() {
	}

	/**
	 * prepare and return the confusion matrix int the form :
	 * <table border='1'>
	 * <tr>
	 * <td>TP</td>
	 * <td>TN</td>
	 * <tr>
	 * <tr>
	 * <td>FP</td>
	 * <td>FN</td>
	 * <tr>
	 * </table>
	 * 
	 * @return
	 */
	public static int[][] prepareConfusionMatrix(List<AgentExperience> agentExperiences) {
		int[][] confusionMatrix = new int[2][2];
		int tp = 0, tn = 0, fp = 0, fn = 0;

		for (AgentExperience experience : agentExperiences) {
			SubjectiveLogicValue featureEvaluation = experience.getMeanFeatureEvaluation();
			if (featureEvaluation.getBelief() > 0) {
				if (featureEvaluation.getDisbelief() == 0 || featureEvaluation.getBelief() / featureEvaluation.getDisbelief() > 2
						&& featureEvaluation.getUncertainty() <= 0.5) {// in this case the agent is considered trustworthy
					if (isTrusteeHonestAgentName(experience)) {
						tp++;
					} else {
						fp++;
					}
				} else if (featureEvaluation.getDisbelief() / featureEvaluation.getBelief() > 1) {
					if (!isTrusteeHonestAgentName(experience)) {// in this case the agent is considered untrustworthy
						tn++;
					} else {
						fn++;
					}
				}
			} else {
				if (featureEvaluation.getDisbelief() > 0) {// in this case the agent is considered untrustworthy
					if (!isTrusteeHonestAgentName(experience)) {
						tn++;
					} else {
						fn++;
					}
				} else {// in this case the agent is considered trustworthy
					if (isTrusteeHonestAgentName(experience)) {
						tp++;
					} else {
						fp++;
					}
				}

			}
		}

		confusionMatrix[1][1] = tp;
		confusionMatrix[1][0] = tn;
		confusionMatrix[0][1] = fp;
		confusionMatrix[0][0] = fn;
		return confusionMatrix;
	}

	public static double calculateMCC(List<AgentExperience> agentExperiences) {
		int[][] confusionMatrix = prepareConfusionMatrix(agentExperiences);
		double mcc = 0;
		int tp, tn, fp, fn;
		tp = confusionMatrix[1][1];
		tn = confusionMatrix[1][0];
		fp = confusionMatrix[0][1];
		fn = confusionMatrix[0][0];
		mcc = ((tp * tn) - (fp * fn)) / Math.sqrt((tp + fp) * (tp + fn) * (tn + fp) * (tn + fn));
		return mcc;
	}

	private static boolean isTrusteeHonestAgentName(AgentExperience experience) {
		return (experience.getTrusteeAgent().getName().startsWith("honest"));
	}
}
