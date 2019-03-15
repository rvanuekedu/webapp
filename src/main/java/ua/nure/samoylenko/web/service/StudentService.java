package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(int id);

    List<Student> getAllStudents();

    void blockStudent(int id);

    void unblockStudent(int id);

    void createStudent(RegisterDTO registerDTO);

    Student getStudent(String email);

    Integer getStudentIdByEmail(String email);

    List<Student> getAllBlockedStudents();

    List<Student> getAllUnblockedStudents();

    boolean studentIsBlocked(String studentEmail);

}
