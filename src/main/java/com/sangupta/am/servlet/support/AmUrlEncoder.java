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

package com.sangupta.am.servlet.support;

import javax.servlet.http.HttpServletResponse;

import com.sangupta.am.servlet.AmHttpServletResponse;

/**
 * A simple URL encoder that is used by {@link AmHttpServletResponse}
 * to encode URLs via the encode methods in the {@link HttpServletResponse}.
 * 
 * This class can be overridden and a new implementation provided to the
 * {@link AmHttpServletResponse} class so that you can easily test things.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmUrlEncoder {

	public String encodeURL(String url) {
		return url;
	}

	public String encodeRedirectURL(String url) {
		return url;
	}

	public String encodeUrl(String url) {
		return url;
	}

	public String encodeRedirectUrl(String url) {
		return url;
	}
	
}
