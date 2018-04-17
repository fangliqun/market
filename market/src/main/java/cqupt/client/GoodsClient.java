package cqupt.client;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cqupt.domain.Goods;
@Component
@FeignClient("marketdao-client")
//@RequestMapping(value="")
public interface GoodsClient {
	@RequestMapping(value="/selectGoods",method = RequestMethod.GET)
	public String selectGoods(@RequestParam("start")int start, @RequestParam("limit")int limit);
	
	@RequestMapping(value="/insertGoods",method = RequestMethod.POST)
	public Goods insertGoods(@RequestParam("goodsname")String goodsname,@RequestParam("inventory")int inventory,@RequestParam("money")int money);
	
	@RequestMapping(value="/deletetGoods",method = RequestMethod.POST)
	public void deletetGoods(@RequestParam("goodsid")int goodsid);
	
	@RequestMapping(value="/selectGoodsById",method = RequestMethod.POST)
	public Goods selectGoodsById(@RequestParam("goodsid")int goodsid);
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public int update(@RequestParam("inventory")int inventory,@RequestParam("money")int money,
			@RequestParam("goodsname")String goodsname,@RequestParam("goodsid")int goodsid);
	
	@RequestMapping(value="/updateGoods",method = RequestMethod.POST)
	public int updateGoods(@RequestParam("inventory")int inventory,@RequestParam("goodsid")int goodsid);
	
	@RequestMapping(value="/save",method = RequestMethod.GET)
	public Goods save(@RequestParam("goods")Goods goods);
	
	@RequestMapping(value="/selectGoodsBygoodsname",method = RequestMethod.GET)
	public Goods selectGoodsBygoodsname(@RequestParam("goodsname")String goodsname);
	
	@RequestMapping(value="/selectAllGoods",method = RequestMethod.GET)
	public List<Goods> selectAllGoods();
}
