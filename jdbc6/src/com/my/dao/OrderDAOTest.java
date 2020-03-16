package com.my.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAOTest {
	public static void main(String[] args) {
		OrderDAO odao = new OrderDAO();
		try {
			OrderInfo info = odao.selectByNo(1);
			System.out.println(info);
			
			System.out.println("주문자 " + info.getOrder_customer());
			System.out.println("주문일자 " + info.getOrder_dt());
			System.out.println("주문 상세정보");
			for(OrderLine line : info.getLines()) {
				Product p = line.getOrder_product();
				System.out.println(p.getProd_no() + " : " + p.getProd_name() + " : " + p.getProd_price() + " : " + line.getOrder_quantity());
			}
			
		} catch (NotFoundException e) {

			e.printStackTrace();
		}
		List<OrderInfo> infos = new ArrayList<OrderInfo>();

		try {
			infos = odao.selectById("id2");
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

		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
