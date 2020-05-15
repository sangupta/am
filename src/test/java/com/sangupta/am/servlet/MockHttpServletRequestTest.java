package com.sangupta.am.servlet;

import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

public class MockHttpServletRequestTest {

    @Test
    public void testSessionValid() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertFalse(request.sessionValid);
        request.setSessionValid(true);
        Assert.assertTrue(request.sessionValid);
        Assert.assertTrue(request.isRequestedSessionIdValid());
    }
    
    @Test
    public void testUserRoles() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertEquals(0, request.userRoles.size());
        request.addUserRole("hello");
        Assert.assertEquals(1, request.userRoles.size());
        request.addUserRole("world");
        Assert.assertEquals(2, request.userRoles.size());
        request.addUserRole("world");
        Assert.assertEquals(2, request.userRoles.size());
        request.removeUserRole("world");
        Assert.assertEquals(1, request.userRoles.size());
        
        Assert.assertFalse(request.isUserInRole(null));
        Assert.assertFalse(request.isUserInRole(""));
        Assert.assertTrue(request.isUserInRole("hello"));
        Assert.assertFalse(request.isUserInRole("world"));
    }
    
    @Test
    public void testSessionIdFromUrl() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertFalse(request.sessionIdFromURL);
        request.setSessionIdFromURL(true);
        Assert.assertTrue(request.sessionIdFromURL);
        Assert.assertTrue(request.isRequestedSessionIdFromUrl());
        Assert.assertTrue(request.isRequestedSessionIdFromURL());
    }
    
    @Test
    public void testSessionIdFromCookie() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertFalse(request.sessionIdFromCookie);
        request.setSessionIdFromCookie(true);
        Assert.assertTrue(request.sessionIdFromCookie);
        Assert.assertTrue(request.isRequestedSessionIdFromCookie());
    }
    
    @Test
    public void testRequestURI() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("hello");
        Assert.assertEquals("hello", request.requestURI);
        Assert.assertEquals("hello", request.getRequestURI());
    }
    
    @Test
    public void testRequestURL() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        StringBuffer sb = new StringBuffer();
        request.setRequestURL(sb);
        Assert.assertEquals(sb, request.requestURL);
        Assert.assertEquals(sb, request.getRequestURL());
    }
    
    @Test
    public void testServletPath() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath("hello");
        Assert.assertEquals("hello", request.servletPath);
        Assert.assertEquals("hello", request.getServletPath());
    }
    
    @Test
    public void testMethod() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        Assert.assertEquals("POST", request.method);
        Assert.assertEquals("POST", request.getMethod());
    }
    
    @Test
    public void testPathInfo() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setPathInfo("hello");
        Assert.assertEquals("hello", request.pathInfo);
        Assert.assertEquals("hello", request.getPathInfo());
    }

    @Test
    public void testPathTranslated() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setPathTranslated("hello");
        Assert.assertEquals("hello", request.pathTranslated);
        Assert.assertEquals("hello", request.getPathTranslated());
    }
    
    @Test
    public void testContextPath() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("hello");
        Assert.assertEquals("hello", request.contextPath);
        Assert.assertEquals("hello", request.getContextPath());
    }
    
    @Test
    public void testQueryString() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setQueryString("hello");
        Assert.assertEquals("hello", request.queryString);
        Assert.assertEquals("hello", request.getQueryString());
    }
    
    @Test
    public void testRemoteUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteUser("hello");
        Assert.assertEquals("hello", request.remoteUser);
        Assert.assertEquals("hello", request.getRemoteUser());
    }

    @Test
    public void testAuthType() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAuthType("basic");
        Assert.assertEquals("basic", request.authType);
        Assert.assertEquals("basic", request.getAuthType());
    }
    
    @Test
    public void testPrincipal() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Principal p = new Principal() {
            
            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return null;
            }
        };
        request.setPrincipal(p);
        Assert.assertEquals(p, request.principal);
        Assert.assertEquals(p, request.getUserPrincipal());
    }
    
    @Test
    public void testSession() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertNull(request.getSession(false));
        
        HttpSession session1 = request.getSession();
        Assert.assertNotNull(session1);
        HttpSession session2 = request.getSession(true);
        Assert.assertNotNull(session2);
        Assert.assertEquals(session1, session2);
        
        Assert.assertNotNull(request.getRequestedSessionId());
    }
    
    @Test
    public void testCookies() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Assert.assertEquals(0, request.getNumCookies());
        
        request.addCookie("hello", "world");
        Assert.assertEquals(1, request.getNumCookies());
        Assert.assertEquals("world", request.getCookie("hello").getValue());
        
        Cookie cookie = new Cookie("h1", "v1");
        request.addCookie(cookie);
        Assert.assertEquals(2, request.getNumCookies());
        Assert.assertEquals("world", request.getCookie("hello").getValue());
        Assert.assertEquals("v1", request.getCookie("h1").getValue());
        Assert.assertEquals(2, request.getCookies().length);
        
     
        request.clearCookies();
        request.addCookie(null);
        Assert.assertEquals(0, request.getNumCookies());
        Assert.assertNull(request.getCookie("hello"));
        Assert.assertNull(request.getCookie("h1"));
    }
    
    @Test
    public void testGetDefault() {
        MockHttpServletRequest request = MockHttpServletRequest.getDefault("hello");
        Assert.assertEquals("HTTP/1.1", request.protocol);
        Assert.assertEquals("hello", request.requestURI);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.headers.put("hello", "world");
        request.getDateHeader("hello");
    }
    
    @Test
    public void testHeaders() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        
        long time = System.currentTimeMillis();
        long compare = time / 1000;
        request.addDateHeader("hlong", time);
        Assert.assertEquals(compare, request.getDateHeader("hlong") / 1000);
        Assert.assertEquals(0, request.getDateHeader("world"));
        
        request.setHeader("hint", "23");
        Assert.assertEquals(23, request.getIntHeader("hint"));
        
        request.addIntHeader("hint2", 46);
        Assert.assertEquals(46, request.getIntHeader("hint2"));        
        request.addIntHeader("hint2", 48);
        Assert.assertEquals(46, request.getIntHeader("hint2"));
        
        Assert.assertEquals(0, request.getIntHeader("hint3"));
        
        Enumeration<String> es = request.getHeaderNames();
        Assert.assertNotNull(es.nextElement());
        Assert.assertNotNull(es.nextElement());
        Assert.assertNotNull(es.nextElement());
        Assert.assertFalse(es.hasMoreElements());
        
        es = request.getHeaders("hint2");
        Assert.assertNotNull(es.nextElement());
        Assert.assertNotNull(es.nextElement());
        Assert.assertFalse(es.hasMoreElements());
        
        Assert.assertNull(request.getHeaders("hint3"));
    }
    
}
