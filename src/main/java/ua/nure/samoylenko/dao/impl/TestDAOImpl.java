package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.TestDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.TestDTO;
import ua.nure.samoylenko.entities.Test;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {


    @Override
    public void createTest(TestDTO testDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.INSERT_NEW_TEST);
            int k = 1;
            pstatement.setString(k++, testDTO.getTestName());
            pstatement.setInt(k++, testDTO.getSubjectId());
            pstatement.setString(k++, testDTO.getComplexityName());
            pstatement.setInt(k, testDTO.getTime());
            pstatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t create test", e);
        }
    }

    @Override
    public Test getTest(int id) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        ResultSet resultSet;
        Test test = null;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.SELECT_TEST);
            pstatement.setInt(1, id);
            resultSet = pstatement.executeQuery();
            if (resultSet.next()) {
                test = executeTest(resultSet);
            }
            return test;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain result", e);
        }
    }

    @Override
    public List<TestDTO> getAllTestsWithSubjectNameAndNumberOfQuestions() {
        List<TestDTO> tests = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_TESTS_WITH_SUBJECT_NAME_AND_NUMBER_OF_QUESTIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                executeTestByName(tests, resultSet);
            }
            return tests;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all tests", e);
        }
    }

    @Override
    public List<TestDTO> getAllTestBySubject(int subjectId) {
        List<TestDTO> tests = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_TESTS_BY_SUBJECT);
            statement.setInt(1, subjectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                executeAllTestBySubject(tests, resultSet);
            }
            return tests;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all tests", e);
        }
    }

    private void executeAllTestBySubject(List<TestDTO> tests, ResultSet resultSet) throws SQLException {
        TestDTO currentTest = new TestDTO();
        Integer testId = resultSet.getInt("id");
        String testName = resultSet.getString("test_name");
        String complexity = resultSet.getString("complexity");
        Integer time = resultSet.getInt("time");
        String subjectName = resultSet.getString("subject_name");
        Integer numberOfQuestions = resultSet.getInt("number_of_questions");
        currentTest.setId(testId);
        currentTest.setTestName(testName);
        currentTest.setComplexityName(complexity);
        currentTest.setTime(time);
        currentTest.setSubjectName(subjectName);
        currentTest.setNumberOfQuestions(numberOfQuestions);
        tests.add(currentTest);
    }

    @Override
    public Integer getTestTimeByTestId(int id) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Integer testTime = null;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_TEST_TIME_BY_TEST_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testTime = resultSet.getInt("time");
            }
            return testTime;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain test time", e);
        }
    }

    @Override
    public List<TestDTO> getTestByName(String testName) {
        List<TestDTO> tests = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_TEST_BY_NAME);
            statement.setString(1, testName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                executeTestByName(tests, resultSet);
            }
            return tests;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain test by name", e);
        }
    }

    private void executeTestByName(List<TestDTO> tests, ResultSet resultSet) throws SQLException {
        TestDTO currentTest = new TestDTO();
        Integer testId = resultSet.getInt("id");
        String name = resultSet.getString("test_name");
        String complexity = resultSet.getString("complexity");
        Integer time = resultSet.getInt("time");
        String subjectName = resultSet.getString("subject_name");
        Integer subjectId = resultSet.getInt("subjects_id");
        Integer numberOfQuestions = resultSet.getInt("number_of_questions");
        currentTest.setId(testId);
        currentTest.setTestName(name);
        currentTest.setComplexityName(complexity);
        currentTest.setTime(time);
        currentTest.setSubjectName(subjectName);
        currentTest.setSubjectId(subjectId);
        currentTest.setNumberOfQuestions(numberOfQuestions);
        tests.add(currentTest);
    }

    @Override
    public void deleteTestById(int id) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.DELETE_TEST_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t delete test", e);
        }
    }

    @Override
    public void changeTestName(String testName, int testId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.UPDATE_TEST_NAME);
            int k = 1;
            preparedStatement.setString(k++, testName);
            preparedStatement.setInt(k, testId);
            preparedStatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t update test name", e);
        }
    }

    @Override
    public void changeTestTime(int time, int testId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.UPDATE_TEST_TIME);
            int k = 1;
            preparedStatement.setInt(k++, time);
            preparedStatement.setInt(k, testId);
            preparedStatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t update test time", e);
        }
    }

    @Override
    public void changeTestComplexity(String complexityName, int testId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.UPDATE_TEST_COMPLEXITY);
            int k = 1;
            preparedStatement.setString(k++, complexityName);
            preparedStatement.setInt(k, testId);
            preparedStatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t update test complexity", e);
        }
    }

    private Test executeTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();

        String testName = resultSet.getString("test_name");
        Integer subjectId = resultSet.getInt("subjects_id");
        String complexity = resultSet.getString("complexity");
        int time = resultSet.getInt("time");

        test.setTestName(testName);
        test.setSubjectId(subjectId);
        test.setComplexity(complexity);
        test.setTime(time);

        return test;
    }

}
