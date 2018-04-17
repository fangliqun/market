package cqupt.controller.goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cqupt.controller.user.RestUserController;
import cqupt.domain.GetMeau;
import cqupt.domain.Goods;
import cqupt.domain.GoodsMeau;
import cqupt.domain.Orders;
import cqupt.domain.User;
import cqupt.service.goods.GoodsService;
import cqupt.service.orders.OrdersService;
import cqupt.service.user.UserService;
import cqupt.utils.ResponseBo;

@RestController
@Component
public class RestGoodsController {
	private static final Logger log=Logger.getLogger(RestGoodsController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="/selectGoods",method=RequestMethod.GET)
	public String selectGoods(Integer start, Integer limit){
		Pageable p=new PageRequest(start,limit);
//		System.out.println(start+" "+limit);
//		JSONObject json = new JSONObject();
//		json.put("pagegoods",  goodsService.selectGoods(p));
		return JSON.toJSONString(goodsService.selectGoods(p));
	}
	
	@RequestMapping(value="/insertGoods",method=RequestMethod.POST)
	public Goods insertGoods(String goodsname,int inventory,int money){
		Goods goods=new Goods();
		goods.setGoodsname(goodsname);
		goods.setInventory(inventory);
		goods.setMoney(money);
		System.out.println(goods.getGoodsname());
		log.warn("用户添加了"+goodsname);
		return goodsService.insertGoods(goods);
	}
	
	@RequestMapping(value="/deletetGoods",method=RequestMethod.POST)
	public void deletetGoods(@RequestParam("goodsid")int goodsid){
		goodsService.deletetGoods(goodsid);
	}
	
	@RequestMapping(value="/selectGoodsById",method=RequestMethod.POST)
	public Goods selectGoodsById(@RequestParam("goodsid")int goodsid){
		return goodsService.selectGoodsById(goodsid);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public int update(@RequestParam("inventory")int inventory,@RequestParam("money")int money,
			@RequestParam("goodsname")String goodsname,@RequestParam("goodsid")int goodsid){
		return goodsService.update(inventory, money, goodsname, goodsid);
	}
	
	@RequestMapping(value="/updateGoods",method=RequestMethod.POST)
	public int updateGoods(@RequestParam("inventory")int inventory,@RequestParam("goodsid")int goodsid){
		return goodsService.updateGoods(inventory, goodsid);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.GET)
	public Goods save(@RequestParam("goods")Goods goods){
		return goodsService.save(goods);
	}
	
	@RequestMapping(value="/selectGoodsBygoodsname",method=RequestMethod.GET)
	public Goods selectGoodsBygoodsname(@RequestParam("goodsname")String goodsname){
		return goodsService.selectGoodsBygoodsname(goodsname);
	}
	
	@RequestMapping(value="/selectAllGoods",method=RequestMethod.GET)
	public List<Goods> selectAllGoods(){
		return goodsService.selectAllGoods();
	}
}
