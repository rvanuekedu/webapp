package ua.nure.samoylenko.web.listener;

import ua.nure.samoylenko.dao.*;
import ua.nure.samoylenko.dao.impl.*;
import ua.nure.samoylenko.web.service.*;
import ua.nure.samoylenko.web.service.impl.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServicesContainer servicesContainer = new ServicesContainer();

        UserDAO userDAO = new UserDAOImpl();
        UserService userService = new UserServiceImpl(userDAO);

        AnswerDAO answerDAO = new AnswerDAOImpl();
        AnswerService answerService = new AnswerServiceImpl(answerDAO);

        QuestionDAO questionDAO = new QuestionDAOImpl();
        QuestionService questionService = new QuestionServiceImpl(questionDAO);

        ResultDAO resultDAO = new ResultDAOImpl();
        ResultService resultService = new ResultServiceImpl(resultDAO);

        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService = new StudentServiceImpl(studentDAO);

        SubjectDAO subjectDAO = new SubjectDAOImpl();
        SubjectService subjectService = new SubjectServiceImpl(subjectDAO);

        TestDAO testDAO = new TestDAOImpl();
        TestService testService = new TestServiceImpl(testDAO);

        servicesContainer.setAnswerService(answerService);
        servicesContainer.setQuestionService(questionService);
        servicesContainer.setResultService(resultService);
        servicesContainer.setStudentService(studentService);
        servicesContainer.setSubjectService(subjectService);
        servicesContainer.setTestService(testService);
        servicesContainer.setUserService(userService);
        servletContextEvent.getServletContext().setAttribute("servicesContainer", servicesContainer);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
