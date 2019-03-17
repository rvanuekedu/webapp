package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.QuestionDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.entities.Question;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    @Override
    public List<Question> getQuestionsByTestId(int id) {
        List<Question> questions = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.SELECT_ALL_QUESTIONS_BY_TEST_ID);
            pstatement.setInt(1, id);
            resultSet = pstatement.executeQuery();
            while (resultSet.next()) {
                executeQuestionsByTestId(questions, resultSet);
            }
            return questions;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all questions", e);
        }
    }

    private void executeQuestionsByTestId(List<Question> questions, ResultSet resultSet) throws SQLException {
        Question currentQuestion = new Question();
        Integer questionId = resultSet.getInt("id");
        String qText = resultSet.getString("qText");
        Integer testId = resultSet.getInt("tests_id");
        currentQuestion.setQuestionId(questionId);
        currentQuestion.setQuestionText(qText);
        currentQuestion.setTestId(testId);
        questions.add(currentQuestion);
    }

    @Override
    public void createQuestion(QuestionDTO questionDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.INSERT_QUESTION_FOR_TEST);
            int k = 1;
            pstatement.setString(k++, questionDTO.getqText());
            pstatement.setInt(k, questionDTO.getTestId());
            pstatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t insert question", e);
        }
    }

    @Override
    public void deleteQuestion(int questionId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pStatement;
        try (Connection connection = provider.getConnection()) {
            pStatement = connection.prepareStatement(SQLConstants.DELETE_QUESTION);
            pStatement.setInt(1, questionId);
            pStatement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t delete question", e);
        }
    }

}
