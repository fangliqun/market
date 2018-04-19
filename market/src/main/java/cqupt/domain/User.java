package cqupt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User  implements Serializable{
	private Integer userid;
	private String username;
	private String password;
    private String address;
    private String callnumber;
	    
	    
	 @Id 
	 @Column(name = "userid", nullable = false)   
	 @GeneratedValue(strategy = GenerationType.AUTO)//唯一主键
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
