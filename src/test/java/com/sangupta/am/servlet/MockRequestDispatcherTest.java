package com.sangupta.am.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockRequestDispatcher}.
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 */
public class MockRequestDispatcherTest {

    @Test
    public void testNoArgsConstructor() {
        MockRequestDispatcher d = new MockRequestDispatcher();
        Assert.assertNull(d.path);
        Assert.assertNull(d.getPath());
        
        d.setPath("http://localhost");
        Assert.assertEquals("http://localhost", d.path);
        Assert.assertEquals("http://localhost", d.getPath());
    }
    
    @Test
    public void testArgsConstructor() {
        MockRequestDispatcher d = new MockRequestDispatcher("http://localhost");
        Assert.assertEquals("http://localhost", d.path);
        Assert.assertEquals("http://localhost", d.getPath());
    }
    
    @Test
    public void test() throws ServletException, IOException {
        MockRequestDispatcher d = new MockRequestDispatcher();
        d.forward(null, null);
        d.include(null, null);
    }
}
