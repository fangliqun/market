package cqupt.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cqupt.domain.Goods;
import cqupt.domain.User;

@Component
@FeignClient("marketdao-client")
public interface UserClient {
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public User login(@RequestParam("username")String username,@RequestParam("password")String password);
	
	@RequestMapping(value="/selectUserByUsername",method = RequestMethod.POST)
	public User selectUserByUsername(@RequestParam("username")String username);
	
	@RequestMapping(value="/registerUser",method = RequestMethod.POST)
	public User registerUser(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("address")String address,@RequestParam("callnumber")String callnumber);
}
