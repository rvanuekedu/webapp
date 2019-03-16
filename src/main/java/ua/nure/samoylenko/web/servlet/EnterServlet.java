package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Student;
import ua.nure.samoylenko.entities.Subject;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.web.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Enter")
public class EnterServlet extends HttpServlet {
    private UserService userService;
    private TestService testService;
    private StudentService studentService;
    private SubjectService subjectService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
        testService = servicesContainer.getTestService();
        studentService = servicesContainer.getStudentService();
        subjectService = servicesContainer.getSubjectService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        User user;
        RegisterDTO registerDTO = new RegisterDTO();

        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        registerDTO.setEmail(email.trim());
        registerDTO.setPassword(password.trim());

        if (!userService.isUserExist(registerDTO)) {
            throw new AppException("Wrong login or password. Please, try again");
        }

        user = userService.getUser(registerDTO.getEmail());
        List<Integer> passedTest = new ArrayList<>();

        httpServletRequest.getSession().setAttribute("user", user);
        httpServletRequest.getSession().setAttribute("role", user.getUserType());
        httpServletRequest.getSession().setAttribute("passedTest", passedTest);

        httpServletResponse.sendRedirect("Enter");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");

        if (null != user && !studentService.studentIsBlocked(user.getEmail())) {

            String role = (String) httpServletRequest.getSession().getAttribute("role");

            List<TestDTO> tests = testService.getAllTestsWithSubjectNameAndNumberOfQuestions();
            List<Student> unblockedStudents = studentService.getAllUnblockedStudents();
            List<Student> blockedStudents = studentService.getAllBlockedStudents();
            List<Subject> subjects = subjectService.getAllSubjects();

            tests.removeIf(testDTO -> passedTest.contains(testDTO.getId()));


            if ("ADMIN".equals(role)) {
                httpServletRequest.setAttribute("unblockedStudents", unblockedStudents);
                httpServletRequest.setAttribute("blockedStudents", blockedStudents);
            }

            if (httpServletRequest.getAttribute("tests") == null) {
                httpServletRequest.setAttribute("tests", tests);
            }

            httpServletRequest.setAttribute("subjects", subjects);

            httpServletRequest.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("startPage.jsp");

        }
    }

}
