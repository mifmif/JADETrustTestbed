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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class would represent requirements needed to be done, it would also include proposition information and result after processing this
 * task by the delegated agent.
 * 
 * @author y.mifrah
 *
 */
abstract public class Task implements Serializable {
	private List<Feature> features = new ArrayList<Feature>();
	private static final long serialVersionUID = 20141220L;
	private Map<String, String> inputs = new HashMap<String, String>();// a task
																		// will
																		// have
																		// specific
																		// requirements

	public Task() {
	}

	private Proposal proposal;// this is the proposal of the choosed agent to
								// process this task , at the end the initiator
								// agent will compare this proposal with the
								// final result to evaluate the agent
								// trustworthiness
	private Result result;

	/**
	 * @return the proposal
	 */
	public Proposal getProposal() {
		return proposal;
	}

	/**
	 * @param proposal
	 *            the proposal to set
	 */
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	protected Map<String, String> getInputs() {
		return inputs;
	}

	protected void setInputs(Map<String, String> inputs) {
		this.inputs = inputs;
	}

	public String getProposerId() {
		return getInputs().get("taskProposerId");
	}

	public String getServiceName() {
		return getInputs().get("serviceName");
	}

	public String setServiceName(String serviceName) {
		return getInputs().put("serviceName", serviceName);
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	protected void addFeature(Feature feature) {
		features.add(feature);
	}
}
