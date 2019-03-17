package ua.nure.samoylenko.web.servlet;

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

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        if (httpServletRequest.getParameter("subject") != null && !httpServletRequest.getParameter("subject").equals("")) {
            int subjectId = Integer.parseInt(httpServletRequest.getParameter("subject"));
            List<TestDTO> tests = testService.getAllTestBySubject(subjectId);

            List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");

            tests.removeIf(testDTO -> passedTest.contains(testDTO.getId()));

            httpServletRequest.setAttribute("tests", tests);

            httpServletRequest.getRequestDispatcher("Enter").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("Enter");
        }
    }
}
