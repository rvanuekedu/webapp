package ua.nure.samoylenko.web.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
    private static Logger LOGGER = Logger.getLogger(AddAnswerServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        answerService = servicesContainer.getAnswerService();
        LOGGER.debug("Init servlet finish");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        LOGGER.debug("Do post in servlet AddAnswerServlet start");
        LOGGER.debug("Check parameter question is valid");
        if (httpServletRequest.getParameter("questionId") != null && httpServletRequest.getParameter("testId") != null) {
            Integer questionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
            int testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            LOGGER.debug("Check parameter newAnswer is valid");
            if (!StringUtils.EMPTY.equals(httpServletRequest.getParameter("newAnswer"))) {
                AnswerDTO answerDTO = new AnswerDTO();
                String answerText = httpServletRequest.getParameter("newAnswer");
                boolean isCorrect = Boolean.parseBoolean(httpServletRequest.getParameter("isCorrect"));
                answerDTO.setaText(answerText);
                answerDTO.setCorrect(isCorrect);
                answerDTO.setQuestionId(questionId);
                answerService.createAnswerForQuestionByQuestionId(answerDTO);
            }
            LOGGER.debug("Trying to send redirect back");
            httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in AddAnswerServlet start");
        httpServletResponse.sendRedirect("Enter");
    }
}
