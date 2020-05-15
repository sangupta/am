package com.sangupta.am.servlet;

import javax.el.ELContext;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link MockJspContext}
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
@SuppressWarnings("deprecation")
public class MockJspContextTest {
    
    @Test
    public void test() {
        MockJspContext jspContext = new MockJspContext();
        
        jspContext.setAttribute("hello", "world");
        Assert.assertEquals("world", jspContext.getAttribute("hello"));
        Assert.assertEquals("world", jspContext.findAttribute("hello"));
        Assert.assertNull(jspContext.findAttribute("404"));
        jspContext.removeAttribute("hello");
        Assert.assertNull(jspContext.getAttribute("hello"));

        jspContext.setAttribute("hellops", "page-scope", PageContext.PAGE_SCOPE);
        jspContext.setAttribute("hellors", "request-scope", PageContext.REQUEST_SCOPE);
        jspContext.setAttribute("helloss", "session-scope", PageContext.SESSION_SCOPE);
        jspContext.setAttribute("helloas", "app-scope", PageContext.APPLICATION_SCOPE);

        Assert.assertEquals("page-scope", jspContext.getAttribute("hellops", PageContext.PAGE_SCOPE));
        Assert.assertEquals("request-scope", jspContext.getAttribute("hellors", PageContext.REQUEST_SCOPE));
        Assert.assertEquals("session-scope", jspContext.getAttribute("helloss", PageContext.SESSION_SCOPE));
        Assert.assertEquals("app-scope", jspContext.getAttribute("helloas", PageContext.APPLICATION_SCOPE));

        Assert.assertEquals(PageContext.PAGE_SCOPE, jspContext.getAttributesScope("hellops"));
        Assert.assertEquals(PageContext.REQUEST_SCOPE, jspContext.getAttributesScope("hellors"));
        Assert.assertEquals(PageContext.SESSION_SCOPE, jspContext.getAttributesScope("helloss"));
        Assert.assertEquals(PageContext.APPLICATION_SCOPE, jspContext.getAttributesScope("helloas"));
        Assert.assertEquals(0, jspContext.getAttributesScope("notfound"));

        Assert.assertEquals("page-scope", jspContext.findAttribute("hellops"));
        Assert.assertEquals("request-scope", jspContext.findAttribute("hellors"));
        Assert.assertEquals("session-scope", jspContext.findAttribute("helloss"));
        Assert.assertEquals("app-scope", jspContext.findAttribute("helloas"));

        Assert.assertTrue(jspContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE).hasMoreElements());
        Assert.assertTrue(jspContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE).hasMoreElements());
        Assert.assertTrue(jspContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE).hasMoreElements());
        Assert.assertTrue(jspContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE).hasMoreElements());

        jspContext.removeAttribute("hellops", PageContext.PAGE_SCOPE);
        jspContext.removeAttribute("hellors", PageContext.REQUEST_SCOPE);
        jspContext.removeAttribute("helloss", PageContext.SESSION_SCOPE);
        jspContext.removeAttribute("helloas", PageContext.APPLICATION_SCOPE);

        Assert.assertNull(jspContext.getAttribute("hellops", PageContext.PAGE_SCOPE));
        Assert.assertNull(jspContext.getAttribute("hellors", PageContext.REQUEST_SCOPE));
        Assert.assertNull(jspContext.getAttribute("helloss", PageContext.SESSION_SCOPE));
        Assert.assertNull(jspContext.getAttribute("helloas", PageContext.APPLICATION_SCOPE));

        Assert.assertFalse(jspContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE).hasMoreElements());
        Assert.assertFalse(jspContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE).hasMoreElements());
        Assert.assertFalse(jspContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE).hasMoreElements());
        Assert.assertFalse(jspContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE).hasMoreElements());

        ELContext ec = Mockito.mock(ELContext.class);
        jspContext.setElContext(ec);
        Assert.assertEquals(ec, jspContext.getELContext());

        ExpressionEvaluator ee = Mockito.mock(ExpressionEvaluator.class);
        jspContext.setExpressionEvaluator(ee);
        Assert.assertEquals(ee, jspContext.getExpressionEvaluator());

        VariableResolver vr = Mockito.mock(VariableResolver.class);
        jspContext.setVariableResolver(vr);
        Assert.assertEquals(vr, jspContext.getVariableResolver());

        MockJspWriter writer = new MockJspWriter();
        jspContext.setJspWriter(writer);
        Assert.assertEquals(writer, jspContext.getOut());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAttribute() {
        MockJspContext jspContext = new MockJspContext();
        jspContext.removeAttribute("hello", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetAttribute() {
        MockJspContext jspContext = new MockJspContext();
        jspContext.setAttribute("hello", "world", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttribute() {
        MockJspContext jspContext = new MockJspContext();
        jspContext.getAttribute("hello", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttributeNamesInScope() {
        MockJspContext jspContext = new MockJspContext();
        jspContext.getAttributeNamesInScope(0);
    }

}
