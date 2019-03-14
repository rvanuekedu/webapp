package ua.nure.samoylenko.web.servlet;

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

@WebServlet("/ShowResult")
public class ShowResultServlet extends HttpServlet {
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
        int result = resultService.getResultValueByStudentId(studentId);

        httpServletRequest.setAttribute("result", result);
        httpServletRequest.getRequestDispatcher("WEB-INF/showResult.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
