package cqupt.controller.orders;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Component
public class OrdersController {
	@RequestMapping(value="/orders",method=RequestMethod.GET)
	public String show(){
		return "orders";
	}
}
