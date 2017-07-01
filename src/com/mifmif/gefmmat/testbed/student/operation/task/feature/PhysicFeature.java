package com.mifmif.gefmmat.testbed.student.operation.task.feature;

import com.mifmif.gefmmat.core.Feature;

public class PhysicFeature extends Feature {
	private static PhysicFeature instance;

	public static PhysicFeature getInstance() {
		if (instance == null)
			instance = new PhysicFeature();
		return instance;
	}

	public PhysicFeature() {
		setName("Physic");
	}
}
