package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.ResultDTO;

import java.util.List;

public interface ResultService {

    void createResult(ResultDTO resultDTO);

    int getResultValueByStudentId(int studentId);

    List<ResultDTO> getResultsByStudentId(int studentId);

}
