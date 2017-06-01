package com.mifmif.gefmmat.testbed.student;

import jade.core.behaviours.TickerBehaviour;

import java.util.logging.Logger;

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.operation.task.TaskFactory;

public class TaskGenerator extends TickerBehaviour {
	private Agent ownerAgent;
	Logger logger = (Logger) Logger.getGlobal();
	private int tasksNumber = 10;
	private int counter = 1;

	public void setTasksNumber(int tasksNumber) {
		this.tasksNumber = tasksNumber;
	}

	public TaskGenerator(Agent ownerAgent, long period_ms, int tasksNumber) {
		super(ownerAgent, period_ms);
		this.ownerAgent = ownerAgent;
		this.tasksNumber = tasksNumber;
	}

	private Task generateTask() {
		Task task = null;
		String[] tasksName = { "addition", "arithmeticOp", "calculateEnergy", "calculateSpeed", "countryCapital", "countryContinent",
				"division",
				"multiplication", "substraction", "stringMatchPattern" };
		int randomIndex = (int) (Math.random() * tasksName.length);
		task = TaskFactory.getTask(tasksName[randomIndex]);
		return task;
	}

	@Override
	protected void onTick() {
		Task task = generateTask();
		ownerAgent.delegateTask(task);
		if (tasksNumber != -1) {// if there is a limit for tasks to be generated
			if (counter == tasksNumber) {
				stop();
			}
			counter++;
		}

	}
}
