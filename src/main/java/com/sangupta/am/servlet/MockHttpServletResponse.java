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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sangupta.am.servlet.support.MockUrlEncoder;
import com.sangupta.jerry.constants.HttpHeaderName;
import com.sangupta.jerry.constants.HttpStatusCode;
import com.sangupta.jerry.ds.SimpleMultiMap;

/**
 * Implementation of the {@link HttpServletResponse} for unit-testing that keeps
 * all parameters within memory and provides useful accessor methods to modify
 * the values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class MockHttpServletResponse extends MockServletResponse implements HttpServletResponse {

    /**
     * {@link Cookie}s associated with this response
     */
    public final List<Cookie> cookies = new ArrayList<>();

    /**
     * As per RFC 7230 header names are case-insensitive. Exercise caution when you
     * add and read headers directly.
     */
    public final SimpleMultiMap<String, String> headers = new SimpleMultiMap<>();

    public int status;

    public String statusMessage;

    public MockUrlEncoder urlEncoder = new MockUrlEncoder();

    public int getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setUrlEncoder(MockUrlEncoder urlEncoder) {
        this.urlEncoder = urlEncoder;
    }

    public int getNumCookies() {
        return this.cookies.size();
    }

    public Cookie getCookie(String name) {
        for (Cookie cookie : this.cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

    public boolean hasCookie(String name) {
        Cookie cookie = this.getCookie(name);
        if (cookie == null) {
            return false;
        }

        return true;
    }

    public String getHeader(String name) {
        return this.headers.getOne(name.toLowerCase());
    }

    public List<String> getHeaderValues(String name) {
        List<String> result = this.headers.getValues(name.toLowerCase());
        if(result != null) {
            return result;
        }
        
        return new ArrayList<>();
    }

    public int getIntHeader(String name) {
        String value = this.getHeader(name);
        return Integer.parseInt(value);
    }

    /**
     * Get a header as a date value returning <code>long</code>. If multiple values
     * are present for the header, the first one is picked.
     * 
     * @param name the name of the header
     * 
     * @return the <code>long</code> value for the header if present, else
     *         <code>-1</code> if absent.
     */
    public long getDateHeader(String name) {
        String value = this.getHeader(name);
        try {
            return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(value).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Enumeration<String> getHeaders(String name) {
        name = name.toLowerCase();
        if (!this.headers.containsKey(name)) {
            return null;
        }

        return Collections.enumeration(this.headers.getValues(name));
    }

    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.headers.keySet());
    }

    public Set<String> getHeaderNamesAsSet() {
        return this.headers.keySet();
    }

    /**
     * Indicates if the response has headers.
     * 
     * @return <code>true</code> if headers are present, <code>false</code>
     *         otherwise
     */
    public boolean hasHeaders() {
        return !this.headers.isEmpty();
    }

    // Overridden methods follow

    @Override
    public void addCookie(Cookie cookie) {
        if (cookie == null) {
            return;
        }
        this.cookies.add(cookie);
    }

    @Override
    public boolean containsHeader(String name) {
        return this.headers.containsKey(name.toLowerCase());
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
    public void sendError(int sc, String msg) {
        this.status = sc;
        this.statusMessage = msg;
    }

    @Override
    public void sendError(int sc) {
        this.setStatus(sc);
    }

    @Override
    public void sendRedirect(String location) {
        this.status = HttpStatusCode.TEMPORARY_REDIRECT;
        this.headers.put(HttpHeaderName.LOCATION, location);
    }

    @Override
    public void setDateHeader(String name, long date) {
        name = name.toLowerCase();
        this.headers.remove(name);
        this.addDateHeader(name, date);
    }

    @Override
    public void addDateHeader(String name, long date) {
        name = name.toLowerCase();
        this.headers.put(name, new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(date)));
    }

    @Override
    public void setHeader(String name, String value) {
        name = name.toLowerCase();
        this.headers.remove(name);
        this.headers.put(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        name = name.toLowerCase();
        this.headers.put(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        name = name.toLowerCase();
        this.headers.remove(name);
        this.headers.put(name, String.valueOf(value));
    }

    @Override
    public void addIntHeader(String name, int value) {
        name = name.toLowerCase();
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
