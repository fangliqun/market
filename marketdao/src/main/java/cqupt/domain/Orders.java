package cqupt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Orders implements Serializable{
	private Integer orderid;
	private String username;
	private String goodsname;
	private String time;
	private Integer number;   
    private String address;
    private String callnumber;
	
   
	 @Id 
	 @Column(name = "orderid", nullable = false)   
	 @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	@Column(name="number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="goodsname")
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	@Column(name="time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	 
    @Column(name="address")
	public String getAddress() {
		return address;
	}
    
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="callnumber")
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
}
