package com.sangupta.am.servlet;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MockJspWriter}.
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 *
 */
public class MockJspWriterTest {
    
    @Test(expected = IOException.class)
    public void testClear() throws IOException {
        MockJspWriter jspWriter = new  MockJspWriter();
        jspWriter.clear();
        jspWriter.close();
    }

    @Test
    public void test() {
        MockJspWriter jspWriter = new  MockJspWriter();
        
        jspWriter.print(true);
        Assert.assertEquals(4, jspWriter.toString().length());

        jspWriter.print('a');
        Assert.assertEquals(5, jspWriter.toString().length());

        jspWriter.print(Double.MAX_VALUE);
        Assert.assertEquals(27, jspWriter.toString().length());

        jspWriter.print(Float.MAX_VALUE);
        Assert.assertEquals(39, jspWriter.toString().length());

        jspWriter.print(Integer.MAX_VALUE);
        Assert.assertEquals(49, jspWriter.toString().length());

        jspWriter.print(Long.MAX_VALUE);
        Assert.assertEquals(68, jspWriter.toString().length());

        jspWriter.print("hello");
        Assert.assertEquals(73, jspWriter.toString().length());
        
        jspWriter.print("hello".toCharArray());
        Assert.assertEquals(78, jspWriter.toString().length());
        
        jspWriter.print((Object) "hello");
        Assert.assertEquals(83, jspWriter.toString().length());
        
        jspWriter.println(true);
        Assert.assertEquals(88, jspWriter.toString().length());

        jspWriter.println('a');
        Assert.assertEquals(90, jspWriter.toString().length());

        jspWriter.println(Double.MAX_VALUE);
        Assert.assertEquals(113, jspWriter.toString().length());

        jspWriter.println(Float.MAX_VALUE);
        Assert.assertEquals(126, jspWriter.toString().length());

        jspWriter.println(Integer.MAX_VALUE);
        Assert.assertEquals(137, jspWriter.toString().length());

        jspWriter.println(Long.MAX_VALUE);
        Assert.assertEquals(157, jspWriter.toString().length());

        jspWriter.println("hello");
        Assert.assertEquals(163, jspWriter.toString().length());
        
        jspWriter.println("hello".toCharArray());
        Assert.assertEquals(169, jspWriter.toString().length());
        
        jspWriter.println((Object) "hello");
        Assert.assertEquals(175, jspWriter.toString().length());
        
        jspWriter.println();
        Assert.assertEquals(176, jspWriter.toString().length());
        
        jspWriter.newLine();
        Assert.assertEquals(177, jspWriter.toString().length());
        
        jspWriter.write("hello".toCharArray(), 2, 2);
        Assert.assertEquals(179, jspWriter.toString().length());
        
        Assert.assertNotNull(jspWriter.getWriter());
        Assert.assertNotNull(jspWriter.getStringWriter());
        
        jspWriter.clearBuffer();
        jspWriter.flush();

        Assert.assertEquals(0, jspWriter.getRemaining());
        
        jspWriter.close();
    }
}
