package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.CountryCapital;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.GeoFeature;

public class CountryCapitalTask extends Task {
	public CountryCapitalTask() {
		addFeature(GeoFeature.getInstance());
		setServiceName("countryCapital");
	}

	public String getCountry() throws InvalidInputParameterException {
		String country = null;
		country = getInputs().get("country");
		if (country == null) {
			throw new InvalidInputParameterException();
		}
		return country;
	}

	public void setCountry(String country) {
		getInputs().put("country", "" + country);
	}

	public static CountryCapitalTask generate() {
		CountryCapitalTask task = new CountryCapitalTask();
		String country = CountryCapital.getRandomCountry();
		task.setCountry(country);
		return task;
	}

	public static class CountryCapitalResult extends Result {
		public void setCapitalResult(String capitalResult) {
			getOutputs().put("capitalResult", "" + capitalResult);
		}

		public String getCapitalResultResult() {
			return getOutputs().get("capitalResult");
		}

	}
}
