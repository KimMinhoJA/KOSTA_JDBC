package com.my.service;

import java.util.List;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;

public class OrderService {
	private static OrderService service = new OrderService();
	OrderDAO dao;
	
	private OrderService() {
		dao = new OrderDAO(); 
	}
	
	public static OrderService getInstance(){
		return service;
	}
	/**
	 * order_no�� �ش��ϴ� �ֹ� ���� ��ȯ
	 * @param order_no
	 * @return
	 * @throws NotFoundException
	 */
	public OrderInfo orderFindByNo(int order_no) throws NotFoundException{
		OrderInfo info = dao.selectByNo(order_no);
		return info;
	}
	
	/**
	 * order_id���� ��� �ֹ� ���� ��ȯ
	 * @param order_id
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> orderFindById(String order_id) throws NotFoundException{
		List<OrderInfo> infos = dao.selectById(order_id);
		return infos;
	}

	public void addOrder(OrderInfo info) throws AddException{
		dao.insert(info);
	}
}
