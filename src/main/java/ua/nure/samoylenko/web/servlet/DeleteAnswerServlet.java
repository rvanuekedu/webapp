package ua.nure.samoylenko.web.servlet;

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

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        answerService = servicesContainer.getAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Integer answerId = Integer.parseInt(httpServletRequest.getParameter("answerId"));
        answerService.deleteAnswerById(answerId);
        String testId = httpServletRequest.getParameter("testId");

        httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);

    }
}
