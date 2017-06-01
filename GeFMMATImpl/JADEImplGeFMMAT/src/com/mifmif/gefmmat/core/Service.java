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

import java.util.List;

import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;

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
	 * Main method that will be executed by the agent to process tasks related to this service.
	 */
	public Result execute(Task task) {
		Result result = null;
		try {
			prepareInputs(task);
		} catch (InvalidInputParameterException exception) {
			exception.printStackTrace();
			return null;
		}
		try {
			result = processTask(task);
		} catch (TaskProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Extract input parameter from the task object to use them later in {@link Service#processTask(Task)}
	 * 
	 * @param task
	 * @throws InvalidInputParameterException
	 */
	protected abstract void prepareInputs(Task task) throws InvalidInputParameterException;

	/**
	 * process that task passed as input parameter to the method and return a result object
	 * 
	 * @param task
	 * @return
	 * @throws TaskProcessingException
	 */
	protected abstract Result processTask(Task task) throws TaskProcessingException;

	public abstract boolean isResultTaskValid(Task task);

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
