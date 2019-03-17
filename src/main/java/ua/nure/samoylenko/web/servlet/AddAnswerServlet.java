package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.AnswerDTO;
import ua.nure.samoylenko.web.service.AnswerService;
import ua.nure.samoylenko.web.service.ServicesContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddAnswer")
public class AddAnswerServlet extends HttpServlet {
    private AnswerService answerService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        answerService = servicesContainer.getAnswerService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        if (httpServletRequest.getParameter("questionId") != null && httpServletRequest.getParameter("testId") != null) {
            Integer questionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));

            if (!"".equals(httpServletRequest.getParameter("newAnswer"))) {
                AnswerDTO answerDTO = new AnswerDTO();
                String answerText = httpServletRequest.getParameter("newAnswer");
                boolean isCorrect = Boolean.parseBoolean(httpServletRequest.getParameter("isCorrect"));
                answerDTO.setaText(answerText);
                answerDTO.setCorrect(isCorrect);
                answerDTO.setQuestionId(questionId);
                answerService.createAnswerForQuestionByQuestionId(answerDTO);
            }

            httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendRedirect("Enter");
    }
}
