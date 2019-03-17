package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.ResultDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOImpl implements ResultDAO {

    @Override
    public void createResult(ResultDTO resultDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.INSERT_RESULT_FOR_STUDENT_AND_TEST);
            int k = 1;
            pstatement.setInt(k++, resultDTO.getValueOfResult());
            pstatement.setString(k++, resultDTO.getDateToDB());
            pstatement.setInt(k++, resultDTO.getTestId());
            pstatement.setInt(k, resultDTO.getStudentId());
            pstatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t insert result", e);
        }
    }

    @Override
    public int getResultValueByStudentId(int studentId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int value = 0;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_RESULT_VALUE_BY_STUDENT_ID);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("result");
            }
            return value;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain result", e);
        }
    }

    @Override
    public List<ResultDTO> getResultsByStudentId(int studentId) {
        List<ResultDTO> results = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_RESULTS_WITH_TEST_NAME);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                executeResultsByStudentId(results, resultSet);
            }
            return results;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain results", e);
        }
    }

    private void executeResultsByStudentId(List<ResultDTO> results, ResultSet resultSet) throws SQLException {
        ResultDTO resultDTO = new ResultDTO();
        String testName = resultSet.getString("test_name");
        int resultValue = resultSet.getInt("result");
        Date date = resultSet.getDate("date");
        resultDTO.setTestName(testName);
        resultDTO.setValueOfResult(resultValue);
        resultDTO.setDate(date);
        results.add(resultDTO);
    }

}
