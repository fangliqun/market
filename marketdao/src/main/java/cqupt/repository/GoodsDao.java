package cqupt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cqupt.domain.Goods;
import cqupt.domain.User;

public interface GoodsDao extends JpaRepository<Goods, Integer>{
	@Modifying
	@Transactional
	@Query("update Goods set inventory=?1,money=?2,goodsname=?3 where goodsid=?4")
	public int update(int inventory,int money,String goodsname,int goodsid);
	
	
	@Modifying
	@Transactional
	@Query("update Goods set inventory=?1 where goodsid=?2")
	public int updateGoods(int inventory,int goodsid);
	
	@Query("from Goods where goodsname =?1")
	public Goods selectGoodsBygoodsname(String goodsname);
}
