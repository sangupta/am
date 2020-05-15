package com.sangupta.am.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockHttpSession}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
@SuppressWarnings("deprecation")
public class MockHttpSessionTest {

    @Test
    public void test() {
        MockHttpSession session = new MockHttpSession();
        
        Assert.assertNotNull(session.getId());
        
        session.setSessionID("sid");
        Assert.assertEquals("sid", session.getId());
        Assert.assertTrue(session.getLastAccessedTime() > 0);

        Assert.assertTrue(session.isNew());
        Assert.assertTrue(session.isSessionValid());

        session.setMaxInactiveInterval(100);
        Assert.assertEquals(100, session.getMaxInactiveInterval());

        session.setAttribute("hello", "world");
        Assert.assertEquals("world", session.getAttribute("hello"));
        Enumeration<String> ei = session.getAttributeNames();
        Assert.assertNotNull(ei.nextElement());
        Assert.assertFalse(ei.hasMoreElements());
        Assert.assertEquals(1, session.getValueNames().length);
        
        session.removeAttribute("hello");
        Assert.assertNull(session.getAttribute("hello"));
        Assert.assertEquals(0, session.getValueNames().length);
        
        session.setCreated(200);
        Assert.assertEquals(200, session.getCreationTime());

        HttpSessionContext context = new HttpSessionContext() {

            @Override
            public HttpSession getSession(String sessionId) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            @SuppressWarnings("rawtypes")
            public Enumeration getIds() {
                return null;
            }
        };
        session.setHttpSessionContext(context);
        Assert.assertEquals(context, session.httpSessionContext);
        Assert.assertEquals(context, session.getSessionContext());

        Assert.assertEquals(0, session.attributes.size());
        session.putValue("test", "value");
        Assert.assertEquals(1, session.attributes.size());
        Assert.assertEquals("value", session.getValue("test"));
        session.removeValue("test");
        Assert.assertEquals(0, session.attributes.size());

        Assert.assertFalse(session.isNew());

        ServletContext sc = new TestServletContext();
        session.setServletContext(sc);
        Assert.assertEquals(sc, session.getServletContext());

        Assert.assertTrue(session.isSessionValid());
        session.invalidate();
        Assert.assertFalse(session.isSessionValid());
    }

    @SuppressWarnings("rawtypes")
    private static class TestServletContext implements ServletContext {
        @Override public void setAttribute(String name, Object object) { }
        @Override public void removeAttribute(String name) { }
        @Override public void log(String message, Throwable throwable) { }
        @Override public void log(Exception exception, String msg) { }
        @Override public void log(String msg) { }
        @Override public Enumeration getServlets() { return null; }
        @Override public Enumeration getServletNames() { return null; }
        @Override public String getServletContextName() { return null; }
        @Override public Servlet getServlet(String name) throws ServletException { return null; }
        @Override public String getServerInfo() { return null; }
        @Override public Set getResourcePaths(String path) { return null; }
        @Override public InputStream getResourceAsStream(String path) { return null; }
        @Override public URL getResource(String path) throws MalformedURLException { return null; }
        @Override public RequestDispatcher getRequestDispatcher(String path) { return null; }
        @Override public String getRealPath(String path) { return null; }
        @Override public RequestDispatcher getNamedDispatcher(String name) { return null; }
        @Override public int getMinorVersion() { return 0; }
        @Override public String getMimeType(String file) { return null; }
        @Override public int getMajorVersion() { return 0; }
        @Override public Enumeration getInitParameterNames() { return null; }
        @Override public String getInitParameter(String name) { return null; }
        @Override public String getContextPath() { return null; }
        @Override public ServletContext getContext(String uripath) { return null; }
        @Override public Enumeration getAttributeNames() { return null; }
        @Override public Object getAttribute(String name) { return null; }
    }
    
}
