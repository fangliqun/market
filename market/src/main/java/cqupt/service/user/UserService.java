package cqupt.service.user;

import cqupt.domain.User;

public interface UserService {
	User login(User user);
	
	User registerUser(User user);
}
