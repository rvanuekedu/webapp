package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Student;
import ua.nure.samoylenko.entities.Subject;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;
import ua.nure.samoylenko.web.service.SubjectService;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchTest")
public class SearchTestServlet extends HttpServlet {
    private TestService testService;
    private StudentService studentService;
    private SubjectService subjectService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
        studentService = servicesContainer.getStudentService();
        subjectService = servicesContainer.getSubjectService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletRequest.setCharacterEncoding("utf-8");
        List<TestDTO> test;
        String testName = httpServletRequest.getParameter("testName");

        test = testService.getTestByName(testName);
        List<Student> unblockedStudents = studentService.getAllUnblockedStudents();
        List<Student> blockedStudents = studentService.getAllBlockedStudents();
        List<Subject> subjects = subjectService.getAllSubjects();

        httpServletRequest.setAttribute("tests", test);
        httpServletRequest.setAttribute("subjects", subjects);
        httpServletRequest.setAttribute("unblockedStudents", unblockedStudents);
        httpServletRequest.setAttribute("blockedStudents", blockedStudents);

        httpServletRequest.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(httpServletRequest, httpServletResponse);
    }

}
