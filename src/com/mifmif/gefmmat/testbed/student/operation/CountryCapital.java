/**
 * Copyright 2015 y.mifrah
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
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
import com.mifmif.gefmmat.testbed.student.operation.task.SpeedTask.SpeedResult;

/**
 * @author y.mifrah
 *
 */
public class CountryCapital extends Service {
	protected static Map<String, String> countryCapital = new HashMap<>();
	static int countCountries;
	String country, capitalResult;

	public CountryCapital() {
		setName("countryCapital");
	}

	@Override
	protected void prepareInputs(Task task) throws InvalidInputParameterException {
		try {
			country = ((CountryCapitalTask) task).getCountry();
			if (country == null) {
				throw new InvalidInputParameterException();
			}
		} catch (Exception exception) {
			throw new InvalidInputParameterException();
		}
	}

	@Override
	protected Result processTask(Task task) throws TaskProcessingException {
		CountryCapitalResult result = new CountryCapitalResult();
		capitalResult = countryCapital.get(country);
		result.setCapitalResult(capitalResult);
		return result;
	}

	static {
		countryCapital.put("Aruba", "Oranjestad");
		countryCapital.put("Ascension Island", "Georgetown");
		countryCapital.put("Afghanistan", "Kabul");
		countryCapital.put("Akrotiri and Dhekelia", "Episkopi Cantonment");
		countryCapital.put("Albania", "Tirana");
		countryCapital.put("Algeria", "Algiers");
		countryCapital.put("American Samoa", "Pago Pago");
		countryCapital.put("Andorra", "Andorra la Vella");
		countryCapital.put("Angola", "Luanda");
		countryCapital.put("Anguilla", "The Valley");
		countryCapital.put("Antigua and Barbuda", "St. John's");
		countryCapital.put("Argentina", "Buenos Aires");
		countryCapital.put("Armenia", "Yerevan");
		countryCapital.put("Australia", "Canberra");
		countryCapital.put("Austria", "Vienna");
		countryCapital.put("Azerbaijan", "Baku");
		countryCapital.put("Bahamas", "Nassau");
		countryCapital.put("Bahrain", "Manama");
		countryCapital.put("Bangladesh", "Dhaka");
		countryCapital.put("Barbados", "Bridgetown");
		countryCapital.put("Belarus", "Minsk");
		countryCapital.put("Belgium", "Brussels");
		countryCapital.put("Belize", "Belmopan");
		countryCapital.put("Benin", "Porto-Novo");
		countryCapital.put("Bermuda", "Hamilton");
		countryCapital.put("Bhutan", "Thimphu");
		countryCapital.put("Bolivia", "Sucre");
		countryCapital.put("Bolivia", "La Paz");
		countryCapital.put("Bosnia and Herzegovina", "Sarajevo");
		countryCapital.put("Botswana", "Gaborone");
		countryCapital.put("Brazil", "Brasília");
		countryCapital.put("British Virgin Islands", "Road Town");
		countryCapital.put("Brunei", "Bandar Seri Begawan");
		countryCapital.put("Bulgaria", "Sofia");
		countryCapital.put("Burkina Faso", "Ouagadougou");
		countryCapital.put("Burundi", "Bujumbura");
		countryCapital.put("Cambodia", "Phnom Penh");
		countryCapital.put("Cameroon", "Yaoundé");
		countryCapital.put("Canada", "Ottawa");
		countryCapital.put("Cape Verde", "Praia");
		countryCapital.put("Cayman Islands", "George Town");
		countryCapital.put("Central African Republic", "Bangui");
		countryCapital.put("Chad", "N'Djamena");
		countryCapital.put("Chile", "Santiago");
		countryCapital.put("China", "Beijing");
		countryCapital.put("Christmas Island", "Flying Fish Cove");
		countryCapital.put("Cocos (Keeling) Islands", "West Island");
		countryCapital.put("Colombia", "Bogotá");
		countryCapital.put("Comoros", "Moroni");
		countryCapital.put("Cook Islands", "Avarua");
		countryCapital.put("Costa Rica", "San José");
		countryCapital.put("Croatia", "Zagreb");
		countryCapital.put("Cuba", "Havana");
		countryCapital.put("Curaçao", "Willemstad");
		countryCapital.put("Cyprus", "Nicosia");
		countryCapital.put("Czech Republic", "Prague");
		countryCapital.put("Côte d'Ivoire", "Yamoussoukro");
		countryCapital.put("Democratic Republic of the Congo", "Kinshasa");
		countryCapital.put("Denmark", "Copenhagen");
		countryCapital.put("Djibouti", "Djibouti");
		countryCapital.put("Dominica", "Roseau");
		countryCapital.put("Dominican Republic", "Santo Domingo");
		countryCapital.put("East Timor (Timor-Leste)", "Dili");
		countryCapital.put("Easter Island", "Hanga Roa");
		countryCapital.put("Ecuador", "Quito");
		countryCapital.put("Egypt", "Cairo");
		countryCapital.put("El Salvador", "San Salvador");
		countryCapital.put("Equatorial Guinea", "Malabo");
		countryCapital.put("Eritrea", "Asmara");
		countryCapital.put("Estonia", "Tallinn");
		countryCapital.put("Ethiopia", "Addis Ababa");
		countryCapital.put("Falkland Islands", "Stanley");
		countryCapital.put("Faroe Islands", "Tórshavn");
		countryCapital.put("Federated States of Micronesia", "Palikir");
		countryCapital.put("Fiji", "Suva");
		countryCapital.put("Finland", "Helsinki");
		countryCapital.put("France", "Paris");
		countryCapital.put("French Guiana", "Cayenne");
		countryCapital.put("French Polynesia", "Papeete");
		countryCapital.put("Gabon", "Libreville");
		countryCapital.put("Gambia", "Banjul");
		countryCapital.put("Georgia", "Tbilisi");
		countryCapital.put("Germany", "Berlin");
		countryCapital.put("Ghana", "Accra");
		countryCapital.put("Gibraltar", "Gibraltar");
		countryCapital.put("Greece", "Athens");
		countryCapital.put("Greenland", "Nuuk");
		countryCapital.put("Grenada", "St. George's");
		countryCapital.put("Guam", "Hagåtña");
		countryCapital.put("Guatemala", "Guatemala City");
		countryCapital.put("Guernsey", "St. Peter Port");
		countryCapital.put("Guinea", "Conakry");
		countryCapital.put("Guinea-Bissau", "Bissau");
		countryCapital.put("Guyana", "Georgetown");
		countryCapital.put("Haiti", "Port-au-Prince");
		countryCapital.put("Honduras", "Tegucigalpa");
		countryCapital.put("Hungary", "Budapest");
		countryCapital.put("Iceland", "Reykjavík");
		countryCapital.put("India", "New Delhi");
		countryCapital.put("Indonesia", "Jakarta");
		countryCapital.put("Iran", "Tehran");
		countryCapital.put("Iraq", "Baghdad");
		countryCapital.put("Ireland", "Dublin");
		countryCapital.put("Isle of Man", "Douglas");
		countryCapital.put("Israel", "Jerusalem");
		countryCapital.put("Italy", "Rome");
		countryCapital.put("Jamaica", "Kingston");
		countryCapital.put("Japan", "Tokyo");
		countryCapital.put("Jersey", "St. Helier");
		countryCapital.put("Jordan", "Amman");
		countryCapital.put("Kazakhstan", "Astana");
		countryCapital.put("Kenya", "Nairobi");
		countryCapital.put("Kiribati", "Tarawa");
		countryCapital.put("Kosovo", "Pristina");
		countryCapital.put("Kuwait", "Kuwait City");
		countryCapital.put("Kyrgyzstan", "Bishkek");
		countryCapital.put("Laos", "Vientiane");
		countryCapital.put("Latvia", "Riga");
		countryCapital.put("Lebanon", "Beirut");
		countryCapital.put("Lesotho", "Maseru");
		countryCapital.put("Liberia", "Monrovia");
		countryCapital.put("Libya", "Tripoli");
		countryCapital.put("Liechtenstein", "Vaduz");
		countryCapital.put("Lithuania", "Vilnius");
		countryCapital.put("Luxembourg", "Luxembourg");
		countryCapital.put("Macedonia", "Skopje");
		countryCapital.put("Madagascar", "Antananarivo");
		countryCapital.put("Malawi", "Lilongwe");
		countryCapital.put("Malaysia", "Kuala Lumpur");
		countryCapital.put("Maldives", "Malé");
		countryCapital.put("Mali", "Bamako");
		countryCapital.put("Malta", "Valletta");
		countryCapital.put("Marshall Islands", "Majuro");
		countryCapital.put("Mauritania", "Nouakchott");
		countryCapital.put("Mauritius", "Port Louis");
		countryCapital.put("Mexico", "Mexico City");
		countryCapital.put("Moldova", "Chisinau");
		countryCapital.put("Monaco", "Monaco");
		countryCapital.put("Mongolia", "Ulaanbaatar");
		countryCapital.put("Montenegro", "Podgorica");
		countryCapital.put("Montserrat", "Plymouth");
		countryCapital.put("Morocco", "Rabat");
		countryCapital.put("Mozambique", "Maputo");
		countryCapital.put("Myanmar", "Naypyidaw");
		countryCapital.put("Nagorno-Karabakh Republic", "Stepanakert");
		countryCapital.put("Namibia", "Windhoek");
		countryCapital.put("Nauru", "Yaren");
		countryCapital.put("Nepal", "Kathmandu");
		countryCapital.put("Netherlands", "Amsterdam");
		countryCapital.put("New Caledonia", "Nouméa");
		countryCapital.put("New Zealand", "Wellington");
		countryCapital.put("Nicaragua", "Managua");
		countryCapital.put("Niger", "Niamey");
		countryCapital.put("Nigeria", "Abuja");
		countryCapital.put("Niue", "Alofi");
		countryCapital.put("Norfolk Island", "Kingston");
		countryCapital.put("North Korea", "Pyongyang");
		countryCapital.put("Northern Cyprus", "Nicosia");
		countryCapital.put("United Kingdom Northern Ireland", "Belfast");
		countryCapital.put("Northern Mariana Islands", "Saipan");
		countryCapital.put("Norway", "Oslo");
		countryCapital.put("Oman", "Muscat");
		countryCapital.put("Pakistan", "Islamabad");
		countryCapital.put("Palau", "Ngerulmud");
		countryCapital.put("Palestine", "Jerusalem");
		countryCapital.put("Panama", "Panama City");
		countryCapital.put("Papua New Guinea", "Port Moresby");
		countryCapital.put("Paraguay", "Asunción");
		countryCapital.put("Peru", "Lima");
		countryCapital.put("Philippines", "Manila");
		countryCapital.put("Pitcairn Islands", "Adamstown");
		countryCapital.put("Poland", "Warsaw");
		countryCapital.put("Portugal", "Lisbon");
		countryCapital.put("Puerto Rico", "San Juan");
		countryCapital.put("Qatar", "Doha");
		countryCapital.put("Republic of China (Taiwan)", "Taipei");
		countryCapital.put("Republic of the Congo", "Brazzaville");
		countryCapital.put("Romania", "Bucharest");
		countryCapital.put("Russia", "Moscow");
		countryCapital.put("Rwanda", "Kigali");
		countryCapital.put("Saint Barthélemy", "Gustavia");
		countryCapital.put("Saint Helena", "Jamestown");
		countryCapital.put("Saint Kitts and Nevis", "Basseterre");
		countryCapital.put("Saint Lucia", "Castries");
		countryCapital.put("Saint Martin", "Marigot");
		countryCapital.put("Saint Pierre and Miquelon", "St. Pierre");
		countryCapital.put("Saint Vincent and the Grenadines", "Kingstown");
		countryCapital.put("Samoa", "Apia");
		countryCapital.put("San Marino", "San Marino");
		countryCapital.put("Saudi Arabia", "Riyadh");
		countryCapital.put("Scotland", "Edinburgh");
		countryCapital.put("Senegal", "Dakar");
		countryCapital.put("Serbia", "Belgrade");
		countryCapital.put("Seychelles", "Victoria");
		countryCapital.put("Sierra Leone", "Freetown");
		countryCapital.put("Singapore", "Singapore");
		countryCapital.put("Sint Maarten", "Philipsburg");
		countryCapital.put("Slovakia", "Bratislava");
		countryCapital.put("Slovenia", "Ljubljana");
		countryCapital.put("Solomon Islands", "Honiara");
		countryCapital.put("Somalia", "Mogadishu");
		countryCapital.put("Somaliland", "Hargeisa");
		countryCapital.put("South Africa", "Pretoria");
		countryCapital.put("South Georgia and the South Sandwich Islands", "Grytviken");
		countryCapital.put("South Korea", "Seoul");
		countryCapital.put("South Ossetia", "Tskhinvali");
		countryCapital.put("South Sudan South Sudan", "Juba");
		countryCapital.put("Spain", "Madrid");
		countryCapital.put("Sri Lanka", "Sri Jayawardenapura Kotte");
		countryCapital.put("Sudan", "Khartoum");
		countryCapital.put("Suriname", "Paramaribo");
		countryCapital.put("Swaziland", "Mbabane");
		countryCapital.put("Sweden", "Stockholm");
		countryCapital.put("Switzerland", "Bern");
		countryCapital.put("Syria", "Damascus");
		countryCapital.put("São Tomé and Príncipe", "São Tomé");
		countryCapital.put("Tajikistan", "Dushanbe");
		countryCapital.put("Tanzania", "Dodoma");
		countryCapital.put("Thailand", "Bangkok");
		countryCapital.put("Togo", "Lomé");
		countryCapital.put("Tonga", "Nukuʻalofa");
		countryCapital.put("Transnistria", "Tiraspol");
		countryCapital.put("Trinidad and Tobago", "Port of Spain");
		countryCapital.put("Tristan da Cunha", "Edinburgh of the Seven Seas");
		countryCapital.put("Tunisia", "Tunis");
		countryCapital.put("Turkey", "Ankara");
		countryCapital.put("Turkmenistan", "Ashgabat");
		countryCapital.put("Turks and Caicos Islands", "Cockburn Town");
		countryCapital.put("Tuvalu", "Funafuti");
		countryCapital.put("Uganda", "Kampala");
		countryCapital.put("Ukraine", "Kiev");
		countryCapital.put("United Arab Emirates", "Abu Dhabi");
		countryCapital.put("United Kingdom; England", "London");
		countryCapital.put("United States", "Washington, D.C.");
		countryCapital.put("United States Virgin Islands", "Charlotte Amalie");
		countryCapital.put("Uruguay", "Montevideo");
		countryCapital.put("Uzbekistan", "Tashkent");
		countryCapital.put("Vanuatu", "Port Vila");
		countryCapital.put("Vatican City", "Vatican City");
		countryCapital.put("Venezuela", "Caracas");
		countryCapital.put("Vietnam", "Hanoi");
		countryCapital.put("Wales", "Cardiff");
		countryCapital.put("Wallis and Futuna", "Mata-Utu");
		countryCapital.put("Western Sahara", "El Aaiún");
		countryCapital.put("Yemen", "Sanaá");
		countryCapital.put("Zambia", "Lusaka");
		countryCapital.put("Zimbabwe", "Harare");
		countCountries = countryCapital.size();
	}

	public static String getRandomCountry() {
		int index = (int) (Math.random() * countCountries);
		return (String) countryCapital.keySet().toArray()[index];
	}

	@Override
	public boolean isResultTaskValid(Task task) {
		try {
			CountryCapitalResult curResult = (CountryCapitalResult) task.getResult();
			CountryCapitalResult validResult = (CountryCapitalResult) execute(task);
			return curResult.getCapitalResultResult().equals(validResult.getCapitalResultResult());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}

}
