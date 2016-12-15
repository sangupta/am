package com.sangupta.am.servlet;

import java.security.Principal;

/**
 * Implementation of the {@link Principal} that stores the name of the user in
 * memory and provides {@link #setName(String)} method to modify it.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */

public class AmPrincipal implements Principal {
	
	protected String name;
	
	public AmPrincipal() {
		
	}
	
	public AmPrincipal(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	// Overridden methods follow
	
	@Override
	public String getName() {
		return this.name;
	}

}
