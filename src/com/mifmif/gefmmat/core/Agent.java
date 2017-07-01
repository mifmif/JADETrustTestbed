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

package com.mifmif.gefmmat.core;

import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetInitiator;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.mifmif.gefmmat.core.trust.TrustMetric;
import com.mifmif.gefmmat.core.util.Utils;
import com.mifmif.gefmmat.testbed.student.ResultValidator;

/**
 * An abstract representation of agent in GeFMMAT Framework, this agent will use it's trust knowledge about their partners while delegating
 * tasks.
 * 
 * @author y.mifrah
 *
 */
public abstract class Agent extends jade.core.Agent {

	private static final long serialVersionUID = 1L;
	private List<Feature> features;
	List<Group> groups;
	private List<AgentExperience> agentExperiences;
	private Map<String, Service> servicesNameServices;
	private long responseTimeout = 100000;
	private TaskHandler taskHandler;// the implementation of this class define
									// how the agent deal with tasks . is it
									// honest of dishonest
	private TrustMetric trustMetric;

	/**
	 * @return the features
	 */
	public List<Feature> getFeatures() {
		return features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	/**
	 * @return the agentExperiences
	 */
	public List<AgentExperience> getAgentExperiences() {
		return agentExperiences;
	}

	/**
	 * @return the agentExperiences
	 */
	public AgentExperience getAgentExperience(AID agentId) {

		for (AgentExperience experience : agentExperiences) {
			if (experience.getTrusteeAgent().equals(agentId))
				return experience;
		}
		AgentExperience experience = new AgentExperience();
		experience.setTrusteeAgent(agentId);
		experience.setTrustMetric(getTrustMetric());
		agentExperiences.add(experience);
		return experience;
	}

	/**
	 * @param agentExperiences
	 *            the agentExperiences to set
	 */
	public void setAgentExperiences(List<AgentExperience> agentExperiences) {
		this.agentExperiences = agentExperiences;
	}

	/**
	 * Delegate a task to an agent
	 * 
	 * @param task
	 */
	public void delegateTask(final Task task) {
		try {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			String serviceName = task.getInputs().get("serviceName");
			AID[] neighborsAgents = getNeighborsAgents(serviceName);
			final int neighborsNumber = neighborsAgents.length;
			for (int i = 0; i < neighborsAgents.length; ++i) {
				if (neighborsAgents[i].equals(getAID())) {
					continue;
				}
				cfp.addReceiver(neighborsAgents[i]);
			}

			cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
			cfp.setReplyByDate(new Date(System.currentTimeMillis() + getResponseTimeout()));
			cfp.setContentObject(task);

			addBehaviour(new ContractNetInitiator(this, cfp) {

				protected void handleAllResponses(Vector responses, Vector acceptances) {
					if (responses.size() < neighborsNumber) {
						// System.out.println("Timeout expired : missing " + (neighborsNumber - responses.size()) + " responses");
						// TODO
					}
					Enumeration elmt = responses.elements();
					while (elmt.hasMoreElements()) {
						ACLMessage msg = (ACLMessage) elmt.nextElement();
						if (msg.getPerformative() == ACLMessage.PROPOSE) {
							ACLMessage reply = msg.createReply();
							reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
							acceptances.addElement(reply);
						}
					}
					// select the proposal of the best proposer
					ACLMessage bestProposal = getBestProposal(acceptances, task.getFeatures());
					if (bestProposal != null) {
						Proposal proposal;
						try {
							bestProposal.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
							proposal = (Proposal) bestProposal.getContentObject();
							task.setProposal(proposal);
						} catch (UnreadableException e) {
							e.printStackTrace();
						}
					}
				}

				protected void handleInform(ACLMessage inform) {
					try {
						// System.out.println("handle result from " + inform.getSender().getName());
						Result result = (Result) inform.getContentObject();
						task.setResult(result);
						AID trusteeAgentId = inform.getSender();
						AgentExperience experience = getAgentExperience(trusteeAgentId);
						// check that the result is valid or not
						ResultValidator.validateTask(task);
						experience.getTrustMetric().updateTrustKnowledge(experience.getTrustKnowledge(), task);
						// System.out.println(trusteeAgentId);
						// System.out.println(experience.getTrustKnowledge().toString());
					} catch (UnreadableException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract public AgentExperience prepareAgentExperience();

	abstract protected AID[] getNeighborsAgents(String serviceName);

	/**
	 * agent evaluate the trustworthiness of each of the proposers and choose the trustworthy one
	 * 
	 * @param acceptances
	 * @return
	 */
	protected ACLMessage getBestProposal(List<ACLMessage> acceptances, List<Feature> taskFeatures) {
		ACLMessage aclMessageOfBestProposal = null;
		AgentExperience agentExperienceOfBestProposal = null;
		for (ACLMessage message : acceptances) {
			try {
				AID sender = (AID) message.getAllReceiver().next();
				AgentExperience agentExperience = getAgentExperience(sender);
				if (agentExperienceOfBestProposal == null) {
					agentExperienceOfBestProposal = agentExperience;
					aclMessageOfBestProposal = message;
					continue;
				}

				boolean isBetterThanCurrentBestProposal = getTrustMetric().compareAgentExperience(agentExperience,
								agentExperienceOfBestProposal, taskFeatures) > 0;
				// Proposal proposal = (Proposal) message.getContentObject();
				// int price =
				// Integer.parseInt(proposal.getInfos().get("price"));
				if (isBetterThanCurrentBestProposal) {
					agentExperienceOfBestProposal = agentExperience;
					aclMessageOfBestProposal = message;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return aclMessageOfBestProposal;
	}

	/**
	 * @return the responseTimeout
	 */
	public long getResponseTimeout() {
		return responseTimeout;
	}

	/**
	 * @param responseTimeout
	 *            the responseTimeout to set
	 */
	public void setResponseTimeout(long responseTimeout) {
		this.responseTimeout = responseTimeout;
	}

	public Map<String, Service> getServicesNameServices() {
		return servicesNameServices;
	}

	public void setServicesNameServices(Map<String, Service> servicesNameServices) {
		this.servicesNameServices = servicesNameServices;
	}

	@Override
	protected void setup() {
		initServices();
		Utils.registerToDF(this);
		super.setup();
	}

	/**
	 * initialize services of the agent using arguments passed as params
	 */
	private void initServices() {
		Object[] arguments = getArguments();
		if (arguments.length != 0) {
			Service[] services = (Service[]) arguments[0];
			if (services != null)
				for (Service service : services) {
					servicesNameServices.put(service.getName(), service);
				}
		}
	}

	@Override
	protected void takeDown() {
		Utils.deregisterFromDF(this);
		super.takeDown();
	}

	public TaskHandler getTaskHandler() {
		return taskHandler;
	}

	public void setTaskHandler(TaskHandler taskHandler) {
		this.taskHandler = taskHandler;
	}

	public TrustMetric getTrustMetric() {
		return trustMetric;
	}

	public void setTrustMetric(TrustMetric trustMetric) {
		this.trustMetric = trustMetric;
	}
}
