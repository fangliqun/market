package cqupt.controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Component
public class GoodsController {
	@RequestMapping(value="/goods",method=RequestMethod.GET)
	public String show(HttpSession session){
		return "goods";
	}
}
