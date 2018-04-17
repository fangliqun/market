package cqupt.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cqupt.domain.Orders;
import cqupt.domain.User;

@Component
@FeignClient("marketdao-client")
public interface OrdersClient {
	@RequestMapping(value="/deleteOrders",method = RequestMethod.POST)
	public void deleteOrders(@RequestParam("orderid")int orderid);
	
	@RequestMapping(value="/insertOrders",method = RequestMethod.POST)
	public Orders insertOrders(@RequestParam("goodsname")String goodsname,@RequestParam("time")String time,
			@RequestParam("username")String username,@RequestParam("number")int number);
	
	@RequestMapping(value="/selectOrderss",method = RequestMethod.GET)
	public String selectOrders(@RequestParam("start")int start,@RequestParam("limit")int limit);
	
	@RequestMapping(value="/selectOrder",method = RequestMethod.GET)
	public Orders selectOrder(@RequestParam("orderid")int orderid);
	
	
	@RequestMapping(value="/selectAllOrder",method = RequestMethod.GET)
	public String selectAllOrder();
	
	
	@RequestMapping(value="/selectAllOrderGoodsname",method = RequestMethod.GET)
	public String selectAllOrderGoodsname();
	
	
}
