package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.StudentDAO;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.Student;
import ua.nure.samoylenko.web.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public void blockStudent(int id) {
        studentDAO.blockStudent(id);
    }

    @Override
    public void unblockStudent(int id) {
        studentDAO.unblockStudent(id);
    }

    @Override
    public void createStudent(RegisterDTO registerDTO) {
        studentDAO.createStudent(registerDTO);
    }

    @Override
    public Student getStudent(String email) {
        return studentDAO.getStudent(email);
    }

    @Override
    public Integer getStudentIdByEmail(String email) {
        return studentDAO.getStudentIdByEmail(email);
    }

    @Override
    public List<Student> getAllBlockedStudents() {
        return studentDAO.getAllBlockedStudents();
    }

    @Override
    public List<Student> getAllUnblockedStudents() {
        return studentDAO.getAllUnblockedStudents();
    }

    @Override
    public boolean studentIsBlocked(String studentEmail) {
        return studentDAO.studentIsBlocked(studentEmail);
    }

}
