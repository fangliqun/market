package cqupt.controller.goods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cqupt.domain.GetMeau;
import cqupt.domain.Goods;
import cqupt.domain.GoodsMeau;
import cqupt.domain.Orders;
import cqupt.domain.User;
import cqupt.mq.ProducerConfiguration;
import cqupt.service.goods.GoodsService;
import cqupt.service.orders.OrdersService;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestGoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrdersService ordersService;
	
	 @Autowired
	 private AmqpTemplate rabbitTemplate;
	
	
	@RequestMapping(value="/selectGoods",method=RequestMethod.GET)
	public  String selectGoods(Integer start, Integer limit){
		String page=goodsService.selectGoods(start-1,limit);
		if(page!=null){
			JSONObject jo=(JSONObject) JSON.parse(page);
			JSONObject json = new JSONObject();
			json.put("total",jo.getString("totalElements"));
			json.put("data",jo.get("content"));
			return json.toJSONString();
		}else{
			return ResponseBo.build(0,"查询失败",0).toJsonString();
		}
	}
	
	@RequestMapping(value="/insertGoods",method=RequestMethod.POST)
	public  String insertGoods(String goodsname,Integer inventory,Integer money){
		System.out.println(goodsname);
		Goods goods=new Goods();
		goods.setGoodsname(goodsname);
		goods.setInventory(inventory);
		goods.setMoney(money); 
		//商品已存在 
//		List<GoodsMeau> list=GetMeau.getMeau();
//		for(int i=0;i<list.size();i++){
//			if(goodsname.equals(list.get(i).getText())){
//				return ResponseBo.build(0,"添加失败,商品已存在,请去修改",0).toJsonString();
//			}
//		}
		Goods goo=goodsService.selectGoodsBygoodsname(goodsname);
		if(goo!=null){
			return ResponseBo.build(0,"添加失败,商品已存在,请去修改",0).toJsonString();
		}
		System.out.println(goodsname);
		Goods g=goodsService.insertGoods(goods);
		if(g!=null){
			return ResponseBo.build(1,"添加成功",g).toJsonString();
		}else{
			return ResponseBo.build(0,"添加失败",g).toJsonString();
		}
	}
	
	@RequestMapping(value="/deletetGoods",method=RequestMethod.POST)
	public String deletetGoods(String datas){
		String[] o=datas.split(",");
		for(int i=0;i<o.length;i++){
			int iii=Integer.parseInt(o[i]);
			goodsService.deletetGoods(iii);
		}
		return ResponseBo.build(1,"删除成功",1).toJsonString();
	}
	
	@Transactional
	@RequestMapping(value="/updateGoods",method=RequestMethod.POST)
	public String updateGoods(int goodsid,int number,String goodsname,String username){
		Goods g=goodsService.selectGoodsById(goodsid);
		int truenumber=g.getInventory();
		if(number<=truenumber){
			SimpleDateFormat formatter=new SimpleDateFormat("MM-dd HH:mm");  
			Date date=new Date();
			String time=formatter.format(date);
			
			
			Orders order=new Orders();
			order.setGoodsname(goodsname);
			order.setUsername(username);
			order.setTime(time);
			order.setNumber(number);
			
			
			Orders o=ordersService.insertOrders(order);
			if(o!=null){
				
				String message=username+"在"+time+"购买了"+goodsname;
				System.out.println("开始发送:"+message);
				this.rabbitTemplate.convertAndSend("marketExchange","marketkey",message);
//				ProducerConfiguration producerConfiguration=new ProducerConfiguration("marketExchange","marketQueue","marketkey");
//				producerConfiguration.send("marketExchange","marketkey",message);
				
				number=truenumber-number;
				int i=goodsService.updateGoods(number,goodsid);
				if(i!=0){
					return ResponseBo.build(1,"更新成功",i).toJsonString();
				}else{
					return ResponseBo.build(0,"更新失败",0).toJsonString();
				}
			}else{
				return ResponseBo.build(0,"插入订单失败",0).toJsonString();
			}
			
		}else{
			return ResponseBo.build(0,"库存不足",0).toJsonString();
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Integer goodsid,Integer inventory,String goodsname,Integer money){
		Goods g=goodsService.selectGoodsById(goodsid);
		if(inventory!=null){
			g.setInventory(inventory);
		}
		if(goodsname!=null){
			g.setGoodsname(goodsname);
		}
		if(money!=null){
			g.setMoney(money);
		}
		int i=goodsService.update(g.getInventory(), g.getMoney(), g.getGoodsname(), g.getGoodsid());
		if(i!=0){
			return ResponseBo.build(1,"更新成功",i).toJsonString();
		}else{
			return ResponseBo.build(0,"更新失败",0).toJsonString();
		}
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Integer goodsid,Integer inventory,String goodsname,Integer money){
		Goods g=goodsService.selectGoodsById(goodsid);
		if(inventory!=null){
			g.setInventory(inventory);
		}
		if(goodsname!=null){
			g.setGoodsname(goodsname);
		}
		if(money!=null){
			g.setMoney(money);
		}
		Goods goods=goodsService.save(g);
		if(goods!=null){
			return ResponseBo.build(1,"更新成功",goods).toJsonString();
		}else{
			return ResponseBo.build(0,"更新失败",goods).toJsonString();
		}
	}
	
	@RequestMapping(value="/selectGoodsMeau",method=RequestMethod.GET)
	public String selectGoodsMeau(){
		return ResponseBo.build(1,"查询成功",GetMeau.getMeau()).toJsonString();
	}
	
//	@RequestMapping(value="/selectGoodsBuyMeau",method=RequestMethod.GET)
//	public String selectGoodsBuyMeau(){
//		List<Goods> g=goodsService.s
//		if(g!=null){
//			return ResponseBo.build(1,"查询成功",g).toJsonString();
//		}else{
//			return ResponseBo.build(0,"查询失败",g).toJsonString();
//		}
//	}
	
	@RequestMapping(value="/selectAllGoods",method=RequestMethod.GET)
	public  String selectAllGoods(){
		List<Goods> list=goodsService.selectAllGoods();
		if(list!=null){
			return ResponseBo.build(1,"查询成功",list).toJsonString();
		}else{
			return ResponseBo.build(0,"查询失败",list).toJsonString();
		}
	}
	
	@RequestMapping(value="/selectGoodsName",method=RequestMethod.GET)
	public  String selectGoodsName(String goodsname){
		Goods goods=goodsService.selectGoodsBygoodsname(goodsname);
		if(goods!=null){
			return ResponseBo.build(1,"货物已存在,请去修改",1).toJsonString();
		}else{
			return ResponseBo.build(0,"货物不存在",0).toJsonString();
		}
	}
	
	@RequestMapping(value="/readmessagefile",method=RequestMethod.GET)
	public  String readmessagefile(){
		try {
			List<String> list=new ArrayList<String>();
			File file=new File("D:/market.txt");
			if(!file.exists()) {
				file.getParentFile().mkdirs();//创建文件夹,可创建多级
				file.createNewFile();//创建文件
			}
			BufferedReader br= new BufferedReader(new FileReader("D:/market.txt"));
			String s=null;
			while((s = br.readLine())!=null){
				list.add(s);
			}
			br.close();
			if(list!=null){
				return ResponseBo.build(1,"消息存在",list).toJsonString();
			}else{
				return ResponseBo.build(0,"没有消息",0).toJsonString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseBo.build(0,"没有消息",0).toJsonString();
		}
		
	}
}
