package com.sangupta.am.servlet2.helper;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Value object that represents the result of invoking a {@link BodyTagSupport} based
 * JSP custom tag library, and thus aids in unit testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmBodyTagEvaluationResult {

	public int startTagResult = -1;
	
	public int afterBodyResult = -1;

	public int endTagResult = -1;

}
