package cqupt.service.goods.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cqupt.client.GoodsClient;
import cqupt.domain.Goods;
import cqupt.repository.GoodsDao;
import cqupt.service.goods.GoodsService;
@Component
public class GoosServiceImp  implements GoodsService{
	@Autowired
	private GoodsClient goodsClient;
	
	@Override
	public String selectGoods(Integer start, Integer limit) {
		return goodsClient.selectGoods(start,limit);
	}

	@Override
	public Goods insertGoods(Goods goods) {
		return goodsClient.insertGoods(goods.getGoodsname(),goods.getInventory(),goods.getMoney());
	}

	@Override
	public void deletetGoods(int goodsid) {
		 goodsClient.deletetGoods(goodsid);
	}

	@Override
	public Goods selectGoodsById(int goodsid) {
		return goodsClient.selectGoodsById(goodsid);
	}

	@Override
	public int update(int inventory, int money, String goodsname, int goodsid) {
		return goodsClient.update(inventory, money, goodsname, goodsid);
	}

	@Override
	public int updateGoods(int inventory, int goodsid) {
		return goodsClient.updateGoods(inventory, goodsid);
	}

	@Override
	public Goods save(Goods goods) {
		return goodsClient.save(goods);
	}

	@Override
	public Goods selectGoodsBygoodsname(String goodsname) {
		return goodsClient.selectGoodsBygoodsname(goodsname);
	}

	@Override
	public List<Goods> selectAllGoods() {
		return goodsClient.selectAllGoods();
	}

}
