package com.sangupta.am.servlet;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.support.MockUrlEncoder;

/**
 * Unit tests for {@link MockUrlEncoder}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
public class MockUrlEncoderTest {
    
    @Test
    public void testEncoder() {
        MockUrlEncoder encoder = new MockUrlEncoder();
        
        Assert.assertEquals("http://localhost", encoder.encodeRedirectUrl("http://localhost"));
        Assert.assertEquals("http://localhost", encoder.encodeUrl("http://localhost"));
        Assert.assertEquals("http://localhost", encoder.encodeURL("http://localhost"));
        Assert.assertEquals("http://localhost", encoder.encodeRedirectURL("http://localhost"));
    }    

}
