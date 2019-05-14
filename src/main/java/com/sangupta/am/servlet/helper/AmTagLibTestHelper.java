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
import java.lang.reflect.InvocationTargetException;

import javax.servlet.Filter;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Assert;

import com.sangupta.am.servlet.AmHttpServletRequest;
import com.sangupta.am.servlet.AmHttpServletResponse;
import com.sangupta.am.servlet.AmJspWriter;
import com.sangupta.am.servlet.AmPageContext;
import com.sangupta.jerry.consume.GenericConsumer;
import com.sangupta.jerry.util.ReflectionUtils;

/**
 * Helper class that aids in unit testing of JSP custom tag libraries
 * by providing convenience methods and wiring default objects.
 *  
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class AmTagLibTestHelper {
	
	/**
	 * Return the {@link JspContext} associated with the tag.
	 * 
	 * @param tag
	 *            the {@link SimpleTag} instance
	 * 
	 * @return {@link JspContext} associated if any, or <code>null</code>
	 *         otherwise
	 */
	public static JspContext getJspContext(SimpleTag tag) {
		if(tag == null) {
			return null;
		}
		
		return ReflectionUtils.getFieldForName(tag, "jspContext", JspContext.class);
	}
	
	/**
	 * Return the {@link JspWriter} from the {@link JspContext} associated with
	 * the tag.
	 * 
	 * @param tag
	 *            the {@link SimpleTag} instance
	 * 
	 * @return {@link JspWriter} associated if any, or <code>null</code>
	 *         otherwise
	 */
	public static JspWriter getJspWriter(SimpleTag tag) {
		if(tag == null) {
			return null;
		}
		
		JspContext context = getJspContext(tag);
		if(context == null) {
			return null;
		}
		
		return context.getOut();
	}
	
	/**
	 * Get the {@link String} value of response written to the internal
	 * {@link JspWriter} associated with a {@link SimpleTag}.
	 * 
	 * @param tag
	 *            the {@link SimpleTag} instance
	 * 
	 * @return the {@link String} written to writer, or <code>null</code> if no
	 *         {@link JspWriter} is available
	 */
	public static String getJspWriterString(SimpleTag tag) {
		JspWriter writer = getJspWriter(tag);
		if(writer == null) {
			return null;
		}
		
		return writer.toString();
	}
	
	/**
	 * Create an instance of the {@link SimpleTag}, and supply dummy
	 * implementations to {@link JspContext} and {@link JspWriter} to the same,
	 * so that tag can be easily unit-tested.
	 * 
	 * @param <T>
	 *            the Class type which extends the {@link SimpleTag}
	 * 
	 * @param clazz
	 *            the class implementing {@link SimpleTag}
	 * 
	 * @return the tag instance created and wired, <code>null</code> if
	 *         something fails
	 * 
	 * @throws RuntimeException
	 *             if tag cannot be instantiated or an
	 *             {@link IllegalAccessException} is thrown
	 */
	public static <T extends SimpleTag> T getSimpleTagForUnitTesting(Class<T> clazz) {
		if(clazz == null) {
			return null;
		}
		
		try {
			T tag = clazz.getDeclaredConstructor().newInstance();
			
			AmPageContext context = new AmPageContext();
			AmJspWriter writer = new AmJspWriter();
			AmHttpServletRequest request = new AmHttpServletRequest();
			AmHttpServletResponse response = new AmHttpServletResponse();

			// set page context
			context.setJspWriter(writer);
			context.setRequest(request);
			context.setResponse(response);
			
			// complete tag
			tag.setJspContext(context);

			return tag;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Create an instance of the {@link BodyTag}, and supply dummy
	 * implementations to {@link JspContext} and {@link JspWriter} to the same,
	 * so that tag can be easily unit-tested.
	 * 
	 * @param <T>
	 *            the Class type which extends the {@link BodyTag}
	 * 
	 * @param clazz
	 *            the class implementing {@link SimpleTag}
	 * 
	 * @return the tag instance created and wired, <code>null</code> if
	 *         something fails
	 * 
	 * @throws RuntimeException
	 *             if the tag cannot instantiated or an IllegalAccessException
	 *             is thrown during instantiation
	 */
	public static <T extends BodyTag> T getBodyTagForUnitTesting(Class<T> clazz) {
		if(clazz == null) {
			return null;
		}
		
		try {
			T tag = clazz.getDeclaredConstructor().newInstance();
			
			AmPageContext context = new AmPageContext();
			AmJspWriter writer = new AmJspWriter();
			AmHttpServletRequest request = new AmHttpServletRequest();
			AmHttpServletResponse response = new AmHttpServletResponse();

			// set page context
			context.setJspWriter(writer);
			context.setRequest(request);
			context.setResponse(response);
			
			// complete tag
			tag.setPageContext(context);

			return tag;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Execute the {@link SimpleTag} safely converting any exception into a
	 * {@link RuntimeException}. Does nothing if the instance provided is
	 * <code>null</code>.
	 * 
	 * @param tag
	 *            the {@link SimpleTag} to execute
	 * 
	 * @throws RuntimeException
	 *             if one of {@link JspException} or {@link IOException} is
	 *             thrown when executing the {@link SimpleTag#doTag()} method
	 */
	public static void doTag(SimpleTag tag) {
		if(tag == null) {
			return;
		}
		
		try {
			tag.doTag();
		} catch (JspException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Execute the {@link BodyTag} safely converting any exception into a
	 * {@link RuntimeException}. Does nothing if the instance provided is
	 * <code>null</code>.
	 * 
	 * @param tag
	 *            the {@link BodyTag} instance to execute
	 * 
	 * @return the {@link AmBodyTagEvaluationResult} generated as part of
	 *         {@link BodyTag} execution
	 */
	public static AmBodyTagEvaluationResult doTag(BodyTag tag) {
		if(tag == null) {
			return null;
		}
		
		AmBodyTagEvaluationResult result = new AmBodyTagEvaluationResult();
		try {
			boolean firstEvaluation = true;
			
			// start working on tag
			result.startTagResult = tag.doStartTag();
			if(result.startTagResult == Tag.SKIP_PAGE) {
				return result;
			}
			
			if(result.startTagResult == Tag.EVAL_BODY_INCLUDE) {
				do {
					if(firstEvaluation) {
						tag.doInitBody();
					}
					
					firstEvaluation = true;
					result.afterBodyResult = tag.doAfterBody();
					if(result.afterBodyResult == Tag.SKIP_BODY) {
						break;
					}
					
					if(result.afterBodyResult == Tag.SKIP_PAGE) {
						return result;
					}
				} while(true);
			}
			
			result.endTagResult = tag.doEndTag();
			
			// return the result
			return result;
		} catch(JspException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test the {@link String} output written to {@link JspWriter} as part of
	 * executing a {@link SimpleTag}.
	 * 
	 * @param <T>
	 *            the Class type which extends the {@link SimpleTag}
	 * 
	 * @param tagClass
	 *            the {@link Class} that implements the {@link Filter}
	 * 
	 * @param expectedOutput
	 *            the expected {@link String} output
	 * 
	 * @param consumer
	 *            the {@link GenericConsumer} that is called before executing
	 *            the tag instance so that any tag properties can be set by the
	 *            callee
	 */
	public static <T extends SimpleTag> void testTagOutput(Class<T> tagClass, String expectedOutput, GenericConsumer<T> consumer) {
		T tag = AmTagLibTestHelper.getSimpleTagForUnitTesting(tagClass);
		consumer.consume(tag);
		AmTagLibTestHelper.doTag(tag);
		String actualOutput = AmTagLibTestHelper.getJspWriter(tag).toString();
		
		Assert.assertEquals(expectedOutput, actualOutput);
	}
	
}
