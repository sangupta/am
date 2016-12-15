/**
 * am: Assert-Mocks for unit-testing Java servlet API code
 * Copyright (c) 2016, Sandeep Gupta
 * 
 * https://sangupta.com/projects/am
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sangupta.am.servlet;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

/**
 * Implementation of the {@link JspContext} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
@SuppressWarnings("deprecation")
public class AmJspContext extends JspContext {
	
	protected final Map<String, Object> pageAttributes = new HashMap<>();
	
	protected final Map<String, Object> requestAttributes = new HashMap<>();
	
	protected final Map<String, Object> sessionAttributes = new HashMap<>();
	
	protected final Map<String, Object> applicationAttributes = new HashMap<>();
	
	protected JspWriter jspWriter;
	
	protected ELContext elContext;
	
	protected ExpressionEvaluator expressionEvaluator;
	
	protected VariableResolver variableResolver;
	
	public void setJspWriter(JspWriter jspWriter) {
		this.jspWriter = jspWriter;
	}
	
	public void setElContext(ELContext elContext) {
		this.elContext = elContext;
	}
	
	public void setExpressionEvaluator(ExpressionEvaluator expressionEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
	}
	
	public void setVariableResolver(VariableResolver variableResolver) {
		this.variableResolver = variableResolver;
	}
	
	// Overridden methods follow

	@Override
	public Object findAttribute(String arg0) {
		if(this.pageAttributes.containsKey(arg0)) {
			return this.pageAttributes.get(arg0);
		}

		if(this.requestAttributes.containsKey(arg0)) {
			return this.requestAttributes.get(arg0);
		}

		if(this.sessionAttributes.containsKey(arg0)) {
			return this.sessionAttributes.get(arg0);
		}

		if(this.applicationAttributes.containsKey(arg0)) {
			return this.applicationAttributes.get(arg0);
		}

		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		return this.pageAttributes.get(arg0);
	}

	@Override
	public Object getAttribute(String arg0, int arg1) {
		switch(arg1) {
			case PageContext.PAGE_SCOPE:
				return this.pageAttributes.get(arg0);
				
			case PageContext.REQUEST_SCOPE:
				return this.requestAttributes.get(arg0);
				
			case PageContext.SESSION_SCOPE:
				return this.sessionAttributes.get(arg0);
				
			case PageContext.APPLICATION_SCOPE:
				return this.applicationAttributes.get(arg0);
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	public Enumeration<String> getAttributeNamesInScope(int arg0) {
		switch(arg0) {
			case PageContext.PAGE_SCOPE:
				return Collections.enumeration(this.pageAttributes.keySet());
				
			case PageContext.REQUEST_SCOPE:
				return Collections.enumeration(this.requestAttributes.keySet());
				
			case PageContext.SESSION_SCOPE:
				return Collections.enumeration(this.sessionAttributes.keySet());
				
			case PageContext.APPLICATION_SCOPE:
				return Collections.enumeration(this.applicationAttributes.keySet());
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	public int getAttributesScope(String arg0) {
		if(this.pageAttributes.containsKey(arg0)) {
			return PageContext.PAGE_SCOPE;
		}
		
		if(this.requestAttributes.containsKey(arg0)) {
			return PageContext.REQUEST_SCOPE;
		}

		if(this.sessionAttributes.containsKey(arg0)) {
			return PageContext.SESSION_SCOPE;
		}

		if(this.applicationAttributes.containsKey(arg0)) {
			return PageContext.APPLICATION_SCOPE;
		}
		
		return 0;
	}

	@Override
	public ELContext getELContext() {
		return this.elContext;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {
		return this.expressionEvaluator;
	}

	@Override
	public JspWriter getOut() {
		return this.jspWriter;
	}

	@Override
	public VariableResolver getVariableResolver() {
		return this.variableResolver;
	}

	@Override
	public void removeAttribute(String arg0) {
		this.pageAttributes.remove(arg0);
	}

	@Override
	public void removeAttribute(String arg0, int arg1) {
		switch(arg1) {
			case PageContext.PAGE_SCOPE:
				this.pageAttributes.remove(arg0);
				return;
				
			case PageContext.REQUEST_SCOPE:
				this.requestAttributes.remove(arg0);
				return;
				
			case PageContext.SESSION_SCOPE:
				this.sessionAttributes.remove(arg0);
				return;
				
			case PageContext.APPLICATION_SCOPE:
				this.applicationAttributes.remove(arg0);
				return;
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		this.pageAttributes.put(arg0, arg1);
	}

	@Override
	public void setAttribute(String arg0, Object arg1, int arg2) {
		switch(arg2) {
			case PageContext.PAGE_SCOPE:
				this.pageAttributes.put(arg0, arg1);
				return;
				
			case PageContext.REQUEST_SCOPE:
				this.requestAttributes.put(arg0, arg1);
				return;
				
			case PageContext.SESSION_SCOPE:
				this.sessionAttributes.put(arg0, arg1);
				return;
				
			case PageContext.APPLICATION_SCOPE:
				this.applicationAttributes.put(arg0, arg1);
				return;
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

}
