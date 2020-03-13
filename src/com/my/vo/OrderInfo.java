package com.my.vo;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private int order_no;
//	private String order_id; 	//db에서 Customer와 서로 관계를 짓고 있기 때문에 이렇게 하는 것은 옳지 않은 표현이다. 
	private Customer order_customer;
	private Date order_dt;
	private List<OrderLine> lines;
	
	public OrderInfo() {
		super();
	}
	public OrderInfo(int order_no, Customer order_customer, Date order_dt, List<OrderLine> lines) {
		super();
		this.order_no = order_no;
		this.order_customer = order_customer;
		this.order_dt = order_dt;
		this.lines = lines;
	}
	
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
	public List<OrderLine> getLines() {
		return lines;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getOrder_customer() {
		return order_customer;
	}
	public void setOrder_customer(Customer order_customer) {
		this.order_customer = order_customer;
	}
	public Date getOrder_dt() {
		return order_dt;
	}
	public void setOrder_dt(Date order_dt) {
		this.order_dt = order_dt;
	}
	
	@Override
	public String toString() {
		return "OrderInfo [order_no=" + order_no + ", order_customer=" + order_customer + ", order_dt=" + order_dt
				+ ", lines=" + lines + "]";
	}
}
