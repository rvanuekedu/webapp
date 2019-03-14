package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.ResultDAO;
import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.dto.ResultShowDTO;
import ua.nure.samoylenko.web.service.ResultService;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private ResultDAO resultDAO;

    public ResultServiceImpl(ResultDAO resultDAO){
        this.resultDAO = resultDAO;
    }

    @Override
    public ResultShowDTO getResultByStudentIdAndTestId(ResultShowDTO resultShowDTO) {
        return resultDAO.getResultByStudentIdAndTestId(resultShowDTO);
    }

    @Override
    public void createResult(ResultDTO resultDTO) {
        resultDAO.createResult(resultDTO);
    }

    @Override
    public int getResultValueByStudentId(int studentId) {
        return resultDAO.getResultValueByStudentId(studentId);
    }

    @Override
    public List<ResultDTO> getResultsByStudentId(int studentId) {
        return resultDAO.getResultsByStudentId(studentId);
    }
}
