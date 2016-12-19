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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * A {@link ServletOutputStream} implementation that writes everything to an
 * underlying {@link java.io.ByteArrayOutputStream}. This allows us to see
 * and manipulate what has been written by the servlet.
 * 
 * @author sangupta
 *
 */
public class ByteArrayServletOutputStream extends ServletOutputStream {
	
	/**
	 * The byte-stream that will store the data that is being written to
	 */
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	/**
	 * A {@link PrintWriter} object over the embedded byte-stream
	 */
	private final PrintWriter writer = new PrintWriter(this.outputStream);
	
	/**
	 * Check if the writer has been obtained from this stream
	 */
	private volatile boolean writerObtained = false;
	
	/**
	 * Return the writer object associated with this.
	 * 
	 * @return the {@link PrintWriter} associated
	 */
	public PrintWriter getWriter() {
		this.writerObtained = true;
		return this.writer;
	}
	
	/**
	 * Return the current size of the data that has been written.
	 * 
	 * @return the length of the written content
	 */
	public int getLength() {
		if (this.writerObtained) {
			this.writer.flush();
		}
		 
		return this.outputStream.size();
	}
	
	/**
	 * Get the entire byte-stream.
	 * 
	 * @return the internal {@link ByteArrayOutputStream} associated with this
	 */
	public ByteArrayOutputStream getByteArrayOutputStream() {
		return this.outputStream;
	}
	
	/**
	 * Get the raw bytes written to this stream.
	 * 
	 * @return the bytes written from the internal {@link ByteArrayOutputStream}
	 */
	public byte[] getBytes() {
		if (this.writerObtained) {
			this.writer.flush();
		}

		return this.outputStream.toByteArray();
	}
	
	//-----------------------------------------
	// Overridden methods follow
	//-----------------------------------------

	/**
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write(int value) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }
        this.outputStream.write(value);
    }

    /**
     * @see java.io.OutputStream#write(byte[])
     */
    @Override
    public void write(byte[] b) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }
        this.outputStream.write(b);
    }

    /**
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }
        this.outputStream.write(b, off, len);
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(boolean)
     */
    @Override
    public void print(boolean b) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(b);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(char)
     */
    @Override
    public void print(char c) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(c);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(double)
     */
    @Override
    public void print(double d) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(d);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(float)
     */
    @Override
    public void print(float f) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(f);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(int)
     */
    @Override
    public void print(int i) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(i);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(long)
     */
    @Override
    public void print(long l) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(l);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#print(java.lang.String)
     */
    @Override
    public void print(String s) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.print(s);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println()
     */
    @Override
    public void println() throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println();
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(boolean)
     */
    @Override
    public void println(boolean b) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(b);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(char)
     */
    @Override
    public void println(char c) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(c);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(double)
     */
    @Override
    public void println(double d) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(d);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(float)
     */
    @Override
    public void println(float f) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(f);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(int)
     */
    @Override
    public void println(int i) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(i);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(long)
     */
    @Override
    public void println(long l) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(l);
        this.writer.flush();
    }

    /**
     * @see javax.servlet.ServletOutputStream#println(java.lang.String)
     */
    @Override
    public void println(String s) throws IOException {
        if(this.writerObtained) {
            this.writer.flush();
        }

        this.writer.println(s);
        this.writer.flush();
    }

    /**
     * @see java.io.OutputStream#flush()
     */
    @Override
    public void flush() throws IOException {
        this.writer.flush();
        this.outputStream.flush();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.outputStream.hashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj == null) {
    		return false;
    	}
    	
    	if(this == obj) {
    		return true;
    	}
    	
    	if(!(obj instanceof ByteArrayServletOutputStream)) {
    		return false;
    	}
    	
        return this.outputStream.equals(obj);
    }
}
