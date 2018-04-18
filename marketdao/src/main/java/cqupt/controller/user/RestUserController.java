package cqupt.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cqupt.domain.User;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestUserController {
	@Autowired
	private UserService userService;
	
	private static final Logger log=Logger.getLogger(RestUserController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public User login(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request){
//		User user =new User(); 
//		user.setUsername(username);
//		user.setPassword(password);
//		return ResponseBo.build(1,1+"",userService.login(user)).toJsonString();
		log.warn(username+"登陆本系统，IP:"+request.getRemoteAddr());
		return userService.login(username, password);
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public User registerUser(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request){
		User user =new User(); 
		user.setUsername(username);
		user.setPassword(password);
		log.info("IP:"+request.getRemoteAddr()+",注册了用户"+username);
		return userService.registerUser(user);
	}
}
