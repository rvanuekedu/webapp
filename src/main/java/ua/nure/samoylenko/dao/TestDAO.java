package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Test;

import java.util.List;

public interface TestDAO {

    void createTest(TestDTO testDTO);

    Test getTest(int id);

    List<TestDTO> getAllTestsWithSubjectNameAndNumberOfQuestions();

    List<TestDTO> getAllTestBySubject(int subjectId);

    Integer getTestTimeByTestId(int id);

    List<TestDTO> getTestByName(String testName);

    void deleteTestById(int id);

    void changeTestName(String testName, int testId);

    void changeTestTime(int time, int testId);

    void changeTestComplexity(String complexityName, int testId);

}
