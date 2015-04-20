/**
 * Copyright 2015 y.mifrah
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mifmif.gefmmat.testbed.student.operation;

import java.util.HashMap;
import java.util.Map;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.task.CountryCapitalTask;
import com.mifmif.gefmmat.testbed.student.operation.task.CountryCapitalTask.CountryCapitalResult;
import com.mifmif.gefmmat.testbed.student.operation.task.CountryContinentTask;
import com.mifmif.gefmmat.testbed.student.operation.task.CountryContinentTask.CountryContinentResult;

/**
 * @author y.mifrah
 *
 */
public class CountryContinent extends Service {
	private static Map<String, String> continentCountry = new HashMap<>();
	String country, continentResult;

	public CountryContinent() {
		setName("countryContinent");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			country = ((CountryContinentTask) task).getCountry();
			if (country == null) {
				throw new InvalidInputParameterException();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		CountryContinentResult result = new CountryContinentResult();
		continentResult = continentCountry.get(country);
		result.setContinentResult(continentResult);
		return result;
	}

	static {
		continentCountry.put("Algeria", "Africa");
		continentCountry.put("Angola", "Africa");
		continentCountry.put("Benin", "Africa");
		continentCountry.put("Botswana", "Africa");
		continentCountry.put("Burkina", "Africa");
		continentCountry.put("Burundi", "Africa");
		continentCountry.put("Cameroon", "Africa");
		continentCountry.put("Cape Verde", "Africa");
		continentCountry.put("Central African Republic", "Africa");
		continentCountry.put("Chad", "Africa");
		continentCountry.put("Comoros", "Africa");
		continentCountry.put("Congo", "Africa");
		continentCountry.put("Djibouti", "Africa");
		continentCountry.put("Egypt", "Africa");
		continentCountry.put("Equatorial Guinea", "Africa");
		continentCountry.put("Eritrea", "Africa");
		continentCountry.put("Ethiopia", "Africa");
		continentCountry.put("Gabon", "Africa");
		continentCountry.put("Gambia", "Africa");
		continentCountry.put("Ghana", "Africa");
		continentCountry.put("Guinea", "Africa");
		continentCountry.put("Guinea-Bissau", "Africa");
		continentCountry.put("Ivory Coast", "Africa");
		continentCountry.put("Kenya", "Africa");
		continentCountry.put("Lesotho", "Africa");
		continentCountry.put("Liberia", "Africa");
		continentCountry.put("Libya", "Africa");
		continentCountry.put("Madagascar", "Africa");
		continentCountry.put("Malawi", "Africa");
		continentCountry.put("Mali", "Africa");
		continentCountry.put("Mauritania", "Africa");
		continentCountry.put("Mauritius", "Africa");
		continentCountry.put("Morocco", "Africa");
		continentCountry.put("Mozambique", "Africa");
		continentCountry.put("Namibia", "Africa");
		continentCountry.put("Niger", "Africa");
		continentCountry.put("Nigeria", "Africa");
		continentCountry.put("Rwanda", "Africa");
		continentCountry.put("Sao Tome and Principe", "Africa");
		continentCountry.put("Senegal", "Africa");
		continentCountry.put("Seychelles", "Africa");
		continentCountry.put("Sierra Leone", "Africa");
		continentCountry.put("Somalia", "Africa");
		continentCountry.put("South Africa", "Africa");
		continentCountry.put("South Sudan", "Africa");
		continentCountry.put("Sudan", "Africa");
		continentCountry.put("Swaziland", "Africa");
		continentCountry.put("Tanzania", "Africa");
		continentCountry.put("Togo", "Africa");
		continentCountry.put("Tunisia", "Africa");
		continentCountry.put("Uganda", "Africa");
		continentCountry.put("Zambia", "Africa");
		continentCountry.put("Zimbabwe", "Africa");
		continentCountry.put("Afghanistan", "Asia");
		continentCountry.put("Bahrain", "Asia");
		continentCountry.put("Bangladesh", "Asia");
		continentCountry.put("Bhutan", "Asia");
		continentCountry.put("Brunei", "Asia");
		continentCountry.put("Burma (Myanmar)", "Asia");
		continentCountry.put("Cambodia", "Asia");
		continentCountry.put("China", "Asia");
		continentCountry.put("East Timor", "Asia");
		continentCountry.put("India", "Asia");
		continentCountry.put("Indonesia", "Asia");
		continentCountry.put("Iran", "Asia");
		continentCountry.put("Iraq", "Asia");
		continentCountry.put("Israel", "Asia");
		continentCountry.put("Japan", "Asia");
		continentCountry.put("Jordan", "Asia");
		continentCountry.put("Kazakhstan", "Asia");
		continentCountry.put("Kuwait", "Asia");
		continentCountry.put("Kyrgyzstan", "Asia");
		continentCountry.put("Laos", "Asia");
		continentCountry.put("Lebanon", "Asia");
		continentCountry.put("Malaysia", "Asia");
		continentCountry.put("Maldives", "Asia");
		continentCountry.put("Mongolia", "Asia");
		continentCountry.put("Nepal", "Asia");
		continentCountry.put("Oman", "Asia");
		continentCountry.put("Pakistan", "Asia");
		continentCountry.put("Philippines", "Asia");
		continentCountry.put("Qatar", "Asia");
		continentCountry.put("Russian Federation", "Asia");
		continentCountry.put("Saudi Arabia", "Asia");
		continentCountry.put("Singapore", "Asia");
		continentCountry.put("Sri Lanka", "Asia");
		continentCountry.put("Syria", "Asia");
		continentCountry.put("Tajikistan", "Asia");
		continentCountry.put("Thailand", "Asia");
		continentCountry.put("Turkey", "Asia");
		continentCountry.put("Turkmenistan", "Asia");
		continentCountry.put("United Arab Emirates", "Asia");
		continentCountry.put("Uzbekistan", "Asia");
		continentCountry.put("Vietnam", "Asia");
		continentCountry.put("Yemen", "Asia");
		continentCountry.put("Albania", "Europe");
		continentCountry.put("Andorra", "Europe");
		continentCountry.put("Armenia", "Europe");
		continentCountry.put("Austria", "Europe");
		continentCountry.put("Azerbaijan", "Europe");
		continentCountry.put("Belarus", "Europe");
		continentCountry.put("Belgium", "Europe");
		continentCountry.put("Bosnia and Herzegovina", "Europe");
		continentCountry.put("Bulgaria", "Europe");
		continentCountry.put("Croatia", "Europe");
		continentCountry.put("Cyprus", "Europe");
		continentCountry.put("Czech Republic", "Europe");
		continentCountry.put("Denmark", "Europe");
		continentCountry.put("Estonia", "Europe");
		continentCountry.put("Finland", "Europe");
		continentCountry.put("France", "Europe");
		continentCountry.put("Georgia", "Europe");
		continentCountry.put("Germany", "Europe");
		continentCountry.put("Greece", "Europe");
		continentCountry.put("Hungary", "Europe");
		continentCountry.put("Iceland", "Europe");
		continentCountry.put("Ireland", "Europe");
		continentCountry.put("Italy", "Europe");
		continentCountry.put("Latvia", "Europe");
		continentCountry.put("Liechtenstein", "Europe");
		continentCountry.put("Lithuania", "Europe");
		continentCountry.put("Luxembourg", "Europe");
		continentCountry.put("Macedonia", "Europe");
		continentCountry.put("Malta", "Europe");
		continentCountry.put("Moldova", "Europe");
		continentCountry.put("Monaco", "Europe");
		continentCountry.put("Montenegro", "Europe");
		continentCountry.put("Netherlands", "Europe");
		continentCountry.put("Norway", "Europe");
		continentCountry.put("Poland", "Europe");
		continentCountry.put("Portugal", "Europe");
		continentCountry.put("Romania", "Europe");
		continentCountry.put("San Marino", "Europe");
		continentCountry.put("Serbia", "Europe");
		continentCountry.put("Slovakia", "Europe");
		continentCountry.put("Slovenia", "Europe");
		continentCountry.put("Spain", "Europe");
		continentCountry.put("Sweden", "Europe");
		continentCountry.put("Switzerland", "Europe");
		continentCountry.put("Ukraine", "Europe");
		continentCountry.put("United Kingdom", "Europe");
		continentCountry.put("Vatican City", "Europe");
		continentCountry.put("Antigua and Barbuda", "North America");
		continentCountry.put("Bahamas", "North America");
		continentCountry.put("Barbados", "North America");
		continentCountry.put("Belize", "North America");
		continentCountry.put("Canada", "North America");
		continentCountry.put("Costa Rica", "North America");
		continentCountry.put("Cuba", "North America");
		continentCountry.put("Dominica", "North America");
		continentCountry.put("Dominican Republic", "North America");
		continentCountry.put("El Salvador", "North America");
		continentCountry.put("Grenada", "North America");
		continentCountry.put("Guatemala", "North America");
		continentCountry.put("Haiti", "North America");
		continentCountry.put("Honduras", "North America");
		continentCountry.put("Jamaica", "North America");
		continentCountry.put("Mexico", "North America");
		continentCountry.put("Nicaragua", "North America");
		continentCountry.put("Panama", "North America");
		continentCountry.put("Saint Kitts and Nevis", "North America");
		continentCountry.put("Saint Lucia", "North America");
		continentCountry.put("Trinidad and Tobago", "North America");
		continentCountry.put("United States", "North America");
		continentCountry.put("Australia", "Oceania");
		continentCountry.put("Fiji", "Oceania");
		continentCountry.put("Kiribati", "Oceania");
		continentCountry.put("Marshall Islands", "Oceania");
		continentCountry.put("Micronesia", "Oceania");
		continentCountry.put("Nauru", "Oceania");
		continentCountry.put("New Zealand", "Oceania");
		continentCountry.put("Palau", "Oceania");
		continentCountry.put("Papua New Guinea", "Oceania");
		continentCountry.put("Samoa", "Oceania");
		continentCountry.put("Solomon Islands", "Oceania");
		continentCountry.put("Tonga", "Oceania");
		continentCountry.put("Tuvalu", "Oceania");
		continentCountry.put("Vanuatu", "Oceania");
		continentCountry.put("Argentina", "South America");
		continentCountry.put("Bolivia", "South America");
		continentCountry.put("Brazil", "South America");
		continentCountry.put("Chile", "South America");
		continentCountry.put("Colombia", "South America");
		continentCountry.put("Ecuador", "South America");
		continentCountry.put("Guyana", "South America");
		continentCountry.put("Paraguay", "South America");
		continentCountry.put("Peru", "South America");
		continentCountry.put("Suriname", "South America");
		continentCountry.put("Uruguay", "South America");
		continentCountry.put("Venezuela", "South America");
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			CountryContinentResult curResult = (CountryContinentResult) task.getResult();
			CountryContinentResult validResult = (CountryContinentResult) execute(task);
			return curResult.getContinentResultResult().equals(validResult.getContinentResultResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
