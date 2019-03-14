package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.dto.ShowQuestionDTO;
import ua.nure.samoylenko.entities.Question;

import java.util.List;

public interface QuestionService {

	List<Question> getQuestionsByTestId(int id);

	void createQuestion(QuestionDTO questionDTO);

	void deleteQuestion(int questionId);

	List<ShowQuestionDTO> getAllQuestionsByTestId(int id);
}
