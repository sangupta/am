package com.sangupta.am.servlet;

import java.util.Enumeration;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.support.MockUrlEncoder;
import com.sangupta.jerry.constants.HttpHeaderName;

/**
 * Unit tests for {@link MockHttpServletResponse}.
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 */
public class MockHttpServletResponseTest {
    
    @Test
    public void testStatus() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Assert.assertEquals(0, response.getStatus());
        
        response.status = 200;        
        Assert.assertEquals(200, response.getStatus());
        
        response.setStatus(400);
        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals(400, response.status);
    }
    
    @Test
    public void testStatusMessage() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Assert.assertNull(response.statusMessage);
        response.statusMessage = "am";
        Assert.assertEquals("am", response.statusMessage);
        Assert.assertEquals("am", response.getStatusMessage());
    }
	
    @Test
    public void testStatusAndMessage() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Assert.assertEquals(0, response.getStatus());
        
        response.setStatus(200, "hello"); 
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("hello", response.getStatusMessage());
        Assert.assertEquals("hello", response.statusMessage);        
    }
    
	@Test
	public void testCookies() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		Assert.assertEquals(0, response.getNumCookies());
		response.addCookie(null);
		Assert.assertEquals(0, response.getNumCookies());
		
		Cookie cookie = new Cookie("am", "test");
		response.addCookie(cookie);
		
		Assert.assertEquals(1, response.getNumCookies());
		Assert.assertEquals(cookie, response.getCookie("am"));
		Assert.assertTrue(response.hasCookie("am"));
		
		Assert.assertNull(response.getCookie("non-existent"));
		Assert.assertFalse(response.hasCookie("non-existent"));
	}

	@Test
    public void testHeaders() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    Assert.assertFalse(response.getHeaderNames().hasMoreElements());
	    Assert.assertEquals(0, response.getHeaderNamesAsSet().size());
	    Assert.assertEquals(0, response.getHeaderValues("am").size());
	    Assert.assertFalse(response.hasHeaders());
        
	    // add header
	    response.addHeader("am", "test");
	    Assert.assertEquals("test", response.getHeader("am"));
	    Assert.assertEquals(1, response.getHeaderNamesAsSet().size());
	    Assert.assertEquals(1, response.getHeaderValues("am").size());
	    Assert.assertTrue(response.hasHeaders());
	    
	    // get int header
	    response.addHeader("hint", "23");
	    Assert.assertEquals(23, response.getIntHeader("hint"));
	    
	    // add date header
	    long time = System.currentTimeMillis();
	    long compare = time / 1000;
	    response.addDateHeader("hlong", time);
	    Assert.assertEquals(compare, response.getDateHeader("hlong") / 1000);
	    
	    // set date header
	    response.setDateHeader("dh2", time);
	    Assert.assertEquals(compare, response.getDateHeader("dh2") / 1000);
	    
	    // set header
        Assert.assertFalse(response.containsHeader("h1"));
        Assert.assertEquals(0, response.getHeaderValues("h1").size());
	    response.setHeader("h1", "hello");
	    Assert.assertEquals(1, response.getHeaderValues("h1").size());
        Assert.assertEquals("hello", response.headers.getOne("h1"));
        
	    response.setHeader("h1", "world");
        Assert.assertEquals(1, response.getHeaderValues("h1").size());
        Assert.assertEquals("world", response.headers.getOne("h1"));
        
        // add more
        response.addHeader("h1", "hello");
        Assert.assertEquals(2, response.getHeaderValues("h1").size());
        Assert.assertTrue(response.containsHeader("h1"));
        
        // set int header
        Assert.assertFalse(response.containsHeader("ih"));
        response.setIntHeader("ih", 200);
        Assert.assertEquals(200, response.getIntHeader("ih"));
        
        response.addIntHeader("ih", 500);
        Assert.assertEquals(200, response.getIntHeader("ih"));
        Assert.assertEquals(2, response.getHeaderValues("ih").size());
	}
	
	@Test
	public void testSendError() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    
	    Assert.assertEquals(0, response.status);
	    Assert.assertNull(response.statusMessage);
	    response.sendError(200);
	    Assert.assertEquals(200, response.status);
        Assert.assertNull(response.statusMessage);
        
        // one more
        response = new MockHttpServletResponse();
        
        Assert.assertEquals(0, response.status);
        Assert.assertNull(response.statusMessage);
        response.sendError(200, "done");
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("done", response.statusMessage);
	}
	
	@Test
    public void testSendRedirect() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    
	    Assert.assertEquals(0, response.headers.size());
	    Assert.assertEquals(0, response.status);
	    
	    response.sendRedirect("http://localhost/redirect");
	    Assert.assertEquals(307, response.status);
	    Assert.assertEquals(1, response.headers.size());
	    Assert.assertEquals("http://localhost/redirect", response.headers.getOne(HttpHeaderName.LOCATION));
	}
	
	@Test
    public void testSetUrlEncoder() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    
	    Assert.assertNotNull(response.urlEncoder);
	    response.setUrlEncoder(null);
	    Assert.assertNull(response.urlEncoder);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testInvalidDateHeader() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    response.setHeader("hello", "world");
	    response.getDateHeader("hello");
	}
	
	@Test
    public void testGetHeadersEnumeration() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    Assert.assertNull(response.getHeaders("h1"));
	    
	    response.addHeader("h1", "v1");
	    Enumeration<String> ei = response.getHeaders("h1");
	    Assert.assertNotNull(ei);
	    Assert.assertNotNull(ei.nextElement());
	    Assert.assertFalse(ei.hasMoreElements());
	    
	    response.addHeader("h1", "v2");
        ei = response.getHeaders("h1");
        Assert.assertNotNull(ei);
        Assert.assertNotNull(ei.nextElement());
        Assert.assertNotNull(ei.nextElement());
        Assert.assertFalse(ei.hasMoreElements());
	}
	
	@Test
	public void testEncoding() {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    MockUrlEncoder encoder = new MockUrlEncoder() {
	        @Override
	        public String encodeRedirectUrl(String url) {
	            return "erurl";
	        }
	        
	        @Override
	        public String encodeUrl(String url) {
	            return "eurl";
	        }
	        
	        @Override
	        public String encodeURL(String url) {
	            return "eURL";
	        }
	        
	        @Override
	        public String encodeRedirectURL(String url) {
	            return "erURL";
	        }
	    };
	    
	    response.setUrlEncoder(encoder);
	    Assert.assertEquals("erurl", response.encodeRedirectUrl("http://localhost"));
	    Assert.assertEquals("eurl", response.encodeUrl("http://localhost"));
	    Assert.assertEquals("eURL", response.encodeURL("http://localhost"));
	    Assert.assertEquals("erURL", response.encodeRedirectURL("http://localhost"));
	}
}
