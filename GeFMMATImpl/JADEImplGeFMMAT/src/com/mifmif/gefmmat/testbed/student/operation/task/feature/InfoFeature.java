package com.mifmif.gefmmat.testbed.student.operation.task.feature;

import com.mifmif.gefmmat.core.Feature;

public class InfoFeature extends Feature {

	private static InfoFeature instance;

	public static InfoFeature getInstance() {
		if (instance == null)
			instance = new InfoFeature();
		return instance;
	}

	public InfoFeature() {

		setName("Info");
	}
}
