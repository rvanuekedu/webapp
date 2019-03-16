package ua.nure.samoylenko.web.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class BeanTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        PageContext context = (PageContext) getJspContext();
        ServletRequest request = context.getRequest();
        Integer testTime = (Integer) request.getAttribute("testTime");

        out.println("<div class=\"timer\" id=\"timer\">");
        out.println("<div id=\"minute\">" + testTime + "</div>&nbsp;:");
        out.println("<div id=\"second\">00</div>");
        out.println("</div>");

    }
}
