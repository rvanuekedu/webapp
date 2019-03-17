package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.TestDAO;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Test;
import ua.nure.samoylenko.web.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    private TestDAO testDAO;

    public TestServiceImpl(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    @Override
    public void createTest(TestDTO testDTO) {
        testDAO.createTest(testDTO);
    }

    @Override
    public Test getTest(int id) {
        return testDAO.getTest(id);
    }

    @Override
    public List<TestDTO> getAllTestsWithSubjectNameAndNumberOfQuestions() {
        return testDAO.getAllTestsWithSubjectNameAndNumberOfQuestions();
    }

    @Override
    public List<TestDTO> getAllTestBySubject(int subjectId) {
        return testDAO.getAllTestBySubject(subjectId);
    }

    @Override
    public Integer getTestTimeByTestId(int id) {
        return testDAO.getTestTimeByTestId(id);
    }

    @Override
    public List<TestDTO> getTestByName(String testName) {
        return testDAO.getTestByName(testName);
    }

    @Override
    public void deleteTestById(int id) {
        testDAO.deleteTestById(id);
    }

    @Override
    public void changeTestName(String testName, int testId) {
        testDAO.changeTestName(testName, testId);
    }

    @Override
    public void changeTestTime(int time, int testId) {
        testDAO.changeTestTime(time, testId);
    }

    @Override
    public void changeTestComplexity(String complexityName, int testId) {
        testDAO.changeTestComplexity(complexityName, testId);
    }
}
