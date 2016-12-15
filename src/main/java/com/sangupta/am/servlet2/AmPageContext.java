package com.sangupta.am.servlet2;

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

@SuppressWarnings("deprecation")
public class AmPageContext extends PageContext {
	
	protected final Map<String, Object> pageAttributes = new HashMap<>();
	
	protected final Map<String, Object> requestAttributes = new HashMap<>();
	
	protected final Map<String, Object> sessionAttributes = new HashMap<>();
	
	protected final Map<String, Object> applicationAttributes = new HashMap<>();
	
	protected JspWriter jspWriter;
	
	protected ELContext elContext;
	
	protected ExpressionEvaluator expressionEvaluator;
	
	protected VariableResolver variableResolver;
	
	protected ServletRequest request;
	
	protected ServletResponse response;
	
	protected ServletConfig servletConfig;
	
	protected ServletContext servletContext;
	
	protected HttpSession session;
	
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
	
	// Overridden methods follow

	@Override
	public void initialize(Servlet servlet, ServletRequest request, ServletResponse response, String errorPageURL, boolean needsSession, int bufferSize, boolean autoFlush) throws IOException, IllegalStateException, IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HttpSession getSession() {
		return this.session;
	}

	@Override
	public Object getPage() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void include(String relativeUrlPath) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void include(String relativeUrlPath, boolean flush) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePageException(Exception e) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePageException(Throwable t) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	// Overridden methods from JspContext

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
