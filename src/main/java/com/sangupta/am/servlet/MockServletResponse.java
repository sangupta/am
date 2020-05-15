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
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import com.sangupta.am.servlet.support.ByteArrayServletOutputStream;

/**
 * Implementation of the {@link ServletResponse} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * 
 * @since 1.0.0
 */
public class MockServletResponse implements ServletResponse {
	
	public String characterEncoding;
	
	public String contentType;
	
	public int contentLength;
	
	public int bufferSize;
	
	public boolean committed;
	
	public Locale locale;
	
	public final ByteArrayServletOutputStream stream = new ByteArrayServletOutputStream();
	
	// Overridden methods follow

	@Override
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return this.stream;
	}

	@Override
	public PrintWriter getWriter() {
		return this.stream.getWriter();
	}

	@Override
	public void setCharacterEncoding(String charset) {
		this.characterEncoding = charset;
	}

	@Override
	public void setContentLength(int len) {
		this.contentLength = len;
	}

	@Override
	public void setContentType(String type) {
		this.contentType = type;
	}

	@Override
	public void setBufferSize(int size) {
		this.bufferSize = size;
	}

	@Override
	public int getBufferSize() {
		return this.bufferSize;
	}

	@Override
	public void flushBuffer() throws IOException {
		this.stream.flush();
	}

	@Override
	public void resetBuffer() {
		// nothing to do
	}

	@Override
	public boolean isCommitted() {
		return this.committed;
	}

	@Override
	public void reset() {
		// nothing to do
	}

	@Override
	public void setLocale(Locale loc) {
		this.locale = loc;
	}

	@Override
	public Locale getLocale() {
		return this.locale;
	}

}
