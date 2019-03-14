package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.dto.ResultShowDTO;

import java.util.List;

public interface ResultService {

    ResultShowDTO getResultByStudentIdAndTestId(ResultShowDTO resultShowDTO);

    void createResult(ResultDTO resultDTO);

    int getResultValueByStudentId(int studentId);

    List<ResultDTO> getResultsByStudentId(int studentId);

}
