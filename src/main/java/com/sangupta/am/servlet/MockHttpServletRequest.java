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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sangupta.jerry.ds.SimpleMultiMap;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.UriUtils;

/**
 * Implementation of {@link HttpServletRequest} for unit-testing that keeps all
 * parameters within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class MockHttpServletRequest extends MockServletRequest implements HttpServletRequest {

    /**
     * {@link Cookie}s associated with this request
     */
    public final List<Cookie> cookies = new ArrayList<>();

    /**
     * Headers associated with this request
     */
    public final SimpleMultiMap<String, String> headers = new SimpleMultiMap<>();

    /**
     * The method name associated with this request. Default value is
     * <code>GET</code>
     */
    public String method = "GET";

    /**
     * {@link Principal} associated with this request
     */
    public Principal principal;

    /**
     * {@link HttpSession} associated with this request
     */
    public HttpSession session;

    /**
     * The URI associated with this request
     */
    public String requestURI;

    /**
     * The URL associated with this request
     */
    public StringBuffer requestURL;

    /**
     * The name of the authentication scheme used to protect the servlet.
     */
    public String authType;

    public String contextPath;

    public String remoteUser;

    public String pathInfo;

    public String pathTranslated;

    public String queryString;

    public String servletPath;

    public boolean sessionIdFromCookie;

    public boolean sessionIdFromURL;

    public boolean sessionValid;

    public final Set<String> userRoles = new HashSet<>();

    public static MockHttpServletRequest getDefault(String path) {
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.protocol = "HTTP/1.1";
        request.scheme = "http";

        request.requestURI = "/context/";
        request.requestURL = new StringBuffer("http://localhost:80/context/");

        request.serverName = "localhost";
        request.serverPort = 8080;

        request.characterEncoding = null;
        request.contentType = null;

        request.remoteAddress = null;
        request.remoteHost = null;
        request.remotePort = -1;

        request.requestURI = path;
        request.requestURL = new StringBuffer(UriUtils.addWebPaths("http://localhost:8080/context/", path));

        request.localAddress = null;
        request.localPort = -1;

        return request;
    }

    public void setSessionValid(boolean sessionValid) {
        this.sessionValid = sessionValid;
    }

    public void addUserRole(String role) {
        this.userRoles.add(role);
    }

    public void removeUserRole(String role) {
        this.userRoles.remove(role);
    }

    public void setSessionIdFromCookie(boolean sessionIdFromCookie) {
        this.sessionIdFromCookie = sessionIdFromCookie;
    }

    public void setSessionIdFromURL(boolean sessionIdFromURL) {
        this.sessionIdFromURL = sessionIdFromURL;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public void setPathTranslated(String pathTranslated) {
        this.pathTranslated = pathTranslated;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
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
        if (cookie == null) {
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

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    // Overridden methods follow

    @Override
    public String getAuthType() {
        return this.authType;
    }

    @Override
    public Cookie[] getCookies() {
        return this.cookies.toArray(new Cookie[] {});
    }
    
    public int getNumCookies() {
        return this.cookies.size();
    }
    
    public Cookie getCookie(String name) {
        for(Cookie cookie : this.cookies) {
            if(name.equals(cookie.getName())) {
                return cookie;
            }
        }
        
        return null;
    }
    
    public void clearCookies() {
        this.cookies.clear();
    }
    
    public void addDateHeader(String name, long date) {
        name = name.toLowerCase();
        this.headers.put(name, new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(date)));
    }

    @Override
    public long getDateHeader(String name) {
        String header = this.getHeader(name);
        if (AssertUtils.isEmpty(header)) {
            return 0;
        }

        try {
            return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(header).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getHeader(String name) {
        name = name.toLowerCase();
        return this.headers.getOne(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        name = name.toLowerCase();
        if (!this.headers.containsKey(name)) {
            return null;
        }

        return Collections.enumeration(this.headers.getValues(name));
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.headers.keySet());
    }

    public void setHeader(String name, String value) {
        name = name.toLowerCase();
        this.headers.remove(name);
        this.headers.put(name, value);
    }

    public void addIntHeader(String name, int value) {
        name = name.toLowerCase();
        this.headers.put(name, String.valueOf(value));
    }
    
    @Override
    public int getIntHeader(String name) {
        name = name.toLowerCase();

        String header = this.getHeader(name);
        if (AssertUtils.isEmpty(header)) {
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
        return this.pathInfo;
    }

    @Override
    public String getPathTranslated() {
        return this.pathTranslated;
    }

    @Override
    public String getContextPath() {
        return this.contextPath;
    }

    @Override
    public String getQueryString() {
        return this.queryString;
    }

    @Override
    public String getRemoteUser() {
        return this.remoteUser;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (AssertUtils.isEmpty(role)) {
            return false;
        }

        if (this.userRoles.contains(role)) {
            return true;
        }

        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public String getRequestedSessionId() {
        return this.getSession().getId();
    }

    @Override
    public String getRequestURI() {
        return this.requestURI;
    }

    @Override
    public StringBuffer getRequestURL() {
        return this.requestURL;
    }

    @Override
    public String getServletPath() {
        return this.servletPath;
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (this.session != null) {
            return session;
        }

        if (create) {
            this.session = new MockHttpSession();
            return this.session;
        }

        return null;
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(true);
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return this.sessionValid;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return this.sessionIdFromCookie;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return this.sessionIdFromURL;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return this.sessionIdFromURL;
    }

}
