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

import java.security.Principal;

/**
 * Implementation of the {@link Principal} that stores the name of the user in
 * memory and provides {@link #setName(String)} method to modify it.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */

public class MockPrincipal implements Principal {
	
	protected String name;
	
	public MockPrincipal() {
		
	}
	
	public MockPrincipal(String name) {
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
