package com.sangupta.am.servlet;

import java.io.IOException;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockServletResponse}
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
public class MockServletResponseTest {

    @Test
    public void test() throws IOException {
        MockServletResponse response = new MockServletResponse();
        
        response.setCharacterEncoding("ce");
        Assert.assertEquals("ce", response.getCharacterEncoding());
        
        response.setBufferSize(128);
        Assert.assertEquals(128, response.getBufferSize());
        
        response.setContentLength(100);
        Assert.assertEquals(100, response.contentLength);
        
        response.setContentType("ct");
        Assert.assertEquals("ct", response.getContentType());
        
        response.setLocale(new Locale("en"));
        Assert.assertEquals(new Locale("en"), response.getLocale());
        
        response.flushBuffer();
        response.reset();
        response.resetBuffer();
        
        Assert.assertNotNull(response.getOutputStream());
        Assert.assertNotNull(response.getWriter());
        
        Assert.assertFalse(response.isCommitted());
        response.committed = true;
        Assert.assertTrue(response.isCommitted());
    }

}
