package com.mifmif.gefmmat.testbed.student.operation.task.feature;

import com.mifmif.gefmmat.core.Feature;

public class GeoFeature extends Feature {
	private static GeoFeature instance;

	public static GeoFeature getInstance() {
		if (instance == null)
			instance = new GeoFeature();
		return instance;
	}

	public GeoFeature() {
		setName("Geo");
	}
}
