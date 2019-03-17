package ua.nure.samoylenko.web.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.samoylenko.entities.Answer;
import ua.nure.samoylenko.entities.Question;
import ua.nure.samoylenko.web.service.AnswerService;
import ua.nure.samoylenko.web.service.QuestionService;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EnterToTest")
public class EnterToTestServlet extends HttpServlet {
    private QuestionService questionService;
    private AnswerService answerService;
    private TestService testService;
    private static Logger LOGGER = Logger.getLogger(EnterToTestServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet EnterToTest start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
        answerService = servicesContainer.getAnswerService();
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in servlet EnterToTest start");
        LOGGER.debug("Check parameter testId is valid");
        if ((httpServletRequest.getParameter("testId") != null) && !httpServletRequest.getParameter("testId").equals(StringUtils.EMPTY)) {
            List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            if (passedTest.contains(testId)) {
                httpServletResponse.sendRedirect("Enter");
            } else {
                List<Question> questions;
                List<Answer> answers;
                Integer testTime;
                LOGGER.debug("Obtain question by services");
                questions = questionService.getQuestionsByTestId(testId);
                LOGGER.debug("Obtain answer by services");
                answers = answerService.getAllAnswersByTestId(testId);
                LOGGER.debug("Obtain testTime by services");
                testTime = testService.getTestTimeByTestId(testId);
                httpServletRequest.setAttribute("questions", questions);
                httpServletRequest.setAttribute("answers", answers);
                httpServletRequest.setAttribute("testTime", testTime);
                httpServletRequest.setAttribute("testId", testId);
                LOGGER.debug("Trying forward to /WEB-INF/TestPage.jsp");
                httpServletRequest.getRequestDispatcher("/WEB-INF/TestPage.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else {
            LOGGER.debug("Parameter testId is not valid");
            LOGGER.debug("Trying to send redirect in Enter");
            httpServletResponse.sendRedirect("Enter");
        }
    }


}
