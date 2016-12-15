package com.sangupta.am.servlet2;

import java.security.Principal;

public class AmPrincipal implements Principal {
	
	protected String name;
	
	public void setName(String name) {
		this.name = name;
	}

	// Overridden methods follow
	
	@Override
	public String getName() {
		return this.name;
	}

}
