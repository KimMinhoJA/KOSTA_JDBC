package com.my.vo;

public class OrderLine {
	private Product order_product;
	private int order_quantity;
	public OrderLine() {
		super();
	}
	public OrderLine(int order_no, Product order_product, int order_quantity) {
		super();
		this.order_product = order_product;
		this.order_quantity = order_quantity;
	}
	public Product getOrder_product() {
		return order_product;
	}
	public void setOrder_product(Product order_product) {
		this.order_product = order_product;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	@Override
	public String toString() {
		return "OrderLine [order_product=" + order_product + ", order_quantity="
				+ order_quantity + "]";
	}
	
}
