/**
 * am: Assert-Mocks for unit-testing Java servlet API code
 * Copyright (c) 2016, Sandeep Gupta
 * 
 * https://sangupta.com/projects/am
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sangupta.am.servlet.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Assert;

import com.sangupta.am.servlet.AmFilterChain;

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
	 * @param <T>
	 *            the Class type which extends the {@link Filter}
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
	
	/**
	 * Execute the filter via the
	 * {@link Filter#doFilter(ServletRequest, ServletResponse, javax.servlet.FilterChain)}
	 * method and checks that the given exception is thrown as part of
	 * execution. If no exception is thrown, or a different exception is thrown
	 * then the unit test is considered to fail.
	 * 
	 * @param filter
	 *            the {@link Filter} to execute
	 * 
	 * @param throwable
	 *            the {@link Exception} instance that we want to test for
	 * 
	 * @param servletRequest
	 *            the {@link ServletRequest} as input
	 * 
	 * @param servletResponse
	 *            the {@link ServletResponse} as output
	 */
	public static void assertFilterException(Filter filter, Throwable throwable, ServletRequest servletRequest, ServletResponse servletResponse) {
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
		} catch (Throwable t) {
			if(throwable.getClass().isAssignableFrom(t.getClass())) {
				Assert.assertTrue(true);
				return;
			}
		}
		
		Assert.fail();
	}
	
}
