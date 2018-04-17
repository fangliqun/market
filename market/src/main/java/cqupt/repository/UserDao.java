package cqupt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cqupt.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
	@Query("from User as u where u.username= ?1 and u.password= ?2")
	public User login(String username,String password);
}
