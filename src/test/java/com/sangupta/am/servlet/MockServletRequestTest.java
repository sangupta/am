package com.sangupta.am.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletInputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockServletRequest}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
public class MockServletRequestTest {

    @Test
    public void test() {
        MockServletRequest request = new MockHttpServletRequest();
        Assert.assertFalse(request.isSecure());
        
        request.setScheme("test");
        Assert.assertEquals("test", request.scheme);
        Assert.assertEquals("test", request.getScheme());
        Assert.assertFalse(request.isSecure());
        
        request.setScheme("https");
        Assert.assertTrue(request.isSecure());
        
        request.setCharacterEncoding("test-encoding");
        Assert.assertEquals("test-encoding", request.characterEncoding);
        Assert.assertEquals("test-encoding", request.getCharacterEncoding());
        
        request.setAttribute("hello", "world");
        Assert.assertEquals("world", request.getAttribute("hello"));
        Assert.assertNull(request.getAttribute("am"));
        Assert.assertNull(request.getAttribute(""));
        Enumeration<String> ei = request.getAttributeNames();
        Assert.assertNotNull(ei.nextElement());
        Assert.assertFalse(ei.hasMoreElements());
        request.removeAttribute("hello");
        Assert.assertNull(request.getAttribute("hello"));
        
        request.setContentLength(100);
        Assert.assertEquals(100, request.contentLength);
        Assert.assertEquals(100, request.getContentLength());
        
        request.setContentType("json");
        Assert.assertEquals("json", request.contentType);
        Assert.assertEquals("json", request.getContentType());
        
        request.setLocalAddress("127.0.0.1");
        Assert.assertEquals("127.0.0.1", request.localAddress);
        Assert.assertEquals("127.0.0.1", request.getLocalAddr());
        
        request.setLocalName("localhost");
        Assert.assertEquals("localhost", request.localName);
        Assert.assertEquals("localhost", request.getLocalName());
        
        request.setLocalPort(1309);
        Assert.assertEquals(1309, request.localPort);
        Assert.assertEquals(1309, request.getLocalPort());
        
        request.setProtocol("http");
        Assert.assertEquals("http", request.protocol);
        Assert.assertEquals("http", request.getProtocol());
        
        request.setRealPath("realpath");
        Assert.assertEquals("realpath", request.realPath);
        Assert.assertEquals("realpath", request.getRealPath(null));
        
        request.setRemoteAddress("8.8.8.8");
        Assert.assertEquals("8.8.8.8", request.remoteAddress);
        Assert.assertEquals("8.8.8.8", request.getRemoteAddr());
        
        request.setRemoteHost("google");
        Assert.assertEquals("google", request.remoteHost);
        Assert.assertEquals("google", request.getRemoteHost());
        
        request.setRemotePort(1309);
        Assert.assertEquals(1309, request.remotePort);
        Assert.assertEquals(1309, request.getRemotePort());
        
        request.setServerName("domdom");
        Assert.assertEquals("domdom", request.serverName);
        Assert.assertEquals("domdom", request.getServerName());
        
        request.setServerPort(1309);
        Assert.assertEquals(1309, request.serverPort);
        Assert.assertEquals(1309, request.getServerPort());
        
        request.setScheme("https");
        Assert.assertEquals("https", request.scheme);
        Assert.assertEquals("https", request.getScheme());
        
        request.parameters.put("hello", "world");
        Assert.assertEquals("world", request.getParameter("hello"));
        request.parameters.put("hello", "world2");
        request.parameters.put("hello", "world3");
        Assert.assertEquals("world", request.getParameter("hello"));
        Assert.assertEquals(3, request.getParameterValues("hello").length);
        Assert.assertNull(request.getParameterValues("am"));
        Assert.assertEquals(1, request.getParameterMap().size());
        
        
        ei = request.getParameterNames();
        Assert.assertNotNull(ei.nextElement());
        Assert.assertFalse(ei.hasMoreElements());
        
        request.parameters.clear();
        Assert.assertEquals(0, request.getParameterMap().size());
        
        Assert.assertNull(request.getLocale());
        request.locales.add(new Locale("en"));
        Assert.assertEquals(new Locale("en"), request.getLocale());
        
        Enumeration<Locale> el = request.getLocales();
        Assert.assertNotNull(el.nextElement());
        Assert.assertFalse(el.hasMoreElements());
        
        ServletInputStream sis = new ServletInputStream() { @Override public int read() throws IOException { return 0; } };

        request.setInputStream(sis);
        Assert.assertEquals(sis, request.getInputStream());
        
        Assert.assertNotNull(request.getRequestDispatcher(null));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateException() {
        MockServletRequest request = new MockHttpServletRequest();
        ServletInputStream sis = new ServletInputStream() { @Override public int read() throws IOException { return 0; } };
        request.setInputStream(sis);
        request.getInputStream();
        request.getReader();
    }
    
    @Test(expected = NullPointerException.class)
    public void testNPE() {
        MockServletRequest request = new MockHttpServletRequest();
        request.setInputStream(null);
        request.getReader();
    }
    
    @Test
    public void testReader() {
        MockServletRequest request = new MockHttpServletRequest();
        ServletInputStream sis = new ServletInputStream() { @Override public int read() throws IOException { return 0; } };
        request.setInputStream(sis);
        Assert.assertNotNull(request.getReader());
    }
    
    @Test
    public void testGetDefault() {
        MockServletRequest request = MockServletRequest.getDefault();
        Assert.assertEquals("HTTP/1.1", request.protocol);
        Assert.assertEquals("http", request.scheme);
    }

}
