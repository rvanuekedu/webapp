package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.ChangeEmailDTO;
import ua.nure.samoylenko.dto.ChangePasswordDTO;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;

public interface UserDAO {
	
	User getUser(RegisterDTO registerDTO);
	
	void createUser(RegisterDTO registerDTO);

	boolean isUserExist(RegisterDTO registerDTO);

	boolean checkUsersDuplicate(String email);

	void changeUserEmail (ChangeEmailDTO changeEmailDTO);

	void chanheUserPassword (ChangePasswordDTO changePasswordDTO);
	
}
