package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BlockStudent")
public class BlockStudentServlet extends HttpServlet {
    private StudentService studentService;
    private static Logger LOGGER = Logger.getLogger(BlockStudentServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet BlockStudent start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        studentService = servicesContainer.getStudentService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do post in servlet BlockStudent start");
        Integer studentId = Integer.valueOf(httpServletRequest.getParameter("blockStudents"));
        studentService.blockStudent(studentId);
        LOGGER.debug("Trying to send redirect in BlockStudent");
        httpServletResponse.sendRedirect("BlockStudent");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do get in BlockStudent start");
        httpServletResponse.sendRedirect("Enter");
    }
}
