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

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import com.sangupta.am.servlet.support.MockExceptionHandler;
import com.sangupta.am.servlet.support.MockForwardOrIncludeHandler;

/**
 * Implementation of the {@link PageContext} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
@SuppressWarnings("deprecation")
public class MockPageContext extends PageContext {
	
	protected final Map<String, Object> pageAttributes = new HashMap<>();
	
	protected final Map<String, Object> applicationAttributes = new HashMap<>();
	
	protected JspWriter jspWriter;
	
	protected ELContext elContext;
	
	protected ExpressionEvaluator expressionEvaluator;
	
	protected VariableResolver variableResolver;
	
	protected ServletRequest request = new MockServletRequest();
	
	protected ServletResponse response = new MockServletResponse();
	
	protected ServletConfig servletConfig;
	
	protected ServletContext servletContext;
	
	protected HttpSession session = new MockHttpSession();
	
	protected MockForwardOrIncludeHandler forwardOrIncludeHandler;
	
	protected MockExceptionHandler exceptionHandler;
	
	protected Object page;
	
	protected Exception exception;
	
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
	
	public void setRequest(ServletRequest request) {
		this.request = request;
	}
	
	public void setResponse(ServletResponse response) {
		this.response = response;
	}
	
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setForwardOrIncludeHandler(MockForwardOrIncludeHandler forwardOrIncludeHandler) {
		this.forwardOrIncludeHandler = forwardOrIncludeHandler;
	}
	
	public void setExceptionHandler(MockExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	public void setPage(Object page) {
		this.page = page;
	}
	
	// Overridden methods follow

	@Override
	public void initialize(Servlet servlet, ServletRequest request, ServletResponse response, String errorPageURL, boolean needsSession, int bufferSize, boolean autoFlush) {
		this.servletConfig = servlet.getServletConfig();
		this.servletContext = servlet.getServletConfig().getServletContext();
		this.request = request;
		this.response = response;
	}

	@Override
	public void release() {
		this.pageAttributes.clear();
		this.elContext = null;
		this.exceptionHandler = null;
		this.applicationAttributes.clear();
		this.expressionEvaluator = null;
		this.variableResolver = null;
		this.forwardOrIncludeHandler = null;
		this.jspWriter = null;
		this.request = null;
		this.response = null;
		this.servletConfig = null;
		this.servletContext = null;
		this.session = null;
	}

	@Override
	public HttpSession getSession() {
		return this.session;
	}

	@Override
	public Object getPage() {
		return this.page;
	}

	@Override
	public ServletRequest getRequest() {
		return this.request;
	}

	@Override
	public ServletResponse getResponse() {
		return this.response;
	}

	@Override
	public Exception getException() {
		return this.exception;
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}

	@Override
	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override
	public void forward(String relativeUrlPath) throws ServletException, IOException {
		this.forwardOrIncludeHandler.handleForward(relativeUrlPath);
	}

	@Override
	public void include(String relativeUrlPath) throws ServletException, IOException {
		this.forwardOrIncludeHandler.handleInclude(relativeUrlPath, true);
	}

	@Override
	public void include(String relativeUrlPath, boolean flush) throws ServletException, IOException {
		this.forwardOrIncludeHandler.handleInclude(relativeUrlPath, flush);
	}

	@Override
	public void handlePageException(Exception e) throws ServletException, IOException {
		this.exceptionHandler.handleException(e);
	}

	@Override
	public void handlePageException(Throwable t) throws ServletException, IOException {
		this.exceptionHandler.handleException(t);
	}
	
	// Overridden methods from JspContext

	@Override
	public Object findAttribute(String key) {
		if(this.pageAttributes.containsKey(key)) {
			return this.pageAttributes.get(key);
		}

		Object attribute = this.getRequest().getAttribute(key);
		if(attribute != null) {
			return attribute;
		}

		attribute = this.getSession().getAttribute(key);
		if(attribute != null) {
			return attribute;
		}

		if(this.applicationAttributes.containsKey(key)) {
			return this.applicationAttributes.get(key);
		}

		return null;
	}

	@Override
	public Object getAttribute(String key) {
		return this.pageAttributes.get(key);
	}

	@Override
	public Object getAttribute(String key, int scope) {
		switch(scope) {
			case PageContext.PAGE_SCOPE:
				return this.pageAttributes.get(key);
				
			case PageContext.REQUEST_SCOPE:
				return this.getRequest().getAttribute(key);
				
			case PageContext.SESSION_SCOPE:
				return this.getSession().getAttribute(key);
				
			case PageContext.APPLICATION_SCOPE:
				return this.applicationAttributes.get(key);
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttributeNamesInScope(int scope) {
		switch(scope) {
			case PageContext.PAGE_SCOPE:
				return Collections.enumeration(this.pageAttributes.keySet());
				
			case PageContext.REQUEST_SCOPE:
				return this.getRequest().getAttributeNames();
				
			case PageContext.SESSION_SCOPE:
				return this.getSession().getAttributeNames();
				
			case PageContext.APPLICATION_SCOPE:
				return Collections.enumeration(this.applicationAttributes.keySet());
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	public int getAttributesScope(String key) {
		if(this.pageAttributes.containsKey(key)) {
			return PageContext.PAGE_SCOPE;
		}
		
		Object attribute = this.getRequest().getAttribute(key);
		if(attribute != null) {
			return PageContext.REQUEST_SCOPE;
		}

		attribute = this.getSession().getAttribute(key);
		if(attribute != null) {
			return PageContext.SESSION_SCOPE;
		}

		if(this.applicationAttributes.containsKey(key)) {
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
	public void removeAttribute(String key) {
		this.pageAttributes.remove(key);
	}

	@Override
	public void removeAttribute(String key, int scope) {
		switch(scope) {
			case PageContext.PAGE_SCOPE:
				this.pageAttributes.remove(key);
				return;
				
			case PageContext.REQUEST_SCOPE:
				this.getRequest().removeAttribute(key);
				return;
				
			case PageContext.SESSION_SCOPE:
				this.getSession().removeAttribute(key);
				return;
				
			case PageContext.APPLICATION_SCOPE:
				this.applicationAttributes.remove(key);
				return;
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

	@Override
	public void setAttribute(String key, Object value) {
		this.pageAttributes.put(key, value);
	}

	@Override
	public void setAttribute(String key, Object value, int scope) {
		switch(scope) {
			case PageContext.PAGE_SCOPE:
				this.pageAttributes.put(key, value);
				return;
				
			case PageContext.REQUEST_SCOPE:
				this.getRequest().setAttribute(key, value);
				return;
				
			case PageContext.SESSION_SCOPE:
				this.getSession().setAttribute(key, value);
				return;
				
			case PageContext.APPLICATION_SCOPE:
				this.applicationAttributes.put(key, value);
				return;
				
			default:
				throw new IllegalArgumentException("invalid scope");
		}
	}

}
