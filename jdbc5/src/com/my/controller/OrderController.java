package com.my.controller;

import java.util.List;

import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.view.FailView;
import com.my.view.SuccessView;
import com.my.vo.OrderInfo;

public class OrderController {
	private static OrderController controller = new OrderController();
	private OrderService service = OrderService.getInstance();
	
	private OrderController() {}
	
	public static OrderController getInstance() {
		return controller;
	}
	
	public void orderFindByNo(int order_no) {
		try{
			OrderInfo info = service.orderFindByNo(order_no);
			SuccessView.printOrderInfo(info);
		}catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public void orderFindById(String order_id) {
		try {
			List<OrderInfo> infos = service.orderFindById(order_id);
			SuccessView.printOrderInfos(infos);
		}catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	
	
	
}
