package cqupt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="goods")
public class Goods implements Serializable{
	private Integer goodsid;
	private	Integer money;
	private Integer inventory;//货存
	private String  goodsname;
	
	@Id 
	 @Column(name = "goodsid", nullable = false)   
	 @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	@Column(name="money")
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	@Column(name="inventory")
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	@Column(name="goodsname")
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
}
