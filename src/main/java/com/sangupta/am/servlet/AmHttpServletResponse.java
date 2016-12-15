package com.sangupta.am.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of the {@link HttpServletResponse} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmHttpServletResponse extends AmServletResponse implements HttpServletResponse {
	
	protected final List<Cookie> cookies = new ArrayList<>();
	
	protected final Map<String, String> headers = new HashMap<>();
	
	protected int status;
	
	protected String statusMessage;
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	// Overridden methods follow

	@Override
	public void addCookie(Cookie cookie) {
		this.cookies.add(cookie);
	}

	@Override
	public boolean containsHeader(String name) {
		return this.headers.containsKey(name);
	}

	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendError(int sc) throws IOException {
		this.setStatus(sc);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		this.headers.put(name, String.valueOf(value));
	}

	@Override
	public void addIntHeader(String name, int value) {
		this.headers.put(name, String.valueOf(value));
	}

	@Override
	public void setStatus(int sc) {
		this.status = sc;
	}

	@Override
	public void setStatus(int sc, String sm) {
		this.status = sc;
		this.statusMessage = sm;
	}

}
