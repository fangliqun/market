package cqupt.controller.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cqupt.controller.goods.RestGoodsController;
import cqupt.domain.Goods;
import cqupt.domain.Orders;
import cqupt.domain.User;
import cqupt.service.orders.OrdersService;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestOrdersController {
	
	private static final Logger log=Logger.getLogger(RestGoodsController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="/deleteOrders",method=RequestMethod.POST)
	public void deleteOrders(@RequestParam("orderid")int orderid){
		ordersService.deleteOrders(orderid);
	}
//	证明分布式事
//	@Transactional(rollbackFor=RuntimeException.class)   
//	@RequestMapping(value="/deleteOrders",method=RequestMethod.GET)
//	public void deleteOrders(@RequestParam("a")int a,@RequestParam("b")int b,@RequestParam("c")int c,@RequestParam("d")int d){
//		ordersService.deleteOrders(a);
//		System.out.println("删除a");
//		ordersService.deleteOrders(b);
//		if(c==5) {
//			 log.warn("删除订单失败"+c);
//			 throw new RuntimeException("test");  
//		}
//		ordersService.deleteOrders(d);
//	}
	@RequestMapping(value="/insertOrders",method=RequestMethod.POST)
	public Orders insertOrders(@RequestParam("goodsname")String goodsname,@RequestParam("time")String time,
			@RequestParam("username")String username,@RequestParam("number")int number){
		Orders o=new Orders();
		o.setGoodsname(goodsname);
		o.setTime(time);
		o.setUsername(username);
		o.setNumber(number);
		log.warn("用户购买了"+goodsname);
		return ordersService.insertOrders(o);
	}
	
	@RequestMapping(value="/selectOrderss",method=RequestMethod.GET)
	public String selectOrderss(Integer start, Integer limit){
		Sort sort=new Sort(Direction.DESC,"time");
		Pageable p=new PageRequest(start,limit,sort);
		return JSON.toJSONString(ordersService.selectOrders(p));
	}
	
	@RequestMapping(value="/selectOrders",method=RequestMethod.GET)
	public List<Orders> selectOrders(@RequestParam("time")String time){
		return ordersService.selectOrders(time);
	}
	
	@RequestMapping(value="/selectOrdersByusername",method=RequestMethod.GET)
	public List<Orders> selectOrdersByusername(@RequestParam("username")String username){
		return ordersService.selectOrdersByusername(username);
	}
	
	@RequestMapping(value="/selectOrder",method=RequestMethod.GET)
	public Orders selectOrder(@RequestParam("orderid")int orderid){
		return ordersService.selectOrder(orderid);
	}
	
	@RequestMapping(value="/selectAllOrder",method=RequestMethod.GET)
	public String selectAllOrder(){
		List<Orders> list=ordersService.selectAllOrder();
		Map<String, Integer> map=new HashMap<String, Integer>();
	
		for(int i=0;i<list.size();i++){
			if(!map.containsKey(list.get(i).getGoodsname())){
				map.put(list.get(i).getGoodsname(), list.get(i).getNumber());
			}else{
				int number=map.get(list.get(i).getGoodsname());
				number=number+list.get(i).getNumber();
				map.put(list.get(i).getGoodsname(), number);
			}
		}
		return ResponseBo.build(1,"成功",map).toJsonString();
	}
	
	@RequestMapping(value="/selectAllOrderGoodsname",method=RequestMethod.GET)
	public String selectAllOrderGoodsname(){
		List<Orders> list=ordersService.selectAllOrder();
		List<String>  data=new ArrayList<String>();
	
		for(int i=0;i<list.size();i++){
			if(!data.contains(list.get(i).getGoodsname())){
				data.add(list.get(i).getGoodsname());
			}
		}
		return ResponseBo.build(1,"成功",data).toJsonString();
	}
}
