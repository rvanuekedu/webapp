package ua.nure.samoylenko.web.service.impl;

import ua.nure.samoylenko.dao.UserDAO;
import ua.nure.samoylenko.dto.ChangeEmailDTO;
import ua.nure.samoylenko.dto.ChangePasswordDTO;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.web.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(RegisterDTO registerDTO) {
        return userDAO.getUser(registerDTO);
    }

    @Override
    public void createUser(RegisterDTO registerDTO) {
        userDAO.createUser(registerDTO);
    }

    @Override
    public boolean isUserExist(RegisterDTO registerDTO) {
        return userDAO.isUserExist(registerDTO);
    }

    @Override
    public boolean checkUsersDuplicate(String email) {
        return userDAO.checkUsersDuplicate(email);
    }

    @Override
    public void changeUserEmail(ChangeEmailDTO changeEmailDTO) {
        userDAO.changeUserEmail(changeEmailDTO);
    }

    @Override
    public void chanheUserPassword(ChangePasswordDTO changePasswordDTO) {
        userDAO.chanheUserPassword(changePasswordDTO);
    }

}
