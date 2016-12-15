package com.sangupta.am.servlet2;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * Implementation of the {@link HttpSession} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
@SuppressWarnings("deprecation")
public class AmHttpSession implements HttpSession {
	
	private final Map<String, Object> attributes = new HashMap<>();
	
	private long created = System.currentTimeMillis();
	
	private String sessionID = UUID.randomUUID().toString();
	
	public void setCreated(long created) {
		this.created = created;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	// Overridden methods follow

	@Override
	public long getCreationTime() {
		return this.created;
	}

	@Override
	public String getId() {
		return this.sessionID;
	}

	@Override
	public long getLastAccessedTime() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		
	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}

	@Override
	public Object getValue(String name) {
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@Override
	public String[] getValueNames() {
		return null;
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.attributes.put(name, value);
	}

	@Override
	public void putValue(String name, Object value) {
		
	}

	@Override
	public void removeAttribute(String name) {
		this.removeAttribute(name);
	}

	@Override
	public void removeValue(String name) {
		
	}

	@Override
	public void invalidate() {
		
	}

	@Override
	public boolean isNew() {
		return false;
	}

}
