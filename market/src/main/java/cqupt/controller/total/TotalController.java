package cqupt.controller.total;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Component
public class TotalController {
	@RequestMapping(value="/total",method=RequestMethod.GET)
	public String show(){
		return "total";
	}
}