/**
 * 
 */
package com.mifmif.gefmmat.core;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;
import jade.util.Logger;

import java.io.IOException;

/**
 * Class that will catch the call for proposals and handle communication with
 * the initiator agent.
 * 
 * @author y.mifrah
 *
 */
public abstract class TaskHandler extends CyclicBehaviour {
	private Agent ownerAgent;
	Logger logger = (Logger) Logger.getGlobal();

	/**
 * 
 */
	public TaskHandler(Agent ownerAgent) {
		this.ownerAgent = ownerAgent;
	}

	@Override
	public void action() {

		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP));

		ownerAgent.addBehaviour(new ContractNetResponder(ownerAgent, template) {
			Task task;

			protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
				System.out.println("Agent " + ownerAgent.getLocalName() + ": CFP received from " + cfp.getSender().getName());
				try {
					task = (Task) cfp.getContentObject();
					Proposal proposal = prepareProposal(task);

					if (proposal != null) {
						// We provide a proposal
						System.out.println("Agent " + ownerAgent.getLocalName() + ": Proposing " + proposal);
						ACLMessage propose = cfp.createReply();
						propose.setPerformative(ACLMessage.PROPOSE);
						propose.setContentObject(proposal);
						return propose;
					} else {
						// We refuse to provide a proposal
						System.out.println("Agent " + ownerAgent.getLocalName() + ": Refuse");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				throw new RefuseException("evaluation-failed");

			}

			protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
				System.out.println("Agent " + ownerAgent.getLocalName() + ": Proposal accepted");

				Result result = performTask(task);
				if (result != null) {
					System.out.println("Agent " + ownerAgent.getLocalName() + ": Action successfully performed");
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
					System.out.println("Agent " + ownerAgent.getLocalName() + ": Action execution failed");
					throw new FailureException("unexpected-error");
				}
			}

			protected void handleRejectProposal(ACLMessage reject) {
				System.out.println("Agent " + ownerAgent.getLocalName() + ": Proposal rejected");
			}
		});
	}

	abstract public Proposal prepareProposal(Task task);

	abstract public Result performTask(Task task);
}
