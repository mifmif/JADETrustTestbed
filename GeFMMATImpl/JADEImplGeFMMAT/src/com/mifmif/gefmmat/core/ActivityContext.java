/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.util.List;

/**
 * An ActivityContext hold in one or more services ,which define a specific
 * context that group those services.
 * 
 * @author y.mifrah
 *
 */
public class ActivityContext {
	private String id;
	private String name;
	private List<Feature> features;
	List<Service> services;

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
}
