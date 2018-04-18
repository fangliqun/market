package cqupt.service.user.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqupt.domain.User;
import cqupt.repository.UserDao;
import cqupt.repository.UserEhcacheRepository;
import cqupt.service.user.UserService;
@Component
public class UserServiceImp implements UserService {
	@Autowired
	private UserEhcacheRepository userEhcacheRepository;
	@Override
	public User login(String username,String password) {
		return userEhcacheRepository.selectByUsername(username,password);
	}
	@Override
	public User registerUser(User user) {
		return userEhcacheRepository.save(user);
	}
}
