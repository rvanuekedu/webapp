package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.StudentDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.dto.StudentDTO;
import ua.nure.samoylenko.entities.Student;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public Student getStudentById(int id) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        Student student = null;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.GET_STUDENT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = executeStudent(resultSet);
            }
            resultSet.close();
            statement.close();
            return student;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain student by id", e);
        }
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_STUDENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student currentStudent = new Student();
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String userEmail = resultSet.getString("user_email");
                Boolean isBanned = resultSet.getBoolean("is_banned");
                Integer id = resultSet.getInt("id");
                currentStudent.setId(id);
                currentStudent.setFirstName(firstName);
                currentStudent.setSecondName(secondName);
                currentStudent.setUserEmail(userEmail);
                currentStudent.setIsBanned(isBanned);
                students.add(currentStudent);
            }
            resultSet.close();
            statement.close();
            return students;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all students", e);
        }
	}

	@Override
	public void blockStudent(int id) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.UPDATE_STUDENTS_SET_BANNED_TRUE);
            statement.setInt(1, id);
            statement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t block student", e);
        }
	}

	@Override
	public void unblockStudent(int id) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.UPDATE_STUDENTS_SET_BANNED_FALSE);
            statement.setInt(1, id);
            statement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t unblock student", e);
        }
	}

	@Override
	public void createStudent(RegisterDTO registerDTO) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.INSERT_NEW_STUDENT);
            int index = 1;
            statement.setString(index++, registerDTO.getFirstName());
            statement.setString(index++, registerDTO.getSecondName());
            statement.setString(index, registerDTO.getEmail());
            statement.execute();
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t create student", e);
        }
	}

	@Override
	public Student getStudent(RegisterDTO registerDTO) {
		ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        Student student = null;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_STUDENT);
            statement.setString(1, registerDTO.getEmail());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = executeStudent(resultSet);
            }
            resultSet.close();
            statement.close();
            return student;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain student", e);
        }

	}

    @Override
    public Integer getStudentIdByEmail(String email) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Integer studentId = null;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_STUDENT_ID_BY_EMAIL);
            preparedStatement.setString(1, email.trim());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentId = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            return studentId;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain student", e);
        }
    }

    @Override
    public List<Student> getAllBlockedStudents() {
        List<Student> students = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_BLOCKED_STUDENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student currentStudent = new Student();
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String userEmail = resultSet.getString("user_email");
                Boolean isBanned = resultSet.getBoolean("is_banned");
                Integer id = resultSet.getInt("id");
                currentStudent.setId(id);
                currentStudent.setFirstName(firstName);
                currentStudent.setSecondName(secondName);
                currentStudent.setUserEmail(userEmail);
                currentStudent.setIsBanned(isBanned);
                students.add(currentStudent);
            }
            resultSet.close();
            statement.close();
            return students;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all students", e);
        }
    }

    @Override
    public List<Student> getAllUnblockedStudents() {
        List<Student> students = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_UNBLOCKED_STUDENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student currentStudent = new Student();
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String userEmail = resultSet.getString("user_email");
                Boolean isBanned = resultSet.getBoolean("is_banned");
                Integer id = resultSet.getInt("id");
                currentStudent.setId(id);
                currentStudent.setFirstName(firstName);
                currentStudent.setSecondName(secondName);
                currentStudent.setUserEmail(userEmail);
                currentStudent.setIsBanned(isBanned);
                students.add(currentStudent);
            }
            resultSet.close();
            statement.close();
            return students;
        } catch (NamingException e)  {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all students", e);
        }
    }

    @Override
    public boolean studentIsBlocked(String studentEmail) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        boolean result = false;
        try (Connection connection = provider.getConnection()) {
            preparedStatement = connection.prepareStatement(SQLConstants.SELECT_STUDENT_BY_EMAIL);
            preparedStatement.setString(1, studentEmail.trim());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean("is_banned");
            }
            return result;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain student", e);
        }
    }

    private Student executeStudent(ResultSet resultSet) throws SQLException {
		 Student student = new Student();
	        int id = resultSet.getInt("id");
	        String firstName = resultSet.getString("first_name");
	        String secondName = resultSet.getString("second_name");
	        String userEmail = resultSet.getString("user_email");
	        boolean isBanned = resultSet.getBoolean("is_banned");

	        student.setId(id);
	        student.setFirstName(firstName);
	        student.setSecondName(secondName);
	        student.setUserEmail(userEmail);
	        student.setIsBanned(isBanned);

	        return student;
	}

}
