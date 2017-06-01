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

package com.mifmif.gefmmat.testbed.student;

import jade.core.AID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Feature;
import com.mifmif.gefmmat.core.Proposal;
import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.TaskHandler;
import com.mifmif.gefmmat.core.trust.TrustMetric;
import com.mifmif.gefmmat.core.util.Utils;
import com.mifmif.gefmmat.testbed.student.trustmodel.BRS;
import com.mifmif.gefmmat.testbed.student.trustmodel.ForgiveFactor;
import com.mifmif.gefmmat.testbed.student.trustmodel.Jonker;
import com.mifmif.gefmmat.testbed.student.trustmodel.NoModel;

/**
 * @author y.mifrah
 *
 */
public class Student extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long TASK_GENERATOR_PERIOD = (long) (10 * 2);// ms

	public Student() {
		StudentStatusRegister.addStudent(this);
		setAgentExperiences(new ArrayList<AgentExperience>());
		setFeatures(new ArrayList<Feature>());
		setServicesNameServices(new HashMap<String, Service>());
		setTaskHandler(new HonestTaskHandler(this));
		setTrustMetric(new TrustMetric(new NoModel()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mifmif.gefmmat.core.Agent#getNeighborsAgents()
	 */
	@Override
	protected AID[] getNeighborsAgents(String serviceName) {
		return Utils.findAgentByService(this, serviceName);
	}

	public AgentExperience prepareAgentExperience() {
		return null;
	}

	public static enum StudentType {
		HONEST, CAMOUFLAGE_DISHONEST, RANDOM_DISHONEST, CONSTANT_DISHONEST
	}

	/**
	 * the 2ed parameter that represent if the agent has a task handler Behavior
	 * 
	 * @return
	 */
	public boolean hasTaskHandlerBahaviour() {
		if (getArguments().length < 2)
			return false;
		boolean hasTaskHandler = (boolean) getArguments()[1];
		return hasTaskHandler;
	}

	/**
	 * the 3ed parameter that represent if the agent has a task generator Behavior
	 * 
	 * @return
	 */
	public boolean hasTaskGeneratorBahaviour() {
		if (getArguments().length < 3)
			return false;
		boolean hasTaskGenerator = (boolean) getArguments()[2];
		return hasTaskGenerator;
	}

	@Override
	protected void setup() {
		boolean hasTaskGeneratorBahaviour = hasTaskGeneratorBahaviour();
		boolean hasTaskHandlerBahaviour = hasTaskHandlerBahaviour();
		if (hasTaskHandlerBahaviour) {
			addBehaviour(getTaskHandler());
		}
		if (hasTaskGeneratorBahaviour) {
			System.out.println("add taskGeneratorBehaviour to " + getAID().getName());
			addBehaviour(new TaskGenerator(this, TASK_GENERATOR_PERIOD, 500/* TODO use argument to pass this value */));
			initializeTrustMetric();
		}
		super.setup();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				takeDown();
			}
		});
	}

	private void initializeTrustMetric() {
		if (getArguments().length < 4)
			return;
		TrustMetric metric = (TrustMetric) getArguments()[3];
		setTrustMetric(metric);
	}

	/**
	 * the build method that return a builderStudent instance to construct a class instance
	 * 
	 * @param studentType
	 * @return
	 */
	public static StudentBuilder builder(StudentType studentType) {
		return new StudentBuilder(studentType);
	}

	/**
	 * Implementation of the BuilderPattern
	 * 
	 * @author y.mifrah
	 *
	 */
	public static class StudentBuilder {
		private Student student;

		public StudentBuilder(StudentType studentType) {
			switch (studentType) {
			case CAMOUFLAGE_DISHONEST:
				student = new CamouflageDishonestStudent();
				break;

			case RANDOM_DISHONEST:
				student = new RandomDishonestStudent();
				break;

			case CONSTANT_DISHONEST:
				student = new ConstantDishonestStudent();
				break;

			case HONEST:
				student = new Student();
				break;

			default:
				student = new Student();
				break;

			}
		}

		public StudentBuilder addFeature(Feature feature) {
			student.getFeatures().add(feature);
			return this;
		}

		public StudentBuilder addService(String serviceName, Service service) {
			student.getServicesNameServices().put(serviceName, service);
			return this;
		}

		public Student build() {
			return student;
		}
	}

	public static class HonestTaskHandler extends TaskHandler {

		public HonestTaskHandler(Agent ownerAgent) {
			super(ownerAgent);
		}

		@Override
		public Proposal prepareProposal(Task task) {
			Proposal proposal = new Proposal();
			proposal.setInfos(new HashMap<String, String>());
			String serviceName = task.getServiceName();
			Service service = getOwnerAgent().getServicesNameServices().get(serviceName);
			if (service != null) {
				proposal.getInfos().put("price", "" + (int) (1 + Math.random() * 10));
			} else {
				proposal.getInfos().put("price", "-1");

			}
			return proposal;
		}

		/**
		 * a honest agent perform tasks with a probability of 95% of success
		 * 
		 * @see com.mifmif.gefmmat.core.TaskHandler#performTask(com.mifmif.gefmmat.core.Task)
		 */
		@Override
		public Result performTask(Task task) {
			boolean possibleError = Math.random() > 0.95;
			if (possibleError)
				return prepareUnfairResult(task);
			else
				return prepareFairResult(task);
		}

		protected Result prepareFairResult(Task task) {
			String serviceName = task.getServiceName();
			Service service = getOwnerAgent().getServicesNameServices().get(serviceName);
			Result result = service.execute(task);
			return result;
		}

		protected Result prepareUnfairResult(Task task) {
			Result result = prepareFairResult(task);
			if (result == null)
				return null;
			Map<String, String> resultOutput = result.getOutputs();
			Map<String, String> unfairResultOutput = new HashMap<String, String>();
			for (Entry<String, String> entry : resultOutput.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				String invalidValue = value + value;// we just
													// concatenate the
													// value with it
													// self , when the
													// requester agent
													// sty to check the
													// validity of this
													// value , he will
													// always find that
													// it's invalid
				try {
					invalidValue = "" + Double.parseDouble(value) * Double.parseDouble(value);
				} catch (Exception exception) {

				}

				unfairResultOutput.put(key, invalidValue);
			}
			result.setOutputs(unfairResultOutput);
			return result;

		}
	}

}
