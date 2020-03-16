package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.view.FailView;
import com.my.view.SuccessView;
import com.my.vo.Product;

public class CartController {
	private static CartController controller = new CartController();
	private CartController() {}
	
	public static CartController getInstance() {
		return controller;
	}

	public void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		if(cart == null) {
//			cart = new HashMap<Product, Integer>();
			FailView.errorMessage("장바구니가 없습니다.");
		}else {
			SuccessView.printViewCart(id);
			
		}
	}
}
