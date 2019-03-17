package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.SubjectDAO;
import ua.nure.samoylenko.dto.SubjectDTO;
import ua.nure.samoylenko.entities.Subject;
import ua.nure.samoylenko.web.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDAO subjectDAO;

    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    @Override
    public Integer addSubject(SubjectDTO subjectDTO) {
        return subjectDAO.addSubject(subjectDTO);
    }
}
