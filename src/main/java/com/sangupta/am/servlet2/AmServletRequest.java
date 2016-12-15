package com.sangupta.am.servlet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import com.sangupta.jerry.ds.SimpleMultiMap;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * Implementation of {@link ServletRequest} to use in unit testing.
 * 
 * @author sangupta
 *
 */
public class AmServletRequest implements ServletRequest {
	
	protected final Map<String, Object> attributes = new HashMap<>();
	
	protected final SimpleMultiMap<String, String> parameters = new SimpleMultiMap<>();
	
	protected final List<Locale> locales = new ArrayList<>();
	
	protected String characterEncoding;
	
	protected String protocol;
	
	protected String scheme;
	
	protected String serverName;
	
	protected int serverPort;
	
	protected String contentType;
	
	protected String remoteAddress;
	
	protected String remoteHost;
	
	protected int remotePort;

	protected String localAddress;
	
	protected String localName;
	
	protected int localPort;
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
	
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	
	// Overridden methods follow

	@Override
	public Object getAttribute(String name) {
		if(AssertUtils.isEmpty(name)) {
			return null;
		}
		
		return this.attributes.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@Override
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		this.characterEncoding = env;
	}

	@Override
	public int getContentLength() {
		return 0;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public String getParameter(String name) {
		List<String> values = this.parameters.getValues(name);
		if(AssertUtils.isEmpty(values)) {
			return null;
		}
		
		return values.get(0);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(this.parameters.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		List<String> values = this.parameters.getValues(name);
		if(AssertUtils.isEmpty(values)) {
			return null;
		}
		
		return values.toArray(StringUtils.EMPTY_STRING_LIST);
	}

	@Override
	public Map getParameterMap() {
		return null;
	}

	@Override
	public String getProtocol() {
		return this.protocol;
	}

	@Override
	public String getScheme() {
		return this.scheme;
	}

	@Override
	public String getServerName() {
		return this.serverName;
	}

	@Override
	public int getServerPort() {
		return this.serverPort;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return null;
	}

	@Override
	public String getRemoteAddr() {
		return this.remoteAddress;
	}

	@Override
	public String getRemoteHost() {
		return this.remoteHost;
	}

	@Override
	public void setAttribute(String name, Object o) {
		this.attributes.put(name, o);
	}

	@Override
	public void removeAttribute(String name) {
		this.attributes.remove(name);
	}

	@Override
	public Locale getLocale() {
		if(this.locales.isEmpty()) {
			return null;
		}
		
		return this.locales.get(0);
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return Collections.enumeration(this.locales);
	}

	@Override
	public boolean isSecure() {
		if(AssertUtils.isEmpty(this.scheme)) {
			return false;
		}
		
		return this.scheme.toLowerCase().trim().startsWith("https://");
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return null;
	}

	@Override
	public String getRealPath(String path) {
		return null;
	}

	@Override
	public int getRemotePort() {
		return this.remotePort;
	}

	@Override
	public String getLocalName() {
		return this.localName;
	}

	@Override
	public String getLocalAddr() {
		return this.localAddress;
	}

	@Override
	public int getLocalPort() {
		return this.localPort;
	}

}
