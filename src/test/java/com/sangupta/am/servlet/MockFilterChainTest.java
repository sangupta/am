package com.sangupta.am.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockFilterChain}.
 * 
 * @author sangupta
 *
 */
public class MockFilterChainTest {
    
    @Test
    public void testMockFilterChain() throws IOException, ServletException {
        MockFilterChain chain = new MockFilterChain();
        Assert.assertFalse(chain.isChainInvoked());
        chain.doFilter(null, null);
        Assert.assertTrue(chain.isChainInvoked());
    }

}
