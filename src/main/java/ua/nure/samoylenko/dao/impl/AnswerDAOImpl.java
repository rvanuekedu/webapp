package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.AnswerDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.AnswerDTO;
import ua.nure.samoylenko.entities.Answer;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.samoylenko.utils.SQLConstants.INSERT_ANSWER_FOR_QUESTION;

public class AnswerDAOImpl implements AnswerDAO {

	@Override
	public void createAnswerForQuestionByQuestionId(AnswerDTO answerDTO) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(INSERT_ANSWER_FOR_QUESTION);
            int k = 1;
            pstatement.setString(k++, answerDTO.getaText());
            pstatement.setBoolean(k++, answerDTO.getCorrect());
            pstatement.setInt(k, answerDTO.getQuestionId());
            pstatement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t insert answer", e);
        }
	}

	@Override
	public void deleteAnswerByName(AnswerDTO answerDTO) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.DELETE_ANSWER_BY_NAME);
            pstatement.setString(1, answerDTO.getaText());
            pstatement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t delete answer", e);
        }

	}

	@Override
	public List<Answer> getAnswersByQuestionsId(AnswerDTO answerDTO) {
		List<Answer> answers = new ArrayList<>();
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement pstatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            pstatement = connection.prepareStatement(SQLConstants.SELECT_ALL_ANSWERS_BY_QUESTIONS_ID);
            pstatement.setInt(1, answerDTO.getQuestionId());
            resultSet = pstatement.executeQuery();
            while (resultSet.next()) {
                Answer currentAnswer = new Answer();
                String aText = resultSet.getString("aText");
                Boolean isCorrect = resultSet.getBoolean("isCorrect");
                Integer questionId = resultSet.getInt("questions_id");
                Integer id = resultSet.getInt("id");
                currentAnswer.setAnswerId(id);
                currentAnswer.setAnswerText(aText);
                currentAnswer.setCorrect(isCorrect);
                currentAnswer.setQuestionId(questionId);
                answers.add(currentAnswer);
            }
            return answers;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all answers", e);
        }
	}

    @Override
    public List<Answer> getAllAnswersByTestId(int id) {
        List<Answer> answers = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_ALL_ANSWERS_BY_TEST_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer currentAnswer = new Answer();
                String aText = resultSet.getString("aText");
                Boolean isCorrect = resultSet.getBoolean("isCorrect");
                Integer questionId = resultSet.getInt("questions_id");
                Integer answerId = resultSet.getInt("id");
                currentAnswer.setAnswerId(answerId);
                currentAnswer.setAnswerText(aText);
                currentAnswer.setCorrect(isCorrect);
                currentAnswer.setQuestionId(questionId);
                answers.add(currentAnswer);
            }
            return answers;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all answers", e);
        }
    }

    @Override
    public List<String> getTrueAnswersByQuestionId(int id) {
        List<String> answers = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_ALL_ANSWERS_WHERE_TRUE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String currentAnswer;
                currentAnswer = resultSet.getString("aText");
                answers.add(currentAnswer);
            }
            preparedStatement.close();
            return answers;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all answers", e);
        }
    }

    @Override
    public void deleteAnswerById(int answerId) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.DELETE_ANSWER_BY_ID);
            preparedStatement.setInt(1, answerId);
            preparedStatement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t delete answer", e);
        }
    }

}
