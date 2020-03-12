package com.my.service;

import java.util.List;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

public class CustomerService {
	private static CustomerService service = new CustomerService();
	private CustomerDAO dao;

	private CustomerService() {
		dao = new CustomerDAO();
	}

	public static CustomerService getInstance() {
		return service;
	}

	/**
	 * 아이디, 비번에 해당고객이 존재하면 로그인 성공, 없으면 로그인 실패
	 * @param id 아이디
	 * @param pwd 비밀번호
	 * @throws NotFoundException
	 */
	public void login(String id, String pwd) throws NotFoundException {
		Customer c= dao.selectById(id);
		if (c.getStatus() == 2 || !c.getPwd().equals(pwd)) {
			throw new NotFoundException("로그인 실패");
		}
	}
	
	public Customer findById(String id) throws NotFoundException{
		return dao.selectById(id);
	}
	
	
	public List<Customer> findByName(String word) throws NotFoundException{
		return dao.selectByName(word);
	}
	
	public List<Customer> findAll() throws NotFoundException{
		return dao.selectAll();
	}

	public void register(Customer c) throws AddException{
		dao.insert(c);
	}
	
	public void modify(Customer c) throws ModifyException{
		dao.update(c);
	}

	public void withdraw(String id) throws ModifyException {
		Customer c = new Customer();
		c.setId(id);
		c.setStatus(2);//탈퇴용 status값 2
		dao.update(c);
		
	}
}



