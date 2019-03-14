package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.TestService;
import ua.nure.samoylenko.utils.SortTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/SortTests")
public class SortTestsServlet extends HttpServlet {
    private TestService testService;
    private static Map<String, Comparator> comparatorMap = new TreeMap<>();

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        testService = servicesContainer.getTestService();

        comparatorMap.put("byNameA-Z", SortTest.SORT_TESTS_BY_NAME_AZ);
        comparatorMap.put("byNameZ-A", SortTest.SORT_TESTS_BY_NAME_ZA);
        comparatorMap.put("bySubjectA-Z", SortTest.SORT_TESTS_BY_SUBJECT_AZ);
        comparatorMap.put("bySubjectZ-A", SortTest.SORT_TESTS_BY_SUBJECT_ZA);
        comparatorMap.put("byNumberOfQuestions0-9", SortTest.SORT_TESTS_BY_NUMBER_OF_QUESTIONS_09);
        comparatorMap.put("byNumberOfQuestions9-0", SortTest.SORT_TESTS_BY_NUMBER_OF_QUESTIONS_90);
        comparatorMap.put("byComplexityIncrease", SortTest.SORT_TEST_BY_COMPLEXITY_INCREASE);
        comparatorMap.put("byComplexityDecrease", SortTest.SORT_TEST_BY_COMPLEXITY_DECREASE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<TestDTO> tests = testService.getAllTestsWithSubjectNameAndNumberOfQuestions();
        List<Integer> passedTest = (List<Integer>) httpServletRequest.getSession().getAttribute("passedTest");

        tests.removeIf(testDTO -> passedTest.contains(testDTO.getId()));

        if (tests.size() > 1 && httpServletRequest.getParameter("sort") != null) {
            tests.sort(comparatorMap.get(httpServletRequest.getParameter("sort")));
        }
        httpServletRequest.setAttribute("tests", tests);

        httpServletRequest.getRequestDispatcher("Enter").forward(httpServletRequest, httpServletResponse);

    }
}
