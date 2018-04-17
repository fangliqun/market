package cqupt.controller.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Component
public class UserController {
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
}
