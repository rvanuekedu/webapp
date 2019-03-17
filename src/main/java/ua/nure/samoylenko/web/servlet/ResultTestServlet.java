package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.entities.Question;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.web.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/ResultTest")
public class ResultTestServlet extends HttpServlet {
    private QuestionService questionService;
    private AnswerService answerService;
    private ResultService resultService;
    private StudentService studentService;
    private static Logger LOGGER = Logger.getLogger(ResultTestServlet.class);


    @Override
    public void init() {
        LOGGER.debug("Init servlet ResultTest start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        questionService = servicesContainer.getQuestionService();
        answerService = servicesContainer.getAnswerService();
        resultService = servicesContainer.getResultService();
        studentService = servicesContainer.getStudentService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do post in servlet ResultTest start");
        httpServletRequest.setCharacterEncoding("utf-8");
        List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");
        Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));

        if (!passedTest.contains(testId)) {
            List<Question> questions;
            List<String> answers;
            String[] checkbox;
            int result;
            float numberOfTrueAnswers = 0;

            LOGGER.debug("Get current date");
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(dt);

            LOGGER.debug("Obtain questions by services");
            questions = questionService.getQuestionsByTestId(testId);

            LOGGER.debug("Process the result for test");
            for (Question question : questions) {
                LOGGER.debug("Obtain answers by services");
                answers = answerService.getTrueAnswersByQuestionId(question.getQuestionId());
                checkbox = httpServletRequest.getParameterValues(String.valueOf(question.getQuestionId()));
                if (checkbox != null) {
                    String[] answersArray = answers.toArray(new String[0]);
                    Arrays.sort(answersArray);
                    Arrays.sort(checkbox);
                    if (Arrays.equals(answersArray, checkbox)) {
                        numberOfTrueAnswers++;
                    }
                }
            }

            result = Math.round(numberOfTrueAnswers / questions.size() * 100);
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            LOGGER.debug("Obtain studentId by services");
            Integer studentId = studentService.getStudentIdByEmail(user.getEmail());
            ResultDTO resultDTO = new ResultDTO();

            resultDTO.setStudentId(studentId);
            resultDTO.setDateToDB(date);
            resultDTO.setTestId(testId);
            resultDTO.setValueOfResult(result);
            LOGGER.debug("Push the result to data base");
            resultService.createResult(resultDTO);
            passedTest.add(testId);
            LOGGER.debug("Trying to send redirect in ShowResult");
            httpServletResponse.sendRedirect("ShowResult");
        } else {
            LOGGER.debug("Trying to send redirect in Enter");
            httpServletResponse.sendRedirect("Enter");
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in  servlet ResultTest start");
        httpServletResponse.sendRedirect("Enter");
    }
}
