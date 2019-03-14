package ua.nure.samoylenko.web.tag;

import ua.nure.samoylenko.dto.TestDTO;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class BeanTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        PageContext context = (PageContext) getJspContext();
        TestDTO testDTO = (TestDTO) context.getAttribute("test");
        out.println("<div class=\"test-name\"><strong>" + testDTO.getTestName() + "</strong></div>");
        out.println("<div class=\"test-subject asd\">Subject: <strong>" + testDTO.getSubjectName() + "</strong></div>");
        out.println("<div class=\"number-of-questions asd\">Number of questions: <strong>" + testDTO.getNumberOfQuestions() + "</strong></div>");
        out.println("<div class=\"complexity asd\">Complexity: <strong>" + testDTO.getComplexityName() + "</strong></div>");
        out.println("<div class=\"time-for-test asd\">Time for test: <strong>" + testDTO.getTime() + " minutes</strong></div>");
        out.println("<div class=\"btn-pass\"><a href=\"/EnterToTest?testId=" + testDTO.getId() + "\">Pass</a></div>");


    }
}
