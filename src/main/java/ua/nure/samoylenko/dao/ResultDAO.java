package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.ResultDTO;

import java.util.List;

public interface ResultDAO {

    void createResult(ResultDTO resultDTO);

    int getResultValueByStudentId(int studentId);

    List<ResultDTO> getResultsByStudentId(int studentId);

}
