package com.my.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.my.controller.OrderController;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class SuccessView {
	/**
	 * �α��� ���� �޼���
	 */
	public static void printLogin() {
		System.out.println("�α��� ����");
	}
	
	/**
	 * ��� ���� �޼���
	 */
	public static void printRegister() {
		System.out.println("��� ����");
	}
	
	/**
	 * �� �� �� ���
	 * @param c ��
	 */
	public static void printCustomer(Customer c) {
		System.out.println(c);
	}
	
	/**
	 * �� ������ ���
	 * @param list ����
	 */
	public static void printCustomerList(List<Customer> list) {
		for(Customer c : list) {
			System.out.println(c);
		}
	}
	
	/**
	 * ���� ���� �޼���
	 */
	public static void printModify() {
		System.out.println("���� ����");
		
	}

	public static void printWithdraw() {
		System.out.println("Ż�� ����");
		
	}
	
	/**
	 * ��� �ֹ����� ���
	 * @param infos
	 */
	public static void printOrderInfos(List<OrderInfo> infos) {
		for (OrderInfo info : infos) {
			System.out.println("�ֹ���ȣ" + info.getOrder_no());
			System.out.println("�ֹ��� " + info.getOrder_customer().getId());
			System.out.println("�ֹ����� " + info.getOrder_dt());
			System.out.println("�ֹ� ������");
			for (OrderLine line : info.getLines()) {
				Product p = line.getOrder_product();
				System.out.println(p.getProd_no() + " : " + p.getProd_name() + " : " + p.getProd_price() + " : "
						+ line.getOrder_quantity());
			}
			System.out.println();
		}
	}
	
	/**
	 * �ֹ� ���� ���
	 * @param info
	 */
	public static void printOrderInfo(OrderInfo info) {
		System.out.println("�ֹ��� " + info.getOrder_customer().getId());
		System.out.println("�ֹ����� " + info.getOrder_dt());
		System.out.println("�ֹ� ������");
		for(OrderLine line : info.getLines()) {
			Product p = line.getOrder_product();
			System.out.println(p.getProd_no() + " : " + p.getProd_name() + " : " + p.getProd_price() + " : " + line.getOrder_quantity());
		}
	}
	
	/**
	 * ������� ��ٱ��� ���� ���
	 * @param id
	 */
	public static void printViewCart(String id) {
		System.out.println("��ٱ��� ����...");
		Session mysession = SessionSet.getInstance().get(id);
		Map<Product, Integer> cart = (Map)mysession.getAttribute("cart");
		for(Product p : cart.keySet()) {
			String no = p.getProd_no();
			String name = p.getProd_name();
			int price = p.getProd_price();
			int quantity = cart.get(p);
			System.out.println(no + " : " + name + " : " + price + "\t" + quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1. �ֹ��ϱ�, 9. ������");
		switch(sc.nextLine()) {
		case "1" :
			
			//////////////////////////////////
			Session session = SessionSet.getInstance().get(id);
//			session.setAttribute("cart", cart);
			
			//��ٱ��Ͽ� �߰�
			OrderController orderController = OrderController.getInstance();
			orderController.addOrder(session);
			
			break;
		case "9" :
			break;
		}
		
		
	}
	
	public static void printAddOrder() {
		System.out.println("�ֹ� ����");
		
	}

	public static void printPutCart() {
		System.out.println("��ٱ��Ͽ� �߰� ����");
		
	}
}
