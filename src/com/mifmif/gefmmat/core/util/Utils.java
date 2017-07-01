package com.mifmif.gefmmat.core.util;

import java.util.Map.Entry;

import com.mifmif.gefmmat.core.Agent;
import com.mifmif.gefmmat.core.Service;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Utils {

	public static AID getService(Agent searcherAgent, String service) {
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType(service);
		dfd.addServices(sd);
		try {
			DFAgentDescription[] result = DFService.search(searcherAgent, dfd);
			if (result.length > 0)
				return result[0].getName();
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		return null;
	}

	public static AID[] findAgentByService(Agent searcherAgent, String service) {
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType(service);
		dfd.addServices(sd);

		SearchConstraints ALL = new SearchConstraints();
		ALL.setMaxResults(new Long(-1));

		try {
			DFAgentDescription[] result = DFService.search(searcherAgent, dfd, ALL);
			AID[] agents = new AID[result.length];
			for (int i = 0; i < result.length; i++) {
				agents[i] = result[i].getName();
			}
			return agents;

		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		return null;
	}

	/**
	 * register the agent to the Directory Facilitator
	 * 
	 * @param agent
	 */
	public static void registerToDF(Agent agent) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(agent.getAID());
		for (Entry<String, Service> serviceEntry : agent.getServicesNameServices().entrySet()) {
			String serviceName = serviceEntry.getKey();
			Service service = serviceEntry.getValue();
			ServiceDescription sd = new ServiceDescription();
			sd.setType(serviceName);// Name of the service
			sd.setName(agent.getLocalName());// Name of the agent
			dfd.addServices(sd);// add the service to the list of provided
								// service by this agent

		}
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	/**
	 * remove the agent from the Directory Facilitator
	 * 
	 * @param agent
	 */
	public static void deregisterFromDF(Agent agent) {
		try {
			DFService.deregister(agent);
		} catch (Exception e) {
		}
	}

}
