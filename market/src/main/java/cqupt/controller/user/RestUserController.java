package cqupt.controller.user;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cqupt.domain.User;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestUserController {
	@Autowired
	private UserService userService;
	
	private static Logger log = Logger.getLogger(RestUserController.class);  

	
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public String UserLogin(HttpServletRequest request,String username,String password){
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		User loginuser=userService.login(user);
		if(loginuser!=null){
			System.out.println(loginuser.getPassword()+"sss"+password);
			if(loginuser.getPassword().equals(password)) {
				HttpSession s= request.getSession();
				s.setAttribute("username", username);
				Date startTime=new Date();
				s.setAttribute("startTime", startTime);
				log.warn(username+"访问了系统");  
				return ResponseBo.build(1,"登陆成功",loginuser).toJsonString();
			}else {
				return ResponseBo.build(0,"用户名或密码错误",loginuser).toJsonString();
			}
			
		}else{
			return ResponseBo.build(0,"用户名或密码错误",loginuser).toJsonString();
		}
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession h=request.getSession();
		if((h.getAttribute("username"))!=null){
			h.removeAttribute("username");
			log.warn(h.getAttribute("username")+"退出了系统，"
					+ "开始时间"+h.getAttribute("startTime")+",结束时间"+new Date());  
			return ResponseBo.build(1,"退出成功",1).toJsonString();
		}else{
			return ResponseBo.build(0,"退出失败",0).toJsonString();
		}
		
	}
}
