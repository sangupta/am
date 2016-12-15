package com.sangupta.am.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Implementation to {@link FilterChain} that just keeps track of whether the
 * {@link #doFilter(ServletRequest, ServletResponse)} method was called or not.
 * This is then exposed as {@link #isChainInvoked()} method.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmFilterChain implements FilterChain {
	
	private volatile boolean chainInvoked = false;
	
	public boolean isChainInvoked() {
		return this.chainInvoked;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		this.chainInvoked = true;
	}

}
