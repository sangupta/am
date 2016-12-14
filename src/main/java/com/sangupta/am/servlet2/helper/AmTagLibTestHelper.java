package com.sangupta.am.servlet2.helper;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sangupta.am.AmUtils;
import com.sangupta.am.servlet2.AmJspContext;
import com.sangupta.am.servlet2.AmJspWriter;

public class AmTagLibTestHelper {
	
	/**
	 * Return the {@link JspContext} associated with the tag.
	 * 
	 * @param tag
	 * @return
	 */
	public static JspContext getJspContext(SimpleTagSupport tag) {
		if(tag == null) {
			return null;
		}
		
		return AmUtils.getFieldForName(tag, "jspContext", JspContext.class);
	}
	
	/**
	 * Return the {@link JspWriter} from the {@link JspContext} associated with the tag.
	 * 
	 * @param tag
	 * @return
	 */
	public static JspWriter getJspWriter(SimpleTagSupport tag) {
		if(tag == null) {
			return null;
		}
		
		JspContext context = getJspContext(tag);
		if(context == null) {
			return null;
		}
		
		return context.getOut();
	}
	
	public static String getJspWriterString(SimpleTagSupport tag) {
		JspWriter writer = getJspWriter(tag);
		if(writer == null) {
			return null;
		}
		
		return writer.toString();
	}

	/**
	 * Create an instance of the tag, and supply dummy implementations to {@link JspContext}
	 * and {@link JspWriter} to the same, so that tag can be easily unit-tested.
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends SimpleTagSupport> T getTagForUnitTesting(Class<T> clazz) {
		if(clazz == null) {
			return null;
		}
		
		try {
			T tag = clazz.newInstance();
			
			AmJspContext context = new AmJspContext();
			AmJspWriter writer = new AmJspWriter();
			
			context.setJspWriter(writer);
			tag.setJspContext(context);
			
			return tag;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Execute the tag safely converting any exception into a {@link RuntimeException}.
	 * 
	 * @param tag
	 */
	public static void doTag(SimpleTagSupport tag) {
		if(tag == null) {
			return;
		}
		
		try {
			tag.doTag();
		} catch (JspException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
