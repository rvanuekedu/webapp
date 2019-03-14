package ua.nure.samoylenko.web.filter;

import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentFilter implements Filter {
    private StudentService studentService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        ServicesContainer servicesContainer = (ServicesContainer) request.getServletContext().getAttribute("servicesContainer");
        studentService = servicesContainer.getStudentService();
        User user = (User) session.getAttribute("user");
        if (!user.getUserType().equals("STUDENT")) {
            throw new AppException("Only student can perform this action");
        } else if (studentService.studentIsBlocked(user.getEmail())) {
            throw new AppException("This student is blocked!");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
