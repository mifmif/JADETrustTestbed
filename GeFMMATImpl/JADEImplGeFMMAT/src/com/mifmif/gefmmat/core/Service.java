/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.util.List;

/**
 * @author y.mifrah
 *
 */
public abstract class Service {
	private String id;
	private String name;
	private ActivityContext context;
	private List<Feature> features;

	/**
	 * Main method that will be executed by the agent to process tasks related
	 * to this service.
	 */
	public abstract Result execute(Task task);

	/**
	 * @return the context
	 */
	public ActivityContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(ActivityContext context) {
		this.context = context;
	}

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

}
