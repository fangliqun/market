package cqupt.service.user.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqupt.client.UserClient;
import cqupt.domain.User;
import cqupt.repository.UserDao;
import cqupt.service.user.UserService;
@Component
public class UserServiceImp implements UserService {
	@Autowired
	private UserClient userClient;
	
	@Override
	public User login(User user) {
		String username=user.getUsername();
		String password=user.getPassword();
		return userClient.login(username, password);
	}

	@Override
	public User registerUser(User user) {
		String username=user.getUsername();
		String password=user.getPassword();
		return userClient.registerUser(username, password);
	}

}
