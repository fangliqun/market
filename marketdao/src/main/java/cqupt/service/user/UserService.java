package cqupt.service.user;

import cqupt.domain.User;

public interface UserService {
	User login(String username,String password);
	
	User registerUser(User user);
	
	User selectUserByUsername(String username);
}
