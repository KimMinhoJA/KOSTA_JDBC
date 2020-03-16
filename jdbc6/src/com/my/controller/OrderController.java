package com.my.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.session.Session;
import com.my.view.FailView;
import com.my.view.SuccessView;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

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
	
	
	public void addOrder(Session session) {
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		OrderInfo info = new OrderInfo();
		Customer order_customer = new Customer();
		order_customer.setId(session.getSessionId());
		info.setOrder_customer(order_customer);
		
		List<OrderLine> lines = new ArrayList<>();
		
		for(Product p : cart.keySet()) {
			OrderLine line = new OrderLine();
			line.setOrder_quantity(cart.get(p));
			line.setOrder_product(p);
			lines.add(line);
			
		}
		
		info.setLines(lines);
		try {
			service.addOrder(info);
			SuccessView.printAddOrder();
		}catch (AddException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
}
