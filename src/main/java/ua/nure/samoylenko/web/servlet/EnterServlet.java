package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
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
    private static Logger LOGGER = Logger.getLogger(EnterServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet Enter start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
        testService = servicesContainer.getTestService();
        studentService = servicesContainer.getStudentService();
        subjectService = servicesContainer.getSubjectService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do post in servlet Enter start");
        RegisterDTO registerDTO = new RegisterDTO();
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        registerDTO.setEmail(email.trim());
        registerDTO.setPassword(password.trim());
        LOGGER.debug("Check user with same mail is exist");
        if (!userService.isUserExist(registerDTO)) {
            throw new AppException("Wrong login or password. Please, try again");
        }

        User user = userService.getUser(registerDTO.getEmail());
        List<Integer> passedTest = new ArrayList<>();
        LOGGER.debug("Set user in session");
        httpServletRequest.getSession().setAttribute("user", user);
        LOGGER.debug("Set role in session");
        httpServletRequest.getSession().setAttribute("role", user.getUserType());
        LOGGER.debug("Set passedTest in session");
        httpServletRequest.getSession().setAttribute("passedTest", passedTest);
        LOGGER.debug("Trying to send redirect in Enter");
        httpServletResponse.sendRedirect("Enter");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in servlet Enter start");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");
        LOGGER.debug("Check user is blocked");
        if (null != user && !studentService.studentIsBlocked(user.getEmail())) {

            String role = (String) httpServletRequest.getSession().getAttribute("role");

            List<TestDTO> tests = testService.getAllTestsWithSubjectNameAndNumberOfQuestions();
            List<Student> unblockedStudents = studentService.getAllUnblockedStudents();
            List<Student> blockedStudents = studentService.getAllBlockedStudents();
            List<Subject> subjects = subjectService.getAllSubjects();
            tests.removeIf(testDTO -> passedTest.contains(testDTO.getId()));


            if ("ADMIN".equals(role)) {
                LOGGER.debug("user is ADMIN");
                httpServletRequest.setAttribute("unblockedStudents", unblockedStudents);
                httpServletRequest.setAttribute("blockedStudents", blockedStudents);
            }

            if (httpServletRequest.getAttribute("tests") == null) {
                httpServletRequest.setAttribute("tests", tests);
            }

            httpServletRequest.setAttribute("subjects", subjects);
            LOGGER.debug("Trying forward to /WEB-INF/mainPage.jsp");
            httpServletRequest.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            LOGGER.debug("user is blocked");
            LOGGER.debug("Trying to send redirect on startPage.jsp");
            httpServletResponse.sendRedirect("startPage.jsp");

        }
    }

}
