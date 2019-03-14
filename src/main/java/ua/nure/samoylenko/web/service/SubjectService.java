package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.SubjectDTO;
import ua.nure.samoylenko.entities.Subject;

import java.util.List;

public interface SubjectService {

	String getSubjectNameById(int id);

	List<Subject> getAllSubjects();

	Integer addSubject(SubjectDTO subjectDTO);

}
