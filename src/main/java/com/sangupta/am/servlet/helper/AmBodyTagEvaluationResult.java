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

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Value object that represents the result of invoking a {@link BodyTagSupport} based
 * JSP custom tag library, and thus aids in unit testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmBodyTagEvaluationResult {

	public int startTagResult = -1;
	
	public int afterBodyResult = -1;

	public int endTagResult = -1;

}
