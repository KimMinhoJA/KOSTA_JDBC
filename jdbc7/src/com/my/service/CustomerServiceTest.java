package com.my.service;

import com.my.exception.NotFoundException;

public class CustomerServiceTest {
	public static void main(String[] args) {
		CustomerService service = CustomerService.getInstance();
		try {
			service.login("id1", "p1");
			System.out.println("로그인 성공");
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
