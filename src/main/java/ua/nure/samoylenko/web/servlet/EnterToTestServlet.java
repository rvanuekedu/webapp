package ua.nure.samoylenko.web.servlet;

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

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
        answerService = servicesContainer.getAnswerService();
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if ((httpServletRequest.getParameter("testId") != null) && !httpServletRequest.getParameter("testId").equals("")) {
            List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            if (passedTest.contains(testId)) {
                httpServletResponse.sendRedirect("Enter");
            } else {
                List<Question> questions;
                List<Answer> answers;
                Integer testTime;

                questions = questionService.getQuestionsByTestId(testId);

                answers = answerService.getAllAnswersByTestId(testId);

                testTime = testService.getTestTimeByTestId(testId);

                httpServletRequest.setAttribute("questions", questions);
                httpServletRequest.setAttribute("answers", answers);
                httpServletRequest.setAttribute("testTime", testTime);
                httpServletRequest.setAttribute("testId", testId);

                httpServletRequest.getRequestDispatcher("/WEB-INF/TestPage.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else {
            httpServletResponse.sendRedirect("Enter");
        }
    }


}
