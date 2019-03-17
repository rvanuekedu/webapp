package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.web.service.QuestionService;
import ua.nure.samoylenko.web.service.ServicesContainer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddQuestion")
public class AddQuestionServlet extends HttpServlet {
    private QuestionService questionService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        if (httpServletRequest.getParameter("testId") != null) {
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));

            if (!"".equals(httpServletRequest.getParameter("newQuestion"))) {
                QuestionDTO questionDTO = new QuestionDTO();
                String questionText = httpServletRequest.getParameter("newQuestion");
                questionDTO.setqText(questionText);
                questionDTO.setTestId(testId);
                questionService.createQuestion(questionDTO);
            }

            httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);
        }
    }
}
