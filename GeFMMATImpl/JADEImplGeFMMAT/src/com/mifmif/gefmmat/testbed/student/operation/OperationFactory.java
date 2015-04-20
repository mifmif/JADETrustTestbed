package com.mifmif.gefmmat.testbed.student.operation;

import java.util.ArrayList;
import java.util.List;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;

/**
 * factory of operations used by students
 * 
 * @author y.mifrah
 *
 */
public class OperationFactory {
	private OperationFactory() {
	}

	public static Service getService(String serviceName) {
		Service service = null;

		switch (serviceName) {
		case "addition":
			service = new Addition();
			break;

		case "arithmeticOp":
			service = new ArithmeticOp();
			break;

		case "calculateEnergy":
			service = new CalculateEnergy();
			break;

		case "calculateSpeed":
			service = new CalculateSpeed();
			break;

		case "countryCapital":
			service = new CountryCapital();
			break;

		case "countryContinent":
			service = new CountryContinent();
			break;

		case "division":
			service = new Division();
			break;

		case "multiplication":
			service = new Multiplication();
			break;

		case "substraction":
			service = new Substraction();
			break;
		case "stringMatchPattern":
			service = new StringMatchPattern();
			break;

		default:
			System.out.println("undefined operation " + serviceName);
			break;
		}
		return service;
	}

	public static Service[] getAllServices() {
		List<Service> services = new ArrayList<Service>();
		services.add(getService("addition"));
		services.add(getService("arithmeticOp"));
		services.add(getService("calculateEnergy"));
		services.add(getService("calculateSpeed"));
		services.add(getService("countryCapital"));
		services.add(getService("countryContinent"));
		services.add(getService("division"));
		services.add(getService("multiplication"));
		services.add(getService("substraction"));
		services.add(getService("stringMatchPattern"));

		return services.toArray(new Service[0]);
	}
}
