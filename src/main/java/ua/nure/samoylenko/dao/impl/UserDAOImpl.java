package ua.nure.samoylenko.dao.impl;

import ua.nure.samoylenko.dao.UserDAO;
import ua.nure.samoylenko.db.ConnectionProvider;
import ua.nure.samoylenko.dto.ChangeEmailDTO;
import ua.nure.samoylenko.dto.ChangePasswordDTO;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.exception.DBException;
import ua.nure.samoylenko.utils.SQLConstants;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public User getUser(RegisterDTO registerDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        User user = null;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_USERS_WHERE_EMAIL);
            statement.setString(1, registerDTO.getEmail());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = executeUser(resultSet);
            }
            return user;
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t obtain user", e);
        }
    }

    private User executeUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String userType = resultSet.getString("user_type");

        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);
        return user;
    }

    @Override
    public void createUser(RegisterDTO registerDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.INSERT_USER);
            int index = 1;
            statement.setString(index++, registerDTO.getEmail().trim());
            statement.setString(index, registerDTO.getPassword().trim());
            statement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t create user", e);
        }
    }

    @Override
    public boolean isUserExist(RegisterDTO registerDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_USERS_WHERE_EMAIL_PASSWORD);
            int k = 1;
            statement.setString(k++, registerDTO.getEmail());
            statement.setString(k, registerDTO.getPassword());
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t check user existence", e);
        }
    }

    @Override
    public boolean checkUsersDuplicate(String email) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.SELECT_USERS_WHERE_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t check user duplicate", e);
        }
    }

    @Override
    public void changeUserEmail(ChangeEmailDTO changeEmailDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.UPDATE_USER_EMAIL);
            int k = 1;
            statement.setString(k++, changeEmailDTO.getNewEmai().trim());
            statement.setString(k, changeEmailDTO.getCurrentEmail().trim());
            statement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t change email", e);
        }
    }

    @Override
    public void chanheUserPassword(ChangePasswordDTO changePasswordDTO) {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        PreparedStatement statement;
        try (Connection connection = provider.getConnection()) {
            statement = connection.prepareStatement(SQLConstants.UPDATE_USER_PASSWORD);
            int k = 1;
            statement.setString(k++, changePasswordDTO.getNewPassword().trim());
            statement.setString(k, changePasswordDTO.getUserEmail());
            statement.execute();
        } catch (NamingException e) {
            throw new DBException("Can't obtain SQL connection", e);
        } catch (SQLException e) {
            throw new DBException("Can`t change password", e);
        }
    }
}
