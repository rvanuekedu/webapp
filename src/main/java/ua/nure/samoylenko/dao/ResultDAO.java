package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.ResultDTO;
import ua.nure.samoylenko.dto.ResultShowDTO;

import java.util.List;

public interface ResultDAO {
	
	ResultShowDTO getResultByStudentIdAndTestId(ResultShowDTO resultShowDTO);
	
	void createResult(ResultDTO resultDTO);

	int getResultValueByStudentId(int studentId);

	List<ResultDTO> getResultsByStudentId (int studentId);
	
}
