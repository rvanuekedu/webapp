package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.entities.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> getQuestionsByTestId(int id);

    void createQuestion(QuestionDTO questionDTO);

    void deleteQuestion(int questionId);

}
