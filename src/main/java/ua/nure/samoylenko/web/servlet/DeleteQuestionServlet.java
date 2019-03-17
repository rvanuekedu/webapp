package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.samoylenko.web.service.QuestionService;
import ua.nure.samoylenko.web.service.ServicesContainer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {
    private QuestionService questionService;
    private static Logger LOGGER = Logger.getLogger(DeleteQuestionServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet DeleteQuestion start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do get in servlet DeleteQuestion start");
        int questionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        questionService.deleteQuestion(questionId);
        String testId = httpServletRequest.getParameter("testId");
        LOGGER.debug("Trying to send redirect back to EnterToTest servlet");
        httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);

    }
}
