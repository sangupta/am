package com.sangupta.am.servlet2;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class AmJspContext extends JspContext {
	
	protected final Map<String, Object> attributes = new HashMap<>();
	
	protected JspWriter jspWriter;
	
	public void setJspWriter(JspWriter jspWriter) {
		this.jspWriter = jspWriter;
	}
	
	// Overridden methods follow

	@Override
	public Object findAttribute(String arg0) {
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		return this.attributes.get(arg0);
	}

	@Override
	public Object getAttribute(String arg0, int arg1) {
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNamesInScope(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAttributesScope(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ELContext getELContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JspWriter getOut() {
		return this.jspWriter;
	}

	@Override
	public VariableResolver getVariableResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAttribute(String arg0) {
		this.attributes.remove(arg0);
	}

	@Override
	public void removeAttribute(String arg0, int arg1) {
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		this.attributes.put(arg0, arg1);
	}

	@Override
	public void setAttribute(String arg0, Object arg1, int arg2) {
		
	}

}
