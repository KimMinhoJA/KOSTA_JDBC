package com.my.vo;

import java.util.Date;

public class Customer {
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private Date reg_dt;
	private int status;
	
	public Customer() {
		super();
	}
	
	
	public Customer(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	


	public Customer(String id, String pwd, String name, String gender, Date reg_dt, int status) {
		this(id, pwd, name);
		this.gender = gender;
		this.reg_dt = reg_dt;
		this.status = status;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ID�� ");
		builder.append(id);
		builder.append(", �̸��� ");
		builder.append(name);
		builder.append(", ������ ");
		builder.append("F".equals(gender)? "��" : "��");
		builder.append(", �������ڴ� ");
		builder.append(reg_dt);
		builder.append(", ���Ի��� ");
		builder.append(status== 1 ? "���Ի���" : "Ż�����");
		return builder.toString();
	}
	
	
}
