package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.SubjectDTO;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Subject;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.SubjectService;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddTest")
public class AddTestServlet extends HttpServlet {
    private TestService testService;
    private SubjectService subjectService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
        subjectService = servicesContainer.getSubjectService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        String testName;
        Integer subjectId;
        String complexity;
        Integer testTime;
        TestDTO testDTO = new TestDTO();
        SubjectDTO subjectDTO = new SubjectDTO();

        testName = httpServletRequest.getParameter("testName");

        if (httpServletRequest.getParameter("newSubject").equals("")) {
            subjectId = Integer.parseInt(httpServletRequest.getParameter("subjects"));
        } else {
            subjectDTO.setSubjectName(httpServletRequest.getParameter("newSubject"));
            subjectId = subjectService.addSubject(subjectDTO);
        }

        complexity = httpServletRequest.getParameter("complexity");

        testTime = Integer.parseInt(httpServletRequest.getParameter("testTime"));

        testDTO.setTestName(testName);
        testDTO.setSubjectId(subjectId);
        testDTO.setComplexityName(complexity);
        testDTO.setTime(testTime);

        testService.createTest(testDTO);

        httpServletResponse.sendRedirect("Enter");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<Subject> subjects = subjectService.getAllSubjects();
        httpServletRequest.setAttribute("subjects", subjects);

        httpServletRequest.getRequestDispatcher("/WEB-INF/addTestPage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
