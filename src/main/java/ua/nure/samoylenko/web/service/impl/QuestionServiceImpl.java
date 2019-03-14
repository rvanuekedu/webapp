package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.QuestionDAO;
import ua.nure.samoylenko.dto.QuestionDTO;
import ua.nure.samoylenko.dto.ShowQuestionDTO;
import ua.nure.samoylenko.entities.Question;
import ua.nure.samoylenko.web.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDAO questionDAO;

    public QuestionServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public List<Question> getQuestionsByTestId(int id) {
        return questionDAO.getQuestionsByTestId(id);
    }

    @Override
    public void createQuestion(QuestionDTO questionDTO) {
        questionDAO.createQuestion(questionDTO);
    }

    @Override
    public void deleteQuestion(int questionId) {
        questionDAO.deleteQuestion(questionId);
    }

    @Override
    public List<ShowQuestionDTO> getAllQuestionsByTestId(int id) {
        return questionDAO.getAllQuestionsByTestId(id);
    }
}
