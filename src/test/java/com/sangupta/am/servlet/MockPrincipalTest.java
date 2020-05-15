package com.sangupta.am.servlet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockPrincipal}.
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 *
 */
public class MockPrincipalTest {

    @Test
    public void testMockPrincipal() {
        MockPrincipal mp = new MockPrincipal();
        Assert.assertNull(mp.name);
        Assert.assertNull(mp.getName());
        
        mp.setName("hello");
        Assert.assertEquals("hello", mp.name);
        Assert.assertEquals("hello", mp.getName());
        
        mp = new MockPrincipal("world");
        Assert.assertEquals("world", mp.name);
        Assert.assertEquals("world", mp.getName());
    }
    
}
