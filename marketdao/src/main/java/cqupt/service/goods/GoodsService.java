package cqupt.service.goods;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cqupt.domain.Goods;
import cqupt.domain.User;

public interface GoodsService {
	Page<Goods> selectGoods(Pageable p);
	
	Goods insertGoods(Goods goods);
	
	void deletetGoods(int goodsid);
	
	Goods selectGoodsById(int goodsid);

	int update(int inventory,int money,String goodsname,int goodsid);
	
	int updateGoods(int inventory,int goodsid);
	
	Goods save(Goods goods);
	
	Goods selectGoodsBygoodsname(String goodsname);
	
	List<Goods> selectAllGoods();
	
}
