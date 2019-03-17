package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.entities.Test;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditTest")
public class EditTestServlet extends HttpServlet {
    private TestService testService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        if (httpServletRequest.getParameter("testId") != null && !httpServletRequest.getParameter("testId").equals("")) {
            Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
            Test test = testService.getTest(testId);

            if (!test.getTestName().equals(httpServletRequest.getParameter("testName")) && !"".equals(httpServletRequest.getParameter("testName"))) {
                testService.changeTestName(httpServletRequest.getParameter("testName").trim(), testId);
            }

            if (!test.getComplexity().equals(httpServletRequest.getParameter("complexity"))) {
                testService.changeTestComplexity(httpServletRequest.getParameter("complexity").trim(), testId);
            }

            if (!"".equals(httpServletRequest.getParameter("testTime")) && test.getTime() != Integer.parseInt(httpServletRequest.getParameter("testTime"))) {
                testService.changeTestTime(Integer.parseInt(httpServletRequest.getParameter("testTime")), testId);
            }

            httpServletResponse.sendRedirect("Enter");
        } else {
            httpServletResponse.sendRedirect("Enter");
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Integer testId = Integer.parseInt(httpServletRequest.getParameter("testId"));
        Test test = testService.getTest(testId);

        httpServletRequest.setAttribute("testInfo", test);
        httpServletRequest.setAttribute("testId", testId);

        httpServletRequest.getRequestDispatcher("/WEB-INF/editTestPage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
