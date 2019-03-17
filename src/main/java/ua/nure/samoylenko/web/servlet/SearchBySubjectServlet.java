package ua.nure.samoylenko.web.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchBySubject")
public class SearchBySubjectServlet extends HttpServlet {
    private TestService testService;
    private static Logger LOGGER = Logger.getLogger(SearchBySubjectServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet SearchBySubject start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in servlet SearchBySubject start");
        LOGGER.debug("Check parameter subject is valid");
        if (httpServletRequest.getParameter("subject") != null && !httpServletRequest.getParameter("subject").equals(StringUtils.EMPTY)) {
            int subjectId = Integer.parseInt(httpServletRequest.getParameter("subject"));
            List<TestDTO> tests = testService.getAllTestBySubject(subjectId);
            List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");
            tests.removeIf(testDTO -> passedTest.contains(testDTO.getId()));
            httpServletRequest.setAttribute("tests", tests);
            LOGGER.debug("Trying to forward to Enter");
            httpServletRequest.getRequestDispatcher("Enter").forward(httpServletRequest, httpServletResponse);
        } else {
            LOGGER.debug("Parameter subject is not valid");
            LOGGER.debug("Trying to send redirect in Enter");
            httpServletResponse.sendRedirect("Enter");
        }
    }
}
