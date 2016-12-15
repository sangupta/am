package com.sangupta.am.servlet2.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Assert;

import com.sangupta.am.servlet2.AmFilterChain;

/**
 * Helper class that aids in testing of {@link Filter}s.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmServletFilterTestHelper {

	/**
	 * Create a new instance of the {@link Filter} class that is geared up for
	 * unit testing.
	 * 
	 * @param clazz
	 *            the {@link Class} that implements the {@link Filter}
	 * 
	 * @return the instance thus created
	 * 
	 * @throws RuntimeException
	 *             if instance creation throws {@link InstantiationException} or
	 *             {@link IllegalAccessException}
	 */
	public static <T extends Filter> T getFilterForUnitTesting(Class<T> clazz) {
		if(clazz == null) {
			return null;
		}
		
		try {
			T instance = clazz.newInstance();
			
			return instance;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Execute the filter via the
	 * {@link Filter#doFilter(ServletRequest, ServletResponse, javax.servlet.FilterChain)}
	 * method and then assert that the {@link FilterChain} was invoked as part
	 * of the execution.
	 * 
	 * @param filter
	 *            the {@link Filter} to execute
	 * @param servletRequest
	 *            the {@link ServletRequest} as input
	 * @param servletResponse
	 *            the {@link ServletResponse} as output
	 * 
	 * @throws RuntimeException
	 *             if
	 *             {@link Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
	 *             fails with either an {@link IOException} or a
	 *             {@link ServletException}
	 */
	public static void assertFilterChainInvoked(Filter filter, ServletRequest servletRequest, ServletResponse servletResponse) {
		if(filter == null) {
			Assert.fail();
		}
		
		if(servletRequest == null) {
			Assert.fail();
		}
		
		if(servletResponse == null) {
			Assert.fail();
		}
		
		AmFilterChain chain = new AmFilterChain();
		try {
			filter.doFilter(servletRequest, servletResponse, chain);
		} catch (IOException | ServletException e) {
			Assert.fail();
		}
		
		Assert.assertTrue(chain.isChainInvoked());
	}

	/**
	 * Execute the filter via the
	 * {@link Filter#doFilter(ServletRequest, ServletResponse, javax.servlet.FilterChain)}
	 * method and then assert that the {@link FilterChain} was <b>NOT</b> invoked as part
	 * of the execution.
	 * 
	 * @param filter
	 *            the {@link Filter} to execute
	 * @param servletRequest
	 *            the {@link ServletRequest} as input
	 * @param servletResponse
	 *            the {@link ServletResponse} as output
	 * 
	 * @throws RuntimeException
	 *             if
	 *             {@link Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
	 *             fails with either an {@link IOException} or a
	 *             {@link ServletException}
	 */
	public static void assertFilterChainNotInvoked(Filter filter, ServletRequest servletRequest, ServletResponse servletResponse) {
		if(filter == null) {
			Assert.fail();
		}
		
		if(servletRequest == null) {
			Assert.fail();
		}
		
		if(servletResponse == null) {
			Assert.fail();
		}
		
		AmFilterChain chain = new AmFilterChain();
		try {
			filter.doFilter(servletRequest, servletResponse, chain);
		} catch (IOException | ServletException e) {
			Assert.fail();
		}
		
		Assert.assertFalse(chain.isChainInvoked());
	}
}
