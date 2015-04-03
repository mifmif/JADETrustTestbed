/**
 * 
 */
package com.mifmif.gefmmat.core;

import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetInitiator;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * An abstract representation of agent in GeFMMAT Framework, this agent will use
 * it's trust knowledge about their partners while delegating tasks.
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
	private long responseTimeout = 5000;

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
	 * @param agentExperiences
	 *            the agentExperiences to set
	 */
	public void setAgentExperiences(List<AgentExperience> agentExperiences) {
		this.agentExperiences = agentExperiences;
	}

	/**
	 * Delegate a task to a trusted agent
	 * 
	 * @param task
	 */
	public void delegateTask(final Task task) {
		try {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);

			AID[] neighborsAgents = getNeighborsAgents();
			final int neighborsNumber = neighborsAgents.length;
			for (int i = 0; i < neighborsAgents.length; ++i) {
				cfp.addReceiver(neighborsAgents[i]);
			}

			cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
			cfp.setReplyByDate(new Date(System.currentTimeMillis() + getResponseTimeout()));
			cfp.setContentObject(task);

			addBehaviour(new ContractNetInitiator(this, cfp) {
				protected void handleRefuse(ACLMessage refuse) {
					System.out.println("Agent " + refuse.getSender().getName() + " refuse this task");
				}

				protected void handleAllResponses(Vector responses, Vector acceptances) {
					if (responses.size() < neighborsNumber) {
						System.out.println("Timeout expired : missing " + (neighborsNumber - responses.size()) + " responses");
					}
					// select the proposal of the best proposer
					ACLMessage bestProposal = getBestProposal(responses, acceptances);
					if (bestProposal != null) {
						Proposal proposal;
						try {
							proposal = (Proposal) bestProposal.getContentObject();
							ACLMessage accept = bestProposal.createReply();
							accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
							task.setProposal(proposal);
						} catch (UnreadableException e) {
							e.printStackTrace();
						}
					}
				}

				protected void handleInform(ACLMessage inform) {
					try {
						Result result = (Result) inform.getContentObject();
						task.setResult(result);
						AID trusteeAgentId = inform.getSender();
						AgentExperience experience = findExperienceWith(trusteeAgentId);
						experience.getTrustMetric().updateTrustKnowledge(experience.getTrustKnowledge());
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

	abstract protected AID[] getNeighborsAgents();

	abstract protected ACLMessage getBestProposal(Vector responses, Vector acceptances);

	// {int bestProposal = -1;
	// AID bestProposer = null;
	// ACLMessage accept = null;
	// Enumeration e = responses.elements();
	// while (e.hasMoreElements()) {
	// ACLMessage msg = (ACLMessage) e.nextElement();
	// if (msg.getPerformative() == ACLMessage.PROPOSE) {
	// ACLMessage reply = msg.createReply();
	// reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
	// acceptances.addElement(reply);
	// int proposal = Integer.parseInt(msg.getContent());
	// if (proposal > bestProposal) {
	// bestProposal = proposal;
	// bestProposer = msg.getSender();
	// accept = reply;
	// }
	// }
	// }}

	private AgentExperience findExperienceWith(AID aid) {
		for (AgentExperience experience : agentExperiences) {
			if (experience.getTrusteeAgent().getAID().equals(aid)) {
				return experience;
			}
		}
		return null;
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
}
