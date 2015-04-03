/**
 * 
 */
package com.mifmif.gefmmat.testbed.student;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Service;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.exception.TaskProcessingException;
import com.mifmif.gefmmat.testbed.student.operation.Addition;

/**
 * @author y.mifrah
 *
 */
public class ConstantDishonestStudent extends Student {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5245628080711850342L;

	static Service getConstantDishonestService(final Service service) {
		Service constantDishonestService = null;
		switch (service.getName()) {
		case "Addition":
			constantDishonestService = new Addition() {

				@Override
				protected void prepareInputs(Task task) throws InvalidInputParameterException {
					super.prepareInputs(task);
				}

				@Override
				protected Result processTask(Task task) throws TaskProcessingException {
					Result result = super.processTask(task);
					Map<String, String> resultOutput = result.getOutputs();
					Map<String, String> unfairResultOutput = new HashMap<String, String>();
					for (Entry<String, String> entry : resultOutput.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();
						String invalidValue = value + value;// we just
															// concatenate the
															// value with it
															// self , when the
															// requester agent
															// sty to check the
															// validity of this
															// value , he will
															// always find that
															// it's invalid
						unfairResultOutput.put(key, invalidValue);
					}
					result.setOutputs(unfairResultOutput);
					return result;
				}

			};

			break;
		// TODO add other services
		default:
			break;
		}

		return constantDishonestService;
	}
}
