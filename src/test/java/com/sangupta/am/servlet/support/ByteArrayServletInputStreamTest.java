package com.sangupta.am.servlet.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ByteArrayServletInputStream}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
public class ByteArrayServletInputStreamTest {
    
    @Test
    public void testString() throws IOException {
        ByteArrayServletInputStream stream = new ByteArrayServletInputStream("hello");
        Assert.assertEquals("hello", IOUtils.toString(stream, StandardCharsets.UTF_8));
    }
    
    @Test
    public void testByteArray() throws IOException {
        ByteArrayServletInputStream stream = new ByteArrayServletInputStream("hello".getBytes());
        Assert.assertEquals("hello", IOUtils.toString(stream, StandardCharsets.UTF_8));
    }

}
