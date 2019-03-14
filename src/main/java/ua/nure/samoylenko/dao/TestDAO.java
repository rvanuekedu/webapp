package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.ResultShowDTO;
import ua.nure.samoylenko.dto.TestChangeComplexityDTO;
import ua.nure.samoylenko.dto.TestChangeTimeDTO;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Test;

import java.util.List;

public interface TestDAO {

    void createTest(TestDTO testDTO);

    Test getTest(int id);

    List<Test> getAllTests();

    List<TestDTO> getAllTestsWithSubjectName();

    List<TestDTO> getAllTestsWithSubjectNameAndNumberOfQuestions();

    List<Test> getAllTestBySubject();

    List<TestDTO> getAllTestSortedByName();

    List<TestDTO> getAllTestSortedBySubject();

    List<TestDTO> getAllTestSortedByNumberOfQuestions();

    Integer getTestTimeByTestId(int id);

    List<TestDTO> getTestByName(String testName);

    void deleteTestById(int id);

    void changeTestName(String testName, int testId);

    void changeTestTime(int time, int testId);

    void changeTestComplexity(String complexityName, int testId);

    ResultShowDTO getTestWithSubjectName(ResultShowDTO resultShowDTO);

}
