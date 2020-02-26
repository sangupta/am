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

package com.sangupta.am.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Implementation to {@link FilterChain} that just keeps track of whether the
 * {@link #doFilter(ServletRequest, ServletResponse)} method was called or not.
 * This is then exposed as {@link #isChainInvoked()} method. In case tests want
 * to override the functionality of {@link #doFilter()} method, they should 
 * add it to {@link #doFilterChain()} method. The latter shall be called from
 * former after internal state for {@link #chainInvoked} is updated.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class MockFilterChain implements FilterChain {
	
	private volatile boolean chainInvoked = false;
	
	public boolean isChainInvoked() {
		return this.chainInvoked;
	}
	
	@Override
	public final void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		this.chainInvoked = true;
		
		this.doFilterChain(request, response);
	}
	
	public void doFilterChain(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		
	}

}
