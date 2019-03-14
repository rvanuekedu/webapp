package ua.nure.samoylenko.web.service;

import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;

public interface UserService {

	User getUser(RegisterDTO registerDTO);

	void createUser(RegisterDTO registerDTO);

	boolean isUserExist(RegisterDTO registerDTO);

	boolean checkUsersDuplicate(RegisterDTO registerDTO);
	
}
