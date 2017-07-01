package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.CountryCapital;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.GeoFeature;

public class CountryContinentTask extends Task {
	public CountryContinentTask() {
		addFeature(GeoFeature.getInstance());
		setServiceName("countryContinent");
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

	public static CountryContinentTask generate() {
		CountryContinentTask task = new CountryContinentTask();
		String country = CountryCapital.getRandomCountry();
		task.setCountry(country);
		return task;
	}

	public static class CountryContinentResult extends Result {
		public void setContinentResult(String continentResult) {
			getOutputs().put("continentResult", "" + continentResult);
		}

		public String getContinentResultResult() {
			return getOutputs().get("continentResult");
		}

	}
}
