package com.my.view;

import java.util.List;

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
}
