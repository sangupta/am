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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import com.sangupta.jerry.ds.SimpleMultiMap;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * Implementation of the {@link ServletRequest} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class MockServletRequest implements ServletRequest {
	
    /**
     * Attributes associated with this request
     */
	public final Map<String, Object> attributes = new HashMap<>();
	
	/**
	 * Parameters associated with this request
	 */
	public final SimpleMultiMap<String, String> parameters = new SimpleMultiMap<>();
	
	/**
	 * {@link Locale}s associated with this request
	 */
	public final List<Locale> locales = new ArrayList<>();
	
	public String characterEncoding;
	
	public String protocol;
	
	public String scheme;
	
	public String serverName;
	
	public int serverPort;
	
	public String contentType;
	
	public String remoteAddress;
	
	public String remoteHost;
	
	public int remotePort;

	public String localAddress;
	
	public String localName;
	
	public int localPort;
	
	public String realPath;
	
	public int contentLength;
	
	public ServletInputStream inputStream;
	
	private boolean inputStreamObtained = false;
	
	public static MockServletRequest getDefault() {
		MockServletRequest request = new MockServletRequest();
		
		request.protocol = "HTTP/1.1";
		request.scheme = "http";
		
		request.serverName = "localhost";
		request.serverPort = 8080;
		
		request.characterEncoding = null;
		request.contentType = null;
		
		request.remoteAddress = null;
		request.remoteHost = null;
		request.remotePort = -1;
		
		request.localAddress = null;
		request.localPort = -1;
		
		return request;
	}
	
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
	
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
	public void setInputStream(ServletInputStream inputStream) {
		this.inputStream = inputStream;
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
		return this.contentLength;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		this.inputStreamObtained = true;
		return this.inputStream;
	}

	@Override
	public String getParameter(String name) {
		return this.parameters.getOne(name);
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
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<>();
		if(this.parameters.isEmpty()) {
			return map;
		}
		
		Set<String> keys = this.parameters.keySet();
		for(String key : keys) {
			map.put(key, this.parameters.getValues(key).toArray(StringUtils.EMPTY_STRING_LIST));
		}
		
		return map;
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
		if(this.inputStreamObtained) {
			throw new IllegalStateException();
		}
		
		if(this.inputStream == null) {
			throw new NullPointerException("Inputstream is null");
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));
		return reader;
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
		return new MockRequestDispatcher(path);
	}

	@Override
	public String getRealPath(String path) {
		return this.realPath;
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
