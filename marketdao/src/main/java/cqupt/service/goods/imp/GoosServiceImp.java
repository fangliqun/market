package cqupt.service.goods.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import cqupt.domain.Goods;
import cqupt.repository.GoodsDao;
import cqupt.service.goods.GoodsService;
@Component
public class GoosServiceImp  implements GoodsService{
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public Page<Goods> selectGoods(Pageable p) {
		return goodsDao.findAll(p);
	}

	@Override
	public Goods insertGoods(Goods goods) {
		return goodsDao.save(goods);
	}

	@Override
	public void deletetGoods(int goodsid) {
		goodsDao.delete(goodsid);;
	}

	@Override
	public Goods selectGoodsById(int goodsid) {
		return goodsDao.findOne(goodsid);
	}

	@Override
	public int update(int inventory, int money, String goodsname, int goodsid) {
		return goodsDao.update(inventory, money, goodsname, goodsid);
	}

	@Override
	public int updateGoods(int inventory, int goodsid) {
		return goodsDao.updateGoods(inventory, goodsid);
	}

	@Override
	public Goods save(Goods goods) {
		return goodsDao.save(goods);
	}

	@Override
	public Goods selectGoodsBygoodsname(String goodsname) {
		return goodsDao.selectGoodsBygoodsname(goodsname);
	}

	@Override
	public List<Goods> selectAllGoods() {
		return goodsDao.findAll();
	}

}
