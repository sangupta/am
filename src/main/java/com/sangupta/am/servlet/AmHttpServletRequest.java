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

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sangupta.jerry.exceptions.NotImplementedException;
import com.sangupta.jerry.util.AssertUtils;

/**
 * Implementation of {@link HttpServletRequest} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmHttpServletRequest extends AmServletRequest implements HttpServletRequest {
	
	protected final List<Cookie> cookies = new ArrayList<>();
	
	protected final Map<String, String> headers = new HashMap<>();
	
	protected String method = "GET";
	
	protected Principal principal;
	
	protected HttpSession session;
	
	protected String requestURI;
	
	protected StringBuffer requestURL;
	
	public static AmHttpServletRequest getDefault(String path) {
		AmHttpServletRequest request = new AmHttpServletRequest();
		
		request.protocol = "HTTP/1.1";
		request.scheme = "http";
		
		request.requestURI = "/context/";
		request.requestURL = new StringBuffer("http://localhost:80/context/");
		
		request.serverName = "localhost";
		request.serverPort = 80;
		
		request.characterEncoding = null;
		request.contentType = null;
		
		request.remoteAddress = null;
		request.remoteHost = null;
		request.remotePort = -1;
		
		request.localAddress = null;
		request.localPort = -1;
		
		return request;
	}
	
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	
	public void setRequestURL(StringBuffer requestURL) {
		this.requestURL = requestURL;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public void addCookie(Cookie cookie) {
		if(cookie == null) {
			return;
		}
		
		this.cookies.add(cookie);
	}
	
	public void addCookie(String name, String value) {
		this.cookies.add(new Cookie(name, value));
	}
	
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	
	// Overridden methods follow

	@Override
	public String getAuthType() {
		throw new NotImplementedException();
	}

	@Override
	public Cookie[] getCookies() {
		return this.cookies.toArray(new Cookie[] { });
	}

	@Override
	public long getDateHeader(String name) {
		return 0;
	}

	@Override
	public String getHeader(String name) {
		return this.headers.get(name);
	}

	@Override
	public Enumeration getHeaders(String name) {
		return null;
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return Collections.enumeration(this.headers.keySet());
	}

	@Override
	public int getIntHeader(String name) {
		String header = this.getHeader(name);
		if(AssertUtils.isEmpty(header)) {
			return 0;
		}
		
		return Integer.parseInt(header);
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getPathInfo() {
		throw new NotImplementedException();
	}

	@Override
	public String getPathTranslated() {
		throw new NotImplementedException();
	}

	@Override
	public String getContextPath() {
		throw new NotImplementedException();
	}

	@Override
	public String getQueryString() {
		throw new NotImplementedException();
	}

	@Override
	public String getRemoteUser() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isUserInRole(String role) {
		throw new NotImplementedException();
	}

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	@Override
	public String getRequestedSessionId() {
		throw new NotImplementedException();
	}

	@Override
	public String getRequestURI() {
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		return null;
	}

	@Override
	public String getServletPath() {
		return null;
	}

	@Override
	public HttpSession getSession(boolean create) {
		if(this.session != null) {
			return session;
		}
		
		if(create) {
			this.session = new AmHttpSession();
			return this.session;
		}
		
		return null;
	}

	@Override
	public HttpSession getSession() {
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

}
