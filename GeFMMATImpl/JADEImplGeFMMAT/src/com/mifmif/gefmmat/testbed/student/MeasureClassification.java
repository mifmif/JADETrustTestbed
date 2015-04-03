package com.mifmif.gefmmat.testbed.student;

import java.util.ArrayList;

/**
 * This class implement the Matthews correlation coefficient method to evaluate
 * the quality of a binary classifications
 * 
 * @author y.mifrah
 *
 */
public class MeasureClassification {
	private static int NUMBER_DISHONEST_STUDENTS;
	private static int NUMBER_HONEST_STUDENTS;

	private ArrayList<Integer> confusionMatrix(ArrayList<Double> trustEvaluations) {
		// true positive, false negative, false positive, true negative,
		ArrayList<Integer> confMtrx = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			confMtrx.add(0);
		}
		for (int k = 0; k < trustEvaluations.size(); k++) {
			int agentId = k;
			if (agentId >= NUMBER_DISHONEST_STUDENTS) { // ground
				// truth:
				// honest
				// advisors
				if (trustEvaluations.get(agentId) > 0.5) // true positive
				{
					confMtrx.set(0, confMtrx.get(0) + 1);
				} else if (trustEvaluations.get(agentId) < 0.5) // false
																// negative
				{
					confMtrx.set(1, confMtrx.get(1) + 1);
				}
			} else { // ground truth: dishonest advisors
				if (trustEvaluations.get(agentId) > 0.5) // false positive
				{
					confMtrx.set(2, confMtrx.get(2) + 1);
				} else if (trustEvaluations.get(agentId) < 0.5) // true negative
				{
					confMtrx.set(3, confMtrx.get(3) + 1);
				}
			}
		}
		return confMtrx;
	}

	public double calculateMCC(ArrayList<Double> trustEvaluations) {

		double MCC = 0.0;
		double trustPositive, falseNegative, falsePositive, trustNegative;
		trustPositive = falseNegative = falsePositive = trustNegative = 0;

		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trustPositive += confMtrx.get(0);
		falseNegative += confMtrx.get(1);
		falsePositive += confMtrx.get(2);
		trustNegative += confMtrx.get(3);

		if (trustPositive == 0)
			trustPositive += 1;
		if (falsePositive == 0)
			falsePositive += 1;
		if (trustNegative == 0)
			trustNegative += 1;
		if (falseNegative == 0)
			falseNegative += 1;
		MCC = (trustPositive * trustNegative - falsePositive * falseNegative)
				/ Math.sqrt((trustPositive + falsePositive) * (trustPositive + falseNegative) * (trustNegative + falsePositive)
						* (trustNegative + falseNegative));
		return MCC;
	}

	public double calculateFalseNegativeR(ArrayList<Double> trustEvaluations) {
		double trusPositive, falseNegative;
		trusPositive = falseNegative = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trusPositive += confMtrx.get(0);
		falseNegative += confMtrx.get(1);

		if (trusPositive == 0)
			trusPositive += 1;
		if (falseNegative == 0)
			falseNegative += 1;
		double falseNegativer = falseNegative / (falseNegative + trusPositive);
		return falseNegativer;
	}

	public double calculateFalsePositiveR(ArrayList<Double> trustEvaluations) {
		double falsePositive, trustNegative;
		falsePositive = trustNegative = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);

		falsePositive += confMtrx.get(2);
		trustNegative += confMtrx.get(3);
		if (falsePositive == 0)
			falsePositive += 1;
		if (trustNegative == 0)
			trustNegative += 1;
		double falsePositiver = falsePositive / (trustNegative + falsePositive);
		return falsePositiver;
	}

	public double calculateAccuracy(ArrayList<Double> trustEvaluations) {
		double trustPositive, falseNegative, falsePositive, trustNegative;
		trustPositive = falseNegative = falsePositive = trustNegative = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trustPositive += confMtrx.get(0);
		falseNegative += confMtrx.get(1);
		falsePositive += confMtrx.get(2);
		trustNegative += confMtrx.get(3);
		if (trustPositive == 0)
			trustPositive += 1;
		if (falsePositive == 0)
			falsePositive += 1;
		if (trustNegative == 0)
			trustNegative += 1;
		if (falseNegative == 0)
			falseNegative += 1;
		double accuracy = (trustNegative + trustPositive) / (trustNegative + falsePositive + falseNegative + trustPositive);
		return accuracy;
	}

	public double calculatePrecision(ArrayList<Double> trustEvaluations) {
		double trustPositive, falsePositive;
		trustPositive = falsePositive = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trustPositive += confMtrx.get(0);
		falsePositive += confMtrx.get(2);
		if (trustPositive == 0)
			trustPositive += 1;
		if (falsePositive == 0)
			falsePositive += 1;

		double prec = trustPositive / (falsePositive + trustPositive);
		return prec;
	}

	public double calculateF(ArrayList<Double> trustEvaluations) {
		double trustPositive, falseNegative, falsePositive;
		trustPositive = falseNegative = falsePositive = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trustPositive += confMtrx.get(0);
		falseNegative += confMtrx.get(1);
		falsePositive += confMtrx.get(2);
		if (trustPositive == 0)
			trustPositive += 1;
		if (falsePositive == 0)
			falsePositive += 1;
		if (falseNegative == 0)
			falseNegative += 1;
		double prec = trustPositive / (falsePositive + trustPositive);
		double recall = trustPositive / (trustPositive + falseNegative);
		double f = 2 * ((prec * recall) / (prec + recall));
		return f;
	}

	public double calculateTrustPositiveR(ArrayList<Double> trustEvaluations) {
		double trustPositive, falseNegative, falsePositive;
		trustPositive = falseNegative = falsePositive = 0;
		ArrayList<Integer> confMtrx = confusionMatrix(trustEvaluations);
		trustPositive += confMtrx.get(0);
		falseNegative += confMtrx.get(1);
		falsePositive += confMtrx.get(2);
		if (trustPositive == 0)
			trustPositive += 1;
		if (falsePositive == 0)
			falsePositive += 1;
		if (falseNegative == 0)
			falseNegative += 1;
		double recall = trustPositive / (trustPositive + falseNegative);
		return recall;
	}

}
