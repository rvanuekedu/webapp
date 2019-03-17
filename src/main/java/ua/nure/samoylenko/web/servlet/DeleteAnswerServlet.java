package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.samoylenko.web.service.AnswerService;
import ua.nure.samoylenko.web.service.ServicesContainer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAnswer")
public class DeleteAnswerServlet extends HttpServlet {
    private AnswerService answerService;
    private static Logger LOGGER = Logger.getLogger(DeleteAnswerServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet DeleteAnswer start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        answerService = servicesContainer.getAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do get in servlet DeleteAnswer start");
        int answerId = Integer.parseInt(httpServletRequest.getParameter("answerId"));
        answerService.deleteAnswerById(answerId);
        String testId = httpServletRequest.getParameter("testId");
        LOGGER.debug("Trying to send redirect back to EnterToTest");
        httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);

    }
}
