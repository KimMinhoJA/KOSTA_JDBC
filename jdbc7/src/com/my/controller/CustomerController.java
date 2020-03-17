package com.my.controller;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.service.CustomerService;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.view.FailView;
import com.my.view.SuccessView;
import com.my.vo.Customer;

public class CustomerController {
	private static CustomerController controller = new CustomerController();
	private CustomerService service = CustomerService.getInstance();

	private CustomerController() {}

	public static CustomerController getInstance() {
		return controller;
	}

	public void login(String id, String pwd) {
		try {
			service.login(id, pwd);
			SuccessView.printLogin();
			//로그인성공한 고객정보를 시스템에 기억 -> 자료구조를 싱글톤패턴으로 만들어서 관리한다.
			SessionSet ss = SessionSet.getInstance();
			Session session = new Session(id);
			ss.add(session);
		} catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}
	
	public void register(Customer c) {
		try{
			service.register(c);
			SuccessView.printRegister();
		}catch (AddException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void findById(String id) {
		try {
			Customer c = service.findById(id);
			SuccessView.printCustomer(c);
		}catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void findByName(String word) {
		try {
			List<Customer> list = service.findByName(word);
			SuccessView.printCustomerList(list);
		}catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void findAll() {
		try {
			List<Customer> list = service.findAll();
			SuccessView.printCustomerList(list);
		}catch (NotFoundException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	public void logout(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		ss.remove(session);
		
	}
	
	public void modify(Customer c) {
		try {
			service.modify(c);
			SuccessView.printModify();
		}catch (ModifyException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public void withdraw(String id) {
		try {
			service.withdraw(id);
			SuccessView.printWithdraw();
		}catch (ModifyException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
