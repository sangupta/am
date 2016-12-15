package com.sangupta.am.servlet2;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;

/**
 * Implementation of the {@link JspWriter} for unit-testing that keeps all
 * params within memory and provides useful accessor methods to modify the
 * values.
 * 
 * Meant to be used only for unit-testing.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmJspWriter extends JspWriter {
	
	protected final StringWriter stringWriter = new StringWriter();
	
	protected final PrintWriter writer = new PrintWriter(this.stringWriter);

	public AmJspWriter() {
		super(Integer.MAX_VALUE, true);
	}

	/**
	 * @return the writer
	 */
	public StringWriter getStringWriter() {
		return this.stringWriter;
	}

	/**
	 * @return the writer
	 */
	public PrintWriter getWriter() {
		return writer;
	}
	
	@Override
	public String toString() {
		return this.stringWriter.toString();
	}
	
	// Overridden methods follow
	
	@Override
	public void clear() throws IOException {
		throw new IOException("already flushed");
	}

	@Override
	public void clearBuffer() throws IOException {
		// do nothing
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

	@Override
	public void flush() throws IOException {
		this.writer.flush();
	}

	@Override
	public int getRemaining() {
		return 0;
	}

	@Override
	public void newLine() throws IOException {
		this.writer.print('\n');
	}

	@Override
	public void print(boolean arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(char arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(int arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(long arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(float arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(double arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(char[] arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(String arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void print(Object arg0) throws IOException {
		this.writer.print(arg0);
	}

	@Override
	public void println() throws IOException {
		this.writer.println();
	}

	@Override
	public void println(boolean arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(char arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(int arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(long arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(float arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(double arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(char[] arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(String arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void println(Object arg0) throws IOException {
		this.writer.println(arg0);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		this.writer.write(cbuf, off, len);
	}

}
