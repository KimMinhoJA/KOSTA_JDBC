package com.my.view;

import java.util.List;

import com.my.vo.Customer;

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
	public static void printUpdate() {
		System.out.println("���� ����");
	}
}
