/**
 * 
 */
package com.mifmif.gefmmat.core;

import java.io.Serializable;
import java.util.Map;

/**
 * @author y.mifrah
 *
 */
public class Result implements Serializable {
	private Map<String, String> infos;

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
