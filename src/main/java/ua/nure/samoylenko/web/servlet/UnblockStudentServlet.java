package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UnblockStudent")
public class UnblockStudentServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        studentService = servicesContainer.getStudentService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Integer studentId = Integer.valueOf(httpServletRequest.getParameter("unblockStudents"));

        studentService.unblockStudent(studentId);

        httpServletResponse.sendRedirect("UnblockStudent");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("Enter");
    }
}
