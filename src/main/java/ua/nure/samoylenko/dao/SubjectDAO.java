package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.SubjectDTO;
import ua.nure.samoylenko.entities.Subject;

import java.util.List;

public interface SubjectDAO {

    List<Subject> getAllSubjects();

    Integer addSubject(SubjectDTO subjectDTO);

}
