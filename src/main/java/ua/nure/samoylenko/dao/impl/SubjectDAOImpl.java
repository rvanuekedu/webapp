package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.SubjectDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.SubjectDTO;
import ua.nure.samoylenko.entities.Subject;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_ALL_SUBJECTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject currentSubject = new Subject();
                String subjectName = resultSet.getString("subject_name");
                Integer id = resultSet.getInt("id");
                currentSubject.setId(id);
                currentSubject.setName(subjectName);
                subjects.add(currentSubject);
            }
            return subjects;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain all subjects", e);
        }
    }

    @Override
    public Integer addSubject(SubjectDTO subjectDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        Integer subjectId = null;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.INSERT_NEW_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subjectDTO.getSubjectName());
            if (statement.executeUpdate() > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    subjectId = resultSet.getInt(1);
                }
            }
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t add subject", e);
        }
        return subjectId;
    }

}
