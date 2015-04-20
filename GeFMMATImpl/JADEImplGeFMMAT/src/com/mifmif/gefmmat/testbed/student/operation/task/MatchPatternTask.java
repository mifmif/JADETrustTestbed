package com.mifmif.gefmmat.testbed.student.operation.task;

import com.mifmif.gefmmat.core.Result;
import com.mifmif.gefmmat.core.Task;
import com.mifmif.gefmmat.testbed.student.exception.InvalidInputParameterException;
import com.mifmif.gefmmat.testbed.student.operation.task.feature.InfoFeature;

public class MatchPatternTask extends Task {
	public MatchPatternTask() {
		addFeature(InfoFeature.getInstance());

		setServiceName("stringMatchPattern");
	}

	public String getExpression() throws InvalidInputParameterException {
		String expression = null;
		expression = getInputs().get("expression");
		if (expression == null) {
			throw new InvalidInputParameterException();
		}
		return expression;
	}

	public String getPattern() throws InvalidInputParameterException {
		String pattern = null;
		pattern = getInputs().get("pattern");
		if (pattern == null) {
			throw new InvalidInputParameterException();
		}
		return pattern;
	}

	public void setExpression(String expression) {
		getInputs().put("expression", "" + expression);
	}

	public void setPattern(String pattern) {
		getInputs().put("pattern", "" + pattern);
	}

	public static MatchPatternTask generate() {
		MatchPatternTask task = new MatchPatternTask();
		String expression = Math.random() > 0.5 ? "123" : "abc";
		task.setPattern("[0-9]+");
		task.setExpression(expression);
		return task;
	}

	public static class MatchPatternResult extends Result {
		public void setMatchResult(boolean isMatch) {
			getOutputs().put("isMatch", "" + isMatch);
		}

		public String getMatchResult() {
			return getOutputs().get("isMatch");
		}

	}
}
