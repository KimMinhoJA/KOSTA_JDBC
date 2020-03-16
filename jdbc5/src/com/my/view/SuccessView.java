package com.my.view;

import java.util.List;

import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

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
	public static void printModify() {
		System.out.println("수정 성공");
		
	}

	public static void printWithdraw() {
		System.out.println("탈퇴 성공");
		
	}
	
	/**
	 * 모든 주문내역 출력
	 * @param infos
	 */
	public static void printOrderInfos(List<OrderInfo> infos) {
		for (OrderInfo info : infos) {
			System.out.println("주문번호" + info.getOrder_no());
			System.out.println("주문자 " + info.getOrder_customer().getId());
			System.out.println("주문일자 " + info.getOrder_dt());
			System.out.println("주문 상세정보");
			for (OrderLine line : info.getLines()) {
				Product p = line.getOrder_product();
				System.out.println(p.getProd_no() + " : " + p.getProd_name() + " : " + p.getProd_price() + " : "
						+ line.getOrder_quantity());
			}
			System.out.println();
		}
	}
	
	/**
	 * 주문 내역 출력
	 * @param info
	 */
	public static void printOrderInfo(OrderInfo info) {
		System.out.println("주문자 " + info.getOrder_customer().getId());
		System.out.println("주문일자 " + info.getOrder_dt());
		System.out.println("주문 상세정보");
		for(OrderLine line : info.getLines()) {
			Product p = line.getOrder_product();
			System.out.println(p.getProd_no() + " : " + p.getProd_name() + " : " + p.getProd_price() + " : " + line.getOrder_quantity());
		}
	}
}
