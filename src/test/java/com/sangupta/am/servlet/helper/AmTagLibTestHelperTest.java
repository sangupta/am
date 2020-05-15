package com.sangupta.am.servlet.helper;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.MockPageContext;
import com.sangupta.jerry.consume.GenericConsumer;

/**
 * Unit tests for {@link AmTagLibTestHelper}
 * 
 * @author sangupta
 * 
 * @since 2.0.0
 *
 */
public class AmTagLibTestHelperTest {
    
    @Test
    public void testFailures() {
        Assert.assertNull(AmTagLibTestHelper.getJspContext(null));
        Assert.assertNull(AmTagLibTestHelper.getJspWriter(null));
        Assert.assertNull(AmTagLibTestHelper.getPageContext(null));
        Assert.assertNull(AmTagLibTestHelper.getJspWriterString(null));
        Assert.assertNull(AmTagLibTestHelper.getSimpleTagForUnitTesting(null));
        Assert.assertNull(AmTagLibTestHelper.getBodyTagForUnitTesting(null));
        AmTagLibTestHelper.doTag((SimpleTag) null);
        Assert.assertNull(AmTagLibTestHelper.doTag((BodyTag) null));
    }

    @Test
    public void testSimpleTag() {
        GenericConsumer<AnchorLinkSimpleTag> consumer = new GenericConsumer<AnchorLinkSimpleTag>() {

            @Override
            public boolean consume(AnchorLinkSimpleTag data) {
                data.link = "heading";
                return true;
            }
        };

        AmTagLibTestHelper.testTagOutput(AnchorLinkSimpleTag.class, "<sup><a href=\"#heading\">heading</a></sup>", consumer);
    }
    
    @Test
    public void testBodyTag() {
        AnchorLinkTag tag = AmTagLibTestHelper.getBodyTagForUnitTesting(AnchorLinkTag.class);
        tag.link = "hello";
        AmBodyTagEvaluationResult result = AmTagLibTestHelper.doTag(tag);
        Assert.assertEquals(-1, result.afterBodyResult);
        Assert.assertEquals(6, result.endTagResult);
        Assert.assertEquals(0, result.startTagResult);
        MockPageContext context = (MockPageContext) AmTagLibTestHelper.getPageContext(tag);
        Assert.assertEquals("<sup><a href=\"#hello\">hello</a></sup>", context.getOut().toString());
    }

    public static class AnchorLinkSimpleTag extends SimpleTagSupport {

        public String link;

        public void doTag() throws JspException {
            // create the output HTMl
            StringBuffer buffer = new StringBuffer();
            buffer.append("<sup><a href=\"#");
            buffer.append(this.link);
            buffer.append("\">");
            buffer.append(this.link);
            buffer.append("</a></sup>");

            // output the HTML
            try {
                JspWriter out = this.getJspContext().getOut();
                out.write(buffer.toString());
            } catch (IOException e) {
                throw new JspException(e);
            }
        }

    }
    
    public static class AnchorLinkTag extends BodyTagSupport {
        
        /**
         * Generated via Eclipse
         */
        private static final long serialVersionUID = 735237068973428524L;
        
        private String link;
        
        public int doStartTag() throws JspException {
            // create the output HTMl
            StringBuffer buffer = new StringBuffer();
            buffer.append("<sup><a href=\"#");
            buffer.append(this.link);
            buffer.append("\">");
            buffer.append(this.link);
            buffer.append("</a></sup>");
            
            // output the HTML
            try {
                JspWriter out = pageContext.getOut();
                out.write(buffer.toString());
            } catch(IOException e) {
                throw new JspException(e);
            }

            return SKIP_BODY;
        }

        public int doEndTag() throws JspException {
            return EVAL_PAGE;
        }
    }
}
