package ua.nure.samoylenko.web.servlet;

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

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Integer questionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        questionService.deleteQuestion(questionId);
        String testId = httpServletRequest.getParameter("testId");

        httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);

    }
}
