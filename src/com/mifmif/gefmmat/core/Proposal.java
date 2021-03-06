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
import java.util.Map;

/**
 * This class contain proposal information of a specific task
 * 
 * @author y.mifrah
 *
 */
public class Proposal implements Serializable {
	private Map<String, String> infos;

	public Proposal() {
	}

	/**
	 * @return the infos
	 */
	public Map<String, String> getInfos() {
		return infos;
	}

	/**
	 * @param infos
	 *            the infos to set
	 */
	public void setInfos(Map<String, String> infos) {
		this.infos = infos;
	}
}
