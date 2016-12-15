package com.sangupta.am.servlet2;

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

import com.sangupta.jerry.util.AssertUtils;

/**
 * Implementation of {@link HttpServletRequest} for unit-testing.
 * 
 * @author sangupta
 *
 */
public class AmHttpServletRequest extends AmServletRequest implements HttpServletRequest {
	
	protected final List<Cookie> cookies = new ArrayList<>();
	
	protected final Map<String, String> headers = new HashMap<>();
	
	protected String method = "GET";
	
	protected Principal principal;
	
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
		return null;
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
		return null;
	}

	@Override
	public String getPathTranslated() {
		return null;
	}

	@Override
	public String getContextPath() {
		return null;
	}

	@Override
	public String getQueryString() {
		return null;
	}

	@Override
	public String getRemoteUser() {
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	@Override
	public String getRequestedSessionId() {
		return null;
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
