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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sangupta.am.servlet.support.AmUrlEncoder;
import com.sangupta.jerry.constants.HttpHeaderName;
import com.sangupta.jerry.constants.HttpStatusCode;
import com.sangupta.jerry.ds.SimpleMultiMap;

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
	
	protected final SimpleMultiMap<String, String> headers = new SimpleMultiMap<>();
	
	protected int status;
	
	protected String statusMessage;
	
	protected AmUrlEncoder urlEncoder = new AmUrlEncoder();
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setUrlEncoder(AmUrlEncoder urlEncoder) {
		this.urlEncoder = urlEncoder;
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
		return this.urlEncoder.encodeURL(url);
	}

	@Override
	public String encodeRedirectURL(String url) {
		return this.urlEncoder.encodeRedirectURL(url);
	}

	@Override
	public String encodeUrl(String url) {
		return this.urlEncoder.encodeUrl(url);
	}

	@Override
	public String encodeRedirectUrl(String url) {
		return this.urlEncoder.encodeRedirectUrl(url);
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		this.status = sc;
		this.statusMessage = msg;
	}

	@Override
	public void sendError(int sc) throws IOException {
		this.setStatus(sc);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		this.status = HttpStatusCode.TEMPORARY_REDIRECT;
		this.headers.put(HttpHeaderName.LOCATION, location);
	}

	@Override
	public void setDateHeader(String name, long date) {
		this.headers.remove(name);
		this.addDateHeader(name, date);
	}

	@Override
	public void addDateHeader(String name, long date) {
		this.headers.put(name, new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(date)));
	}

	@Override
	public void setHeader(String name, String value) {
		this.headers.remove(name);
		this.headers.put(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		this.headers.remove(name);
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
