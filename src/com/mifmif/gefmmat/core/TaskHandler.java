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

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Class that will catch the call for proposals and handle communication with
 * the initiator agent.
 * 
 * @author y.mifrah
 *
 */
public abstract class TaskHandler extends OneShotBehaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5177380340115631579L;
	private Agent ownerAgent;
	Logger logger = (Logger) Logger.getLogger(getClass().getName());
	/**
	 * this value present whether the agent will decide to handle the cfp or not
	 * , if equal to 1 then it will always handle the cfp ,if O then it will
	 * never handle the cfp
	 */
	private double probabilityToHandleProposal = 0.85;

	/**
 * 
 */
	public TaskHandler(Agent ownerAgent) {
		this.setOwnerAgent(ownerAgent);
	}

	@Override
	public void action() {
		// System.out.println("call taskHandlerAction");
		MessageTemplate template = MessageTemplate
				.and(MessageTemplate
						.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
						MessageTemplate.MatchPerformative(ACLMessage.CFP));

		getOwnerAgent().addBehaviour(
				new ContractNetResponder(getOwnerAgent(), template) {
					Task task;

					protected ACLMessage handleCfp(ACLMessage cfp)
							throws NotUnderstoodException, RefuseException {
						// System.out.println("Agent " +
						// getOwnerAgent().getLocalName()
						// + ": CFP received from " +
						// cfp.getSender().getName());
						try {
							task = (Task) cfp.getContentObject();
							Proposal proposal = prepareProposal(task);
							boolean handleProposal = Math.random() < getProbabilityToHandleProposal();
							if (handleProposal && proposal != null) {
								// We provide a proposal
								// System.out.println("Agent " +
								// getOwnerAgent().getLocalName() +
								// ": Proposing " +
								// proposal);
								ACLMessage propose = cfp.createReply();
								propose.setPerformative(ACLMessage.PROPOSE);
								propose.setContentObject(proposal);
								return propose;
							} else {
								// We refuse to provide a proposal
								// System.out.println("Agent " +
								// getOwnerAgent().getLocalName() + ": Refuse");

								ACLMessage propose = cfp.createReply();
								propose.setPerformative(ACLMessage.REFUSE);
								return propose;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						throw new RefuseException("evaluation-failed");

					}

					protected ACLMessage prepareResultNotification(
							ACLMessage cfp, ACLMessage propose,
							ACLMessage accept) throws FailureException {
						// System.out.println("Agent " +
						// getOwnerAgent().getLocalName() +
						// ": Proposal accepted");

						Result result = performTask(task);
						if (result != null) {
							// System.out.println("Agent " +
							// getOwnerAgent().getLocalName() +
							// ": Action successfully performed");
							ACLMessage inform = accept.createReply();
							try {
								inform.setContentObject(result);
							} catch (IOException e) {
								e.printStackTrace();
								throw new FailureException("unexpected-error");
							}
							inform.setPerformative(ACLMessage.INFORM);
							return inform;
						} else {
							// System.out.println("Agent " +
							// getOwnerAgent().getLocalName() +
							// ": Action execution failed");
							throw new FailureException("unexpected-error");
						}
					}

					protected void handleRejectProposal(ACLMessage reject) {
						// System.out.println("Agent " +
						// getOwnerAgent().getLocalName() +
						// ": Proposal rejected");
					}
				});
		// block();
	}

	abstract public Proposal prepareProposal(Task task);

	abstract public Result performTask(Task task);

	public Agent getOwnerAgent() {
		return ownerAgent;
	}

	public void setOwnerAgent(Agent ownerAgent) {
		this.ownerAgent = ownerAgent;
	}

	public double getProbabilityToHandleProposal() {
		return probabilityToHandleProposal;
	}

	public void setProbabilityToHandleProposal(
			double probabilityToHandleProposal) {
		this.probabilityToHandleProposal = probabilityToHandleProposal;
	}
}
