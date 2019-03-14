package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.QuestionDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.dto.ShowQuestionDTO;
import ua.nure.samoylenko.dto.TestDTO;
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

public class QuestionDAOImpl implements QuestionDAO{

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
                Question currentQuestion = new Question();
                Integer questionId = resultSet.getInt("id");
                String qText = resultSet.getString("qText");
                Integer testId = resultSet.getInt("tests_id");
                currentQuestion.setQuestionId(questionId);
                currentQuestion.setQuestionText(qText);
                currentQuestion.setTestId(testId);
                questions.add(currentQuestion);
            }
            return questions;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all questions", e);
        }
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
        } catch (NamingException e)  {
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
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t delete question", e);
        }
	}

    @Override
    public List<ShowQuestionDTO> getAllQuestionsByTestId(int id) {
        List<ShowQuestionDTO> questions = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_ALL_QUESTIONS_AND_ANSWERS_BY_TEST_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShowQuestionDTO showQuestionDTO = new ShowQuestionDTO();
                String questionText = resultSet.getString("qText");
                String answerText = resultSet.getString("aText");
                Boolean isCorrect = resultSet.getBoolean("isCorrect");
                Integer questionId = resultSet.getInt("question_id");
                Integer answerQuestionId = resultSet.getInt("answer_question_id");
                showQuestionDTO.setQuestionText(questionText);
                showQuestionDTO.setAnswerText(answerText);
                showQuestionDTO.setCorrect(isCorrect);
                showQuestionDTO.setQuestionId(questionId);
                showQuestionDTO.setAnswerQuestionId(answerQuestionId);
                questions.add(showQuestionDTO);
            }
            return questions;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all questions", e);
        }
    }

}
