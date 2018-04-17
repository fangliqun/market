package cqupt.controller.orders;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cqupt.domain.Goods;
import cqupt.domain.Orders;
import cqupt.domain.User;
import cqupt.service.orders.OrdersService;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestOrdersController {
	private static final Logger log =Logger.getLogger(RestOrdersController.class);
	@Autowired
	private OrdersService ordersService;
	
	private AmqpTemplate  rabbitTemplate;
	
	@Transactional   
	@RequestMapping(value="/deleteOrders",method=RequestMethod.POST)
	public String deleteOrders(String datas){
		ArrayList list=new ArrayList();
		String[] o=datas.split(",");
		for(int i=0;i<o.length;i++){
			int iii=Integer.parseInt(o[i]);
//			int iordersService.deleteOrders(iii);
//			this.rabbitTemplate.convertAndSend("goodsExchange","goods",iii);
			list.add(iii);
			System.out.println("开始发送:"+iii);
		}
		return ResponseBo.build(1,"删除成功",1).toJsonString();
	}
	
	@Transactional(rollbackFor=RuntimeException.class)   
	@RequestMapping(value="/deleteOrders",method=RequestMethod.GET)
	public void deleteOrders(@RequestParam("a")int a,@RequestParam("b")int b,@RequestParam("c")int c,@RequestParam("d")int d){
		ordersService.deleteOrders(a);
		System.out.println("删除a");
		ordersService.deleteOrders(b);
		if(c==5) {
			 log.warn("删除订单失败"+c);
			 throw new RuntimeException("test");  
		}
		ordersService.deleteOrders(d);
	}
	@RequestMapping(value="/insertOrders",method=RequestMethod.POST)
	public String insertOrders(String goodsname, String username,String time){
		Orders o=new Orders();
		o.setGoodsname(goodsname);
		o.setTime(time);
		o.setUsername(username);
		Orders order=ordersService.insertOrders(o);
		if(order!=null){
			return ResponseBo.build(1,"添加成功",order).toJsonString();
		}else{
			return ResponseBo.build(0,"添加失败",order).toJsonString();
		}
	}
	
	@RequestMapping(value="/selectOrders",method=RequestMethod.GET)
	public String selectOrders(Integer start, Integer limit){
		String page=ordersService.selectOrders(start-1,limit);
		if(page!=null){
			JSONObject jo=(JSONObject) JSON.parse(page);
			JSONObject json = new JSONObject();
//			json.put("total", page.getTotalElements());
//			json.put("data",page.getContent());
			json.put("total",jo.getString("totalElements"));
			json.put("data",jo.get("content"));
			return json.toJSONString();
		}else{
			return ResponseBo.build(0,"查询失败",page).toJsonString();
		}
	}
	
//	@RequestMapping(value="/selectOrdersByTime",method=RequestMethod.GET)
//	public String selectOrdersByTime(String time){
//		List<Orders> o=ordersService.selectOrders(time);
//		if(o!=null){
//			return ResponseBo.build(1,"查询成功",o).toJsonString();
//		}else{
//			return ResponseBo.build(0,"查询失败",o).toJsonString();
//		}
//	}
	
	@RequestMapping(value="/selectOrder",method=RequestMethod.GET)
	public String selectOrder(int orderid){
		Orders o=ordersService.selectOrder(orderid);
		if(o!=null){
			return ResponseBo.build(1,"查询成功",o).toJsonString();
		}else{
			return ResponseBo.build(0,"查询失败",o).toJsonString();
		}
	}
	
	@RequestMapping(value="/selectAllOrder",method=RequestMethod.GET)
	public String selectAllOrder(){
		String o=ordersService.selectAllOrder();
		JSONObject json=new JSONObject();
		JSONObject j=(JSONObject) json.parse(o);
		Object content=j.get("content");
		if(content!=null){
			return ResponseBo.build(1,"查询成功",content).toJsonString();
		}else{
			return ResponseBo.build(0,"查询失败",content).toJsonString();
		}
	}
	
	@RequestMapping(value="/selectAllOrderGoodsname",method=RequestMethod.GET)
	public String selectAllOrderGoodsname(){
		String o=ordersService.selectAllOrderGoodsname();
		JSONObject json=new JSONObject();
		JSONObject j=(JSONObject) json.parse(o);
		Object content=j.get("content");
		if(content!=null){
			return ResponseBo.build(1,"查询成功",content).toJsonString();
		}else{
			return ResponseBo.build(0,"查询失败",content).toJsonString();
		}
	}
	
//	@RequestMapping(value="/update",method=RequestMethod.GET)更新货存
//	public String update(int orderid){
//		Orders o=ordersService.selectOrder(orderid);
//		if(o!=null){
//			return ResponseBo.build(1,"查询成功",o).toJsonString();
//		}else{
//			return ResponseBo.build(0,"查询失败",o).toJsonString();
//		}
//	}
}
