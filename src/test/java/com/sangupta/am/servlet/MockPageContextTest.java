package com.sangupta.am.servlet;

import java.io.IOException;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.sangupta.am.servlet.support.MockExceptionHandler;
import com.sangupta.am.servlet.support.MockForwardOrIncludeHandler;

/**
 * Unit test for {@link MockPageContext}.
 * 
 * @author sangupta
 *
 * @since 2.0.0
 */
@SuppressWarnings("deprecation")
public class MockPageContextTest {

    @Test
    public void test() throws ServletException, IOException {
        MockPageContext pageContext = new MockPageContext();
        pageContext.setAttribute("hello", "world");
        Assert.assertEquals("world", pageContext.getAttribute("hello"));
        Assert.assertEquals("world", pageContext.findAttribute("hello"));
        Assert.assertNull(pageContext.findAttribute("404"));
        pageContext.removeAttribute("hello");
        Assert.assertNull(pageContext.getAttribute("hello"));

        pageContext.setAttribute("hellops", "page-scope", PageContext.PAGE_SCOPE);
        pageContext.setAttribute("hellors", "request-scope", PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("helloss", "session-scope", PageContext.SESSION_SCOPE);
        pageContext.setAttribute("helloas", "app-scope", PageContext.APPLICATION_SCOPE);

        Assert.assertEquals("page-scope", pageContext.getAttribute("hellops", PageContext.PAGE_SCOPE));
        Assert.assertEquals("request-scope", pageContext.getAttribute("hellors", PageContext.REQUEST_SCOPE));
        Assert.assertEquals("session-scope", pageContext.getAttribute("helloss", PageContext.SESSION_SCOPE));
        Assert.assertEquals("app-scope", pageContext.getAttribute("helloas", PageContext.APPLICATION_SCOPE));

        Assert.assertEquals(PageContext.PAGE_SCOPE, pageContext.getAttributesScope("hellops"));
        Assert.assertEquals(PageContext.REQUEST_SCOPE, pageContext.getAttributesScope("hellors"));
        Assert.assertEquals(PageContext.SESSION_SCOPE, pageContext.getAttributesScope("helloss"));
        Assert.assertEquals(PageContext.APPLICATION_SCOPE, pageContext.getAttributesScope("helloas"));
        Assert.assertEquals(0, pageContext.getAttributesScope("notfound"));

        Assert.assertEquals("page-scope", pageContext.findAttribute("hellops"));
        Assert.assertEquals("request-scope", pageContext.findAttribute("hellors"));
        Assert.assertEquals("session-scope", pageContext.findAttribute("helloss"));
        Assert.assertEquals("app-scope", pageContext.findAttribute("helloas"));

        Assert.assertTrue(pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE).hasMoreElements());
        Assert.assertTrue(pageContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE).hasMoreElements());
        Assert.assertTrue(pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE).hasMoreElements());
        Assert.assertTrue(pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE).hasMoreElements());

        pageContext.removeAttribute("hellops", PageContext.PAGE_SCOPE);
        pageContext.removeAttribute("hellors", PageContext.REQUEST_SCOPE);
        pageContext.removeAttribute("helloss", PageContext.SESSION_SCOPE);
        pageContext.removeAttribute("helloas", PageContext.APPLICATION_SCOPE);

        Assert.assertNull(pageContext.getAttribute("hellops", PageContext.PAGE_SCOPE));
        Assert.assertNull(pageContext.getAttribute("hellors", PageContext.REQUEST_SCOPE));
        Assert.assertNull(pageContext.getAttribute("helloss", PageContext.SESSION_SCOPE));
        Assert.assertNull(pageContext.getAttribute("helloas", PageContext.APPLICATION_SCOPE));

        Assert.assertFalse(pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE).hasMoreElements());
        Assert.assertFalse(pageContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE).hasMoreElements());
        Assert.assertFalse(pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE).hasMoreElements());
        Assert.assertFalse(pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE).hasMoreElements());

        ELContext ec = Mockito.mock(ELContext.class);
        pageContext.setElContext(ec);
        Assert.assertEquals(ec, pageContext.getELContext());

        MockExceptionHandler meh = Mockito.mock(MockExceptionHandler.class);
        pageContext.setExceptionHandler(meh);
        Assert.assertEquals(meh, pageContext.exceptionHandler);

        ExpressionEvaluator ee = Mockito.mock(ExpressionEvaluator.class);
        pageContext.setExpressionEvaluator(ee);
        Assert.assertEquals(ee, pageContext.getExpressionEvaluator());

        MockForwardOrIncludeHandler h = Mockito.mock(MockForwardOrIncludeHandler.class);
        pageContext.setForwardOrIncludeHandler(h);
        Assert.assertEquals(h, pageContext.forwardOrIncludeHandler);

        VariableResolver vr = Mockito.mock(VariableResolver.class);
        pageContext.setVariableResolver(vr);
        Assert.assertEquals(vr, pageContext.getVariableResolver());

        MockJspWriter writer = new MockJspWriter();
        pageContext.setJspWriter(writer);
        Assert.assertEquals(writer, pageContext.getOut());

        Object o = new Object();
        pageContext.setPage(o);
        Assert.assertEquals(o, pageContext.getPage());

        MockServletRequest sr = new MockHttpServletRequest();
        pageContext.setRequest(sr);
        Assert.assertEquals(sr, pageContext.getRequest());

        MockServletResponse rs = new MockServletResponse();
        pageContext.setResponse(rs);
        Assert.assertEquals(rs, pageContext.getResponse());

        ServletContext sc = Mockito.mock(ServletContext.class);
        pageContext.setServletContext(sc);
        Assert.assertEquals(sc, pageContext.getServletContext());

        ServletConfig sg = Mockito.mock(ServletConfig.class);
        pageContext.setServletConfig(sg);
        Assert.assertEquals(sg, pageContext.getServletConfig());

        Assert.assertNull(pageContext.getException());
        Assert.assertNotNull(pageContext.getSession());
        pageContext.setSession(null);
        Assert.assertNull(pageContext.getSession());

        pageContext.forward(null);
        pageContext.include(null);
        pageContext.include(null, true);

        pageContext.handlePageException(null);
        pageContext.handlePageException((Throwable) null);
    }

    @Test
    public void testInit() {
        MockPageContext pageContext = new MockPageContext();

        Servlet servlet = Mockito.mock(Servlet.class);
        ServletConfig sg = Mockito.mock(ServletConfig.class);
        ServletContext sc = Mockito.mock(ServletContext.class);

        Mockito.when(servlet.getServletConfig()).thenReturn(sg);
        Mockito.when(sg.getServletContext()).thenReturn(sc);

        pageContext.initialize(servlet, null, null, null, true, 128, true);
        pageContext.release();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAttribute() {
        MockPageContext pageContext = new MockPageContext();
        pageContext.removeAttribute("hello", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetAttribute() {
        MockPageContext pageContext = new MockPageContext();
        pageContext.setAttribute("hello", "world", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttribute() {
        MockPageContext pageContext = new MockPageContext();
        pageContext.getAttribute("hello", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttributeNamesInScope() {
        MockPageContext pageContext = new MockPageContext();
        pageContext.getAttributeNamesInScope(0);
    }
}
