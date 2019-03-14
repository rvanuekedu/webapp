package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.web.service.ResultService;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowResults")
public class ShowResultsServlet extends HttpServlet {
    private ResultService resultService;
    private StudentService studentService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        resultService = servicesContainer.getResultService();
        studentService = servicesContainer.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int studentId = studentService.getStudentIdByEmail(user.getEmail());
        List<ResultDTO> results = resultService.getResultsByStudentId(studentId);

        httpServletRequest.setAttribute("results", results);
        httpServletRequest.getRequestDispatcher("/WEB-INF/showPassedTests.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
