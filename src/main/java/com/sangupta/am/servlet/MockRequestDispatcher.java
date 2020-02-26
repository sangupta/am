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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 *
 */
public class MockRequestDispatcher implements RequestDispatcher {
	
	protected String path;
	
	public MockRequestDispatcher() {

	}
	
	public MockRequestDispatcher(String path) {
		this.path = path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	// Overridden methods follow

	@Override
	public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}

}
