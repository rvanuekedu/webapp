package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.ChangeEmailDTO;
import ua.nure.samoylenko.dto.ChangePasswordDTO;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;

public interface UserService {

	User getUser(String email);

	void createUser(RegisterDTO registerDTO);

	boolean isUserExist(RegisterDTO registerDTO);

	boolean checkUsersDuplicate(String email);

	void changeUserEmail (ChangeEmailDTO changeEmailDTO);

	void changeUserPassword (ChangePasswordDTO changePasswordDTO);
	
}
