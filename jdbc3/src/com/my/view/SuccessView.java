package com.my.view;

import java.util.List;

import com.my.vo.Customer;

public class SuccessView {
	/**
	 * 로그인 성공 메세지
	 */
	public static void printLogin() {
		System.out.println("로그인 성공");
	}
	
	/**
	 * 등록 성공 메세지
	 */
	public static void printRegister() {
		System.out.println("등록 성공");
	}
	
	/**
	 * 고객 한 명 출력
	 * @param c 고객
	 */
	public static void printCustomer(Customer c) {
		System.out.println(c);
	}
	
	/**
	 * 고객 여러명 출력
	 * @param list 고객들
	 */
	public static void printCustomerList(List<Customer> list) {
		for(Customer c : list) {
			System.out.println(c);
		}
	}
	
	/**
	 * 수정 성공 메세지
	 */
	public static void printUpdate() {
		System.out.println("수정 성공");
	}
}
