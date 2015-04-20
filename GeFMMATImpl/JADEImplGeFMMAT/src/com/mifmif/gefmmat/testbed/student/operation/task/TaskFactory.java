package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Task;

public class TaskFactory {
	private TaskFactory() {
	}

	public static Task getTask(String taskName) {
		Task task = null;
		switch (taskName) {
		case "addition":
			task = AdditionTask.generate();
			break;

		case "arithmeticOp":
			task = ArithmeticOpTask.generate();
			break;

		case "calculateEnergy":
			task = EnergyTask.generate();
			break;

		case "calculateSpeed":
			task = SpeedTask.generate();
			break;

		case "countryCapital":
			task = CountryCapitalTask.generate();
			break;

		case "countryContinent":
			task = CountryContinentTask.generate();
			break;

		case "division":
			task = DivisionTask.generate();
			break;

		case "multiplication":
			task = MultiplicationTask.generate();
			break;

		case "substraction":
			task = SubstractionTask.generate();
			break;
		case "stringMatchPattern":
			task = MatchPatternTask.generate();
			break;

		default:
			System.out.println("undefined task " + taskName + " return generic task");
			task = new Task() {
			};
		}
		return task;
	}
}
