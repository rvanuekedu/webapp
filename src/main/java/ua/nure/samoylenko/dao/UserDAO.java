package ua.nure.samoylenko.dao;

import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.entities.User;

public interface UserDAO {
	
	User getUser(RegisterDTO registerDTO);
	
	void createUser(RegisterDTO registerDTO);

	boolean isUserExist(RegisterDTO registerDTO);

	boolean checkUsersDuplicate(RegisterDTO registerDTO);
	
}
