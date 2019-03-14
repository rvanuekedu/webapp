package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.AnswerDTO;
import ua.nure.samoylenko.entities.Answer;

import java.util.List;

public interface AnswerDAO {

	void createAnswerForQuestionByQuestionId(AnswerDTO answerDTO);
	
	void deleteAnswerByName(AnswerDTO answerDTO);
	
	List<Answer> getAnswersByQuestionsId(AnswerDTO answerDTO);

	List<Answer> getAllAnswersByTestId(int id);

	List<String> getTrueAnswersByQuestionId(int id);

	void deleteAnswerById (int answerId);

}
