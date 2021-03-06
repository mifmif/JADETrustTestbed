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

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.core.trust.TrustMetric;
import com.mifmif.gefmmat.testbed.student.operation.OperationFactory;
import com.mifmif.gefmmat.testbed.student.trustmodel.BRS;
import com.mifmif.gefmmat.testbed.student.trustmodel.ForgiveFactor;
import com.mifmif.gefmmat.testbed.student.trustmodel.Jonker;
import com.mifmif.gefmmat.testbed.student.trustmodel.NoModel;
import com.mifmif.gefmmat.util.Measure;
import com.mifmif.gefmmat.util.SubjectiveLogicValue;

/**
 * @author y.mifrah
 *
 */
public class MainContainer {
	private static final double FIRGIVE_FACTOR_VAL = 0.6;
	private static ContainerController myContainer;
	private int honestStudentNumber = 5;
	private int camouflageStudentNumber = 5;
	private int randomStudentNumber = 5;
	private int constantDishonestStudentNumber = 5;
	private int whitewashingStudentNumber = 5;
	private int taskGeneratorStudentNumber = 1;
	private int numberOfTasks = 1;
	private TrustMetric[] metrics;
	private Service[] services;
	private boolean hasTaskHandlerBehaviour = false;
	private boolean hasTaskGeneratorBehaviour = true;
	private Object[] agentDelegatorArguments;
	private Object[] agentHandlerArguments;

	public MainContainer() {
		init();
	}

	private void init() {
		jade.core.Runtime myRuntime = jade.core.Runtime.instance();

		// prepare the settings for the platform that we're going to connect to
		Profile myProfile = new ProfileImpl();
		myProfile.setParameter(Profile.MAIN_HOST, "localhost");
		myProfile.setParameter(Profile.MAIN_PORT, "1099");

		myContainer = myRuntime.createMainContainer(myProfile);

		System.out.println("containers created");
		System.out.println("Launching the rma agent on the main container ...");
		AgentController rma;
		try {
			rma = myContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			rma.start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initOperations();
		initTrustMetrics();
		prepareAgentsArgument();
	}

	private void initOperations() {
		services = OperationFactory.getAllServices();
	}

	private void initTrustMetrics() {
		TrustMetric trustMetricBRS = new TrustMetric(new BRS());
		TrustMetric trustMetricJonker = new TrustMetric(new Jonker());
		TrustMetric trustMetricForgiveFactor = new TrustMetric(new ForgiveFactor(FIRGIVE_FACTOR_VAL));
		TrustMetric trustMetricNoModel = new TrustMetric(new NoModel());
		metrics = new TrustMetric[] { trustMetricBRS, trustMetricForgiveFactor, trustMetricJonker, trustMetricNoModel };
	}

	private void prepareAgentsArgument() {
		agentDelegatorArguments = new Object[] { null, isHasTaskHandlerBehaviour(), isHasTaskGeneratorBehaviour(), 500 };
		agentHandlerArguments = new Object[] { services, true };
	}

	public static void main(String[] args) throws StaleProxyException {
		MainContainer mainContainer = new MainContainer();
		mainContainer.startTestCase();
	}

	public void startTestCase() {
		try {
			for (int i = 0; i != getHonestStudentNumber(); ++i) {
				// AgentController student =
				// myContainer.createNewAgent("honestAgent" + i,
				// "jade.tools.testagent.TestAgent", new Object[0]);
				AgentController student;
				student = myContainer.createNewAgent("honestAgent_" + i, "com.mifmif.gefmmat.testbed.student.Student",
						agentHandlerArguments);

				student.start();
			}
			// Creating Camouflage students
			for (int i = 0; i != getCamouflageStudentNumber(); ++i) {
				AgentController student = myContainer.createNewAgent("camouflageAgent_" + i,
						"com.mifmif.gefmmat.testbed.student.CamouflageDishonestStudent", agentHandlerArguments);
				student.start();
			}
			// Creating Random students
			for (int i = 0; i != getRandomStudentNumber(); ++i) {
				AgentController student = myContainer.createNewAgent("RandomDishonestAgent_" + i,
						"com.mifmif.gefmmat.testbed.student.RandomDishonestStudent", agentHandlerArguments);
				student.start();
			}

			// Creating ConstantDishonest students
			for (int i = 0; i != getConstantDishonestStudentNumber(); ++i) {
				AgentController student = myContainer.createNewAgent("ConstantDishonestAgent_" + i,
						"com.mifmif.gefmmat.testbed.student.ConstantDishonestStudent", agentHandlerArguments);
				student.start();
			}

			// Creating WhitewashingDishonestStudent students
			for (int i = 0; i != getWhitewashingStudentNumber(); ++i) {
				AgentController student = myContainer.createNewAgent("WhitewashingDishonestStudent_" + i,
						"com.mifmif.gefmmat.testbed.student.WhitewashingDishonestStudent", agentHandlerArguments);
				student.start();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Creating taskGenerator students
			for (int i = 0; i != getTaskGeneratorStudentNumber(); ++i) {
				agentDelegatorArguments = new Object[] { null, isHasTaskHandlerBehaviour(),
						isHasTaskGeneratorBehaviour(), metrics[i], numberOfTasks };
				AgentController taskGeneratorStudent = myContainer.createNewAgent("taskGeneratorStudent_" + i + "_"
						+ metrics[i].getTrustMetricName(), "com.mifmif.gefmmat.testbed.student.Student",
						agentDelegatorArguments);
				taskGeneratorStudent.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(new ConsoleViewer()).start();
	}

	public static ContainerController getContainer() {
		return myContainer;
	}

	public static void setContainer(ContainerController myContainer) {
		MainContainer.myContainer = myContainer;
	}

	static class ConsoleViewer implements Runnable {

		@Override
		public void run() {
			Scanner in = new Scanner(System.in);
			List<Student> students = new ArrayList<Student>();
			System.out.println("Available students : ");
			int count = 0;
			for (Student student : StudentStatusRegister.getStudents()) {
				if (student.hasTaskGeneratorBahaviour()) {
					count++;
					System.out.println(count + " : " + student.getAID().getName());
					students.add(student);
				}
			}
			while (true) {
				System.out.println("select student index [1-" + count + "] : ");
				int index = 0;
				try {
					index = in.nextInt();
				} catch (Exception exception) {

				}

				if (index > count || index < 1) {
					System.out.println("invalid index ");
					continue;
				}
				Student student = students.get(index - 1);
				System.out.println("-View evaluations information about passed experiences");
				System.out.println("-View mean evaluations information ");
				System.out.println("select option [1-2] : ");
				int option = 0;
				try {
					option = in.nextInt();
				} catch (Exception exception) {

				}
				if (option > 2 || option < 1) {
					System.out.println("invalid option ");
					continue;
				}
				switch (option) {
				case 1:
					for (AgentExperience agentExperience : student.getAgentExperiences()) {
						System.out.println(agentExperience.toString());
					}

					break;
				case 2:

					Map<String, Entry<Integer, SubjectiveLogicValue>> meanEvaluations = prepareMeanEvaluations(student
							.getAgentExperiences());
					int[] stasicfactionAndDesatisfaction = countStasicfactionAndDesatisfaction(student
							.getAgentExperiences());
					System.out.println("trustor agent : " + student.getAID().getName() + " ["
							+ getTotalTasksNbr(student.getAgentExperiences()) + "] , MCC = "
							+ Measure.calculateMCC(student.getAgentExperiences()) + "     satisfaction : "
							+ stasicfactionAndDesatisfaction[0] + " desatisfaction : "
							+ stasicfactionAndDesatisfaction[1]);
					for (Entry<String, Entry<Integer, SubjectiveLogicValue>> categoEval : meanEvaluations.entrySet()) {
						String catego = categoEval.getKey();
						SubjectiveLogicValue eval = categoEval.getValue().getValue();
						int selectionTime = categoEval.getValue().getKey();
						System.out.printf("		" + getStringWithSpace(catego + "[" + selectionTime + "]")
								+ ":	%.3f    %.3f    %.3f    \n", eval.getBelief(), eval.getDisbelief(),
								eval.getUncertainty());
					}
					break;

				default:
					break;
				}

			}
		}

		private int getTotalTasksNbr(List<AgentExperience> exps) {
			int total = 0;
			for (AgentExperience ex : exps) {
				total += ex.getProcessedTasks().size();
			}
			return total;

		}

		public String getStringWithSpace(String str) {
			if (str.length() > 34)
				return str;
			char[] s = new char[35 - str.length()];
			Arrays.fill(s, ' ');
			return str + String.valueOf(s);
		}

		/**
		 * this method return a map that show for each category the mean
		 * evaluation of trustworthiness , and the number of time agens from
		 * this category was selected
		 * 
		 * @param agentExperiences
		 * @return
		 */
		/**
		 * @param agentExperiences
		 * @return
		 */
		private Map<String, Entry<Integer, SubjectiveLogicValue>> prepareMeanEvaluations(
				List<AgentExperience> agentExperiences) {
			Map<String, Entry<Integer, SubjectiveLogicValue>> meanEvaluationByCategory = new HashMap<String, Entry<Integer, SubjectiveLogicValue>>();
			Map<String, List<AgentExperience>> agentExperienceByCategory = new HashMap<String, List<AgentExperience>>();
			for (AgentExperience experience : agentExperiences) {
				String agentName = experience.getTrusteeAgent().getName();
				String agentCategory = agentName.split("_")[0];
				List<AgentExperience> experiencesOfCategory = agentExperienceByCategory.get(agentCategory);
				if (experiencesOfCategory == null) {
					experiencesOfCategory = new ArrayList<AgentExperience>();
					agentExperienceByCategory.put(agentCategory, experiencesOfCategory);
				}
				experiencesOfCategory.add(experience);
			}

			for (Entry<String, List<AgentExperience>> expByCatego : agentExperienceByCategory.entrySet()) {
				SubjectiveLogicValue meanEval = new SubjectiveLogicValue();
				String catego = expByCatego.getKey();
				int selectionTime = 0;
				int nbrExp = expByCatego.getValue().size();
				double b = 0;
				double d = 0;
				double u = 0;
				for (AgentExperience experience : expByCatego.getValue()) {
					SubjectiveLogicValue meanFeatureEvaluation = experience.getMeanFeatureEvaluation();
					b += meanFeatureEvaluation.getBelief();
					d += meanFeatureEvaluation.getDisbelief();
					u += meanFeatureEvaluation.getUncertainty();
					selectionTime += experience.getProcessedTasks().size();
				}
				b /= nbrExp;
				d /= nbrExp;
				u /= nbrExp;
				meanEval.setBelief(b);
				meanEval.setDisbelief(d);
				meanEval.setUncertainty(u);
				meanEvaluationByCategory.put(catego, new AbstractMap.SimpleEntry(selectionTime, meanEval));
			}
			return meanEvaluationByCategory;
		}
	}

	public static int[] countStasicfactionAndDesatisfaction(List<AgentExperience> agentExperiences) {
		int[] satDesat = new int[2];
		int satisfaction = 0;
		int desatisfaction = 0;
		for (AgentExperience experience : agentExperiences) {
			for (Task task : experience.getProcessedTasks()) {
				if (task.getResult().isValid()) {
					satisfaction++;
				} else {
					desatisfaction++;
				}
			}
		}
		satDesat[0] = satisfaction;
		satDesat[1] = desatisfaction;
		return satDesat;
	}

	public int getHonestStudentNumber() {
		return honestStudentNumber;
	}

	public void setHonestStudentNumber(int honestStudentNumber) {
		this.honestStudentNumber = honestStudentNumber;
	}

	public int getCamouflageStudentNumber() {
		return camouflageStudentNumber;
	}

	public void setCamouflageStudentNumber(int camouflageStudentNumber) {
		this.camouflageStudentNumber = camouflageStudentNumber;
	}

	public int getRandomStudentNumber() {
		return randomStudentNumber;
	}

	public void setRandomStudentNumber(int randomStudentNumber) {
		this.randomStudentNumber = randomStudentNumber;
	}

	public int getConstantDishonestStudentNumber() {
		return constantDishonestStudentNumber;
	}

	public void setConstantDishonestStudentNumber(int constantDishonestStudentNumber) {
		this.constantDishonestStudentNumber = constantDishonestStudentNumber;
	}

	public int getWhitewashingStudentNumber() {
		return whitewashingStudentNumber;
	}

	public void setWhitewashingStudentNumber(int whitewashingStudentNumber) {
		this.whitewashingStudentNumber = whitewashingStudentNumber;
	}

	public int getTaskGeneratorStudentNumber() {
		return taskGeneratorStudentNumber;
	}

	public void setTaskGeneratorStudentNumber(int taskGeneratorStudentNumber) {
		this.taskGeneratorStudentNumber = taskGeneratorStudentNumber;
	}

	public boolean isHasTaskHandlerBehaviour() {
		return hasTaskHandlerBehaviour;
	}

	public void setHasTaskHandlerBehaviour(boolean hasTaskHandlerBehaviour) {
		this.hasTaskHandlerBehaviour = hasTaskHandlerBehaviour;
	}

	public boolean isHasTaskGeneratorBehaviour() {
		return hasTaskGeneratorBehaviour;
	}

	public void setHasTaskGeneratorBehaviour(boolean hasTaskGeneratorBehaviour) {
		this.hasTaskGeneratorBehaviour = hasTaskGeneratorBehaviour;
	}

	public void setNumberOfTasks(int nbrTasks) {
		this.numberOfTasks = nbrTasks;

	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}
}
