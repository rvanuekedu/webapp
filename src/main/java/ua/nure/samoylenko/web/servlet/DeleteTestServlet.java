package ua.nure.samoylenko.web.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteTest")
public class DeleteTestServlet extends HttpServlet {
    private TestService testService;
    private static Logger LOGGER = Logger.getLogger(DeleteTestServlet.class);

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (httpServletRequest.getParameter("testId") != null && !httpServletRequest.getParameter("testId").equals(StringUtils.EMPTY)) {
            int testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            testService.deleteTestById(testId);
        }
        httpServletResponse.sendRedirect("Enter");
    }
}
