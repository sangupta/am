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
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.sangupta.jerry.util.StringUtils;

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
	
	public final Map<String, Object> attributes = new HashMap<>();
	
	public long created = System.currentTimeMillis();
	
	public long lastAccessed = this.created;
	
	public String sessionID = UUID.randomUUID().toString();
	
	public int maxInterval;
	
	public ServletContext servletContext;
	
	public HttpSessionContext httpSessionContext;
	
	public boolean sessionValid = true;
	
	public void setCreated(long created) {
		this.created = created;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public void setHttpSessionContext(HttpSessionContext httpSessionContext) {
		this.httpSessionContext = httpSessionContext;
	}
	
	public boolean isSessionValid() {
		return sessionValid;
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
		return this.lastAccessed;
	}

	@Override
	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		this.maxInterval = interval;
	}

	@Override
	public int getMaxInactiveInterval() {
		return this.maxInterval;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return this.httpSessionContext;
	}

	@Override
	public Object getAttribute(String name) {
		this.lastAccessed = System.currentTimeMillis();
		
		return this.attributes.get(name);
	}

	@Override
	public Object getValue(String name) {
		return this.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@Override
	public String[] getValueNames() {
		return this.attributes.keySet().toArray(StringUtils.EMPTY_STRING_LIST);
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.lastAccessed = System.currentTimeMillis();
		this.attributes.put(name, value);
	}

	@Override
	public void putValue(String name, Object value) {
		this.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		this.lastAccessed = System.currentTimeMillis();
		this.removeAttribute(name);
	}

	@Override
	public void removeValue(String name) {
		this.removeAttribute(name);
	}

	@Override
	public void invalidate() {
		this.sessionValid = false;
	}

	@Override
	public boolean isNew() {
		return this.created == this.lastAccessed;
	}

}
