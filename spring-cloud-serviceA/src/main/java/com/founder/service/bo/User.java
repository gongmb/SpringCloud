package com.founder.service.bo;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -2939963392355465224L;

	private int id;
	private String name;
	private String password;
	private Integer phone;
	private String city;
	private String ip;
	private String img;
	private String wechat;
	private String sina;
	
	
	
	public User() {
		super();
	}
	
	
	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public User( String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(int id, String name, String password, Integer phone,
			String city, String ip, String img, String wechat, String sina) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.city = city;
		this.ip = ip;
		this.img = img;
		this.wechat = wechat;
		this.sina = sina;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getSina() {
		return sina;
	}
	public void setSina(String sina) {
		this.sina = sina;
	}
	
	
	
}
