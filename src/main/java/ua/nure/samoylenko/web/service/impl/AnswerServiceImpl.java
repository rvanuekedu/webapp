package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.AnswerDAO;
import ua.nure.samoylenko.dto.AnswerDTO;
import ua.nure.samoylenko.entities.Answer;
import ua.nure.samoylenko.web.service.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerDAO answerDAO;

    public AnswerServiceImpl(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    public void createAnswerForQuestionByQuestionId(AnswerDTO answerDTO) {
        answerDAO.createAnswerForQuestionByQuestionId(answerDTO);
    }

    @Override
    public void deleteAnswerByName(AnswerDTO answerDTO) {
        answerDAO.deleteAnswerByName(answerDTO);
    }

    @Override
    public List<Answer> getAnswersByQuestionsId(AnswerDTO answerDTO) {
        return answerDAO.getAnswersByQuestionsId(answerDTO);
    }

    @Override
    public List<Answer> getAllAnswersByTestId(int id) {
        return answerDAO.getAllAnswersByTestId(id);
    }

    @Override
    public List<String> getTrueAnswersByQuestionId(int id) {
        return answerDAO.getTrueAnswersByQuestionId(id);
    }

    @Override
    public void deleteAnswerById(int answerId) {
        answerDAO.deleteAnswerById(answerId);
    }
}
