package com.sangupta.am.servlet.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.MockServletRequest;
import com.sangupta.am.servlet.MockServletResponse;

/**
 * Unit tests for {@link AmServletFilterTestHelper}
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 *
 */
public class AmServletFilterTestHelperTest {

    @Test
    public void testChainInvoked() {
        TestFilter filter = new TestFilter();
        AmServletFilterTestHelper.assertFilterChainInvoked(filter, new MockServletRequest(), new MockServletResponse());
    }

    @Test
    public void testChainNotInvoked() {
        TestNothingFilter filter = new TestNothingFilter();
        AmServletFilterTestHelper.assertFilterChainNotInvoked(filter, new MockServletRequest(), new MockServletResponse());
    }

    @Test
    public void testException() {
        TestExceptionFilter filter = new TestExceptionFilter();
        AmServletFilterTestHelper.assertFilterException(filter, IllegalArgumentException.class, new MockServletRequest(), new MockServletResponse());
    }

    @Test
    public void testFailures() {
        try {
            AmServletFilterTestHelper.assertFilterChainInvoked(null, null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterChainInvoked(new TestFilter(), null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterChainInvoked(new TestFilter(), new MockServletRequest(), null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterChainNotInvoked(null, null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterChainNotInvoked(new TestNothingFilter(), null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterChainNotInvoked(new TestNothingFilter(), new MockServletRequest(), null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterException(null, null, null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterException(new TestExceptionFilter(), null, null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterException(new TestExceptionFilter(), IllegalArgumentException.class, null, null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
        
        try {
            AmServletFilterTestHelper.assertFilterException(new TestExceptionFilter(), IllegalArgumentException.class, new MockServletRequest(), null);
            Assert.assertTrue(false);
        } catch (AssertionError e) {
            Assert.assertTrue(true);
        }
    }

    public static class TestFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }

    }

    public static class TestNothingFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        }

        @Override
        public void destroy() {
        }

    }

    public static class TestExceptionFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            throw new IllegalArgumentException();
        }

        @Override
        public void destroy() {
        }

    }
}
