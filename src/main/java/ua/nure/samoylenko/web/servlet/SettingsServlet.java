package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.entities.Student;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Settings")
public class SettingsServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        studentService = servicesContainer.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Student student = studentService.getStudent(user.getEmail());
        httpServletRequest.setAttribute("student", student);
        httpServletRequest.getRequestDispatcher("WEB-INF/settingsPage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
