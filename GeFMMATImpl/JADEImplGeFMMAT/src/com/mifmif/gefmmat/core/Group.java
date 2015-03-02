/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.util.List;

/**
 * @author y.mifrah
 *
 */
public class Group {
	private String id;
	private String name;
	private String description;
	private List<Agent> associatedAgents;
	private List<Agent> leaders;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the associatedAgents
	 */
	public List<Agent> getAssociatedAgents() {
		return associatedAgents;
	}

	/**
	 * @param associatedAgents
	 *            the associatedAgents to set
	 */
	public void setAssociatedAgents(List<Agent> associatedAgents) {
		this.associatedAgents = associatedAgents;
	}

	/**
	 * @return the leaders
	 */
	public List<Agent> getLeaders() {
		return leaders;
	}

	/**
	 * @param leaders
	 *            the leaders to set
	 */
	public void setLeaders(List<Agent> leaders) {
		this.leaders = leaders;
	}
}
