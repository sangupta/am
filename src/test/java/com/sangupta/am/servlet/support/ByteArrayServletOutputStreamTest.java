package com.sangupta.am.servlet.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ByteArrayServletOutputStream}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
public class ByteArrayServletOutputStreamTest {

    @Test
    public void testHashCodeAndEquals() throws IOException {
        ByteArrayServletOutputStream s1 = new ByteArrayServletOutputStream();
        ByteArrayServletOutputStream s2 = new ByteArrayServletOutputStream();
        
        Assert.assertNotEquals(s1.hashCode(), s2.hashCode());
        Assert.assertEquals(s1.hashCode(), s1.hashCode());
        Assert.assertEquals(s2.hashCode(), s2.hashCode());
        
        Assert.assertTrue(s1.equals(s1));
        Assert.assertFalse(s1.equals(null));
        Assert.assertFalse(s1.equals("hello"));
        Assert.assertFalse(s1.equals(s2));

        s1.close();
        s2.close();
    }

    @Test
    public void testWithoutObtainingWriter() throws IOException {
        ByteArrayServletOutputStream stream = new ByteArrayServletOutputStream();

        stream.write(Integer.MAX_VALUE);
        Assert.assertEquals(1, stream.getLength());

        stream.write("hello".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals(6, stream.getLength());

        stream.write("hello".getBytes(StandardCharsets.UTF_8), 2, 2);
        Assert.assertEquals(8, stream.getLength());

        stream.print(true);
        Assert.assertEquals(12, stream.getLength());

        stream.print('a');
        Assert.assertEquals(13, stream.getLength());

        stream.print(Double.MAX_VALUE);
        Assert.assertEquals(35, stream.getLength());

        stream.print(Float.MAX_VALUE);
        Assert.assertEquals(47, stream.getLength());

        stream.print(Integer.MAX_VALUE);
        Assert.assertEquals(57, stream.getLength());

        stream.print(Long.MAX_VALUE);
        Assert.assertEquals(76, stream.getLength());

        stream.print("hello");
        Assert.assertEquals(81, stream.getLength());

        stream.println();
        Assert.assertEquals(82, stream.getLength());

        stream.println(true);
        Assert.assertEquals(87, stream.getLength());

        stream.println('a');
        Assert.assertEquals(89, stream.getLength());

        stream.println(Double.MAX_VALUE);
        Assert.assertEquals(112, stream.getLength());

        stream.println(Float.MAX_VALUE);
        Assert.assertEquals(125, stream.getLength());

        stream.println(Integer.MAX_VALUE);
        Assert.assertEquals(136, stream.getLength());

        stream.println(Long.MAX_VALUE);
        Assert.assertEquals(156, stream.getLength());

        stream.println("hello");
        Assert.assertEquals(162, stream.getLength());

        Assert.assertEquals(162, stream.getBytes().length);
        
        stream.close();
    }

    @Test
    public void testWithObtainingWriter() throws IOException {
        ByteArrayServletOutputStream stream = new ByteArrayServletOutputStream();

        Assert.assertNotNull(stream.getWriter());
        stream.write(Integer.MAX_VALUE);
        Assert.assertEquals(1, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.write("hello".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals(6, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.write("hello".getBytes(StandardCharsets.UTF_8), 2, 2);
        Assert.assertEquals(8, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print(true);
        Assert.assertEquals(12, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print('a');
        Assert.assertEquals(13, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print(Double.MAX_VALUE);
        Assert.assertEquals(35, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print(Float.MAX_VALUE);
        Assert.assertEquals(47, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print(Integer.MAX_VALUE);
        Assert.assertEquals(57, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print(Long.MAX_VALUE);
        Assert.assertEquals(76, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.print("hello");
        Assert.assertEquals(81, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println();
        Assert.assertEquals(82, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println(true);
        Assert.assertEquals(87, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println('a');
        Assert.assertEquals(89, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println(Double.MAX_VALUE);
        Assert.assertEquals(112, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println(Float.MAX_VALUE);
        Assert.assertEquals(125, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println(Integer.MAX_VALUE);
        Assert.assertEquals(136, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println(Long.MAX_VALUE);
        Assert.assertEquals(156, stream.getLength());

        Assert.assertNotNull(stream.getWriter());
        stream.println("hello");
        Assert.assertEquals(162, stream.getLength());

        Assert.assertEquals(162, stream.getBytes().length);

        stream.flush();
        Assert.assertEquals(162, stream.getBytes().length);

        Assert.assertNotNull(stream.getByteArrayOutputStream());
        
        stream.close();
    }

}
