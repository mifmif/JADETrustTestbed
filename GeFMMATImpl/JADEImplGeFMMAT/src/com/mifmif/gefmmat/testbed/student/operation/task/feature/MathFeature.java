package com.mifmif.gefmmat.testbed.student.operation.task.feature;

import com.mifmif.gefmmat.core.Feature;

public class MathFeature extends Feature {
	private static MathFeature instance;

	public static MathFeature getInstance() {
		if (instance == null)
			instance = new MathFeature();
		return instance;
	}
	public MathFeature() {
		setName("Math");
	}
}
