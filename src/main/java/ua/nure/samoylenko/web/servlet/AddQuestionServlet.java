package ua.nure.samoylenko.web.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
    private static Logger LOGGER = Logger.getLogger(AddQuestionServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet AddQuestion start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        LOGGER.debug("Do post in servlet AddNewTopic start");
        LOGGER.debug("Check parameter testId is valid");
        if (httpServletRequest.getParameter("testId") != null) {
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            LOGGER.debug("Check parameter newQuestion is valid");
            if (!StringUtils.EMPTY.equals(httpServletRequest.getParameter("newQuestion"))) {
                QuestionDTO questionDTO = new QuestionDTO();
                String questionText = httpServletRequest.getParameter("newQuestion");
                questionDTO.setqText(questionText);
                questionDTO.setTestId(testId);
                questionService.createQuestion(questionDTO);
            }
            LOGGER.debug("Trying send redirect back");
            httpServletResponse.sendRedirect("EnterToTest?testId=" + testId);
        }
    }
}
