package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO dao = new CustomerDAO();
		List<Customer> list = null;
		Customer c = 
				new Customer("idt", "pt11", "nt11", "M", null, 0);
//				new Customer("id1", "pt", "nt", "M", null, 0);
//				new Customer("idt2", "pt", "nt", "F", null, 0);
				
//		try {
//			dao.insert(c);
//		}catch(AddException e) {
//			e.printStackTrace();
//		}
						
				
//		try {
//			c = dao.selectById("idt");
//			System.out.println(c);
//		}catch (NotFoundException e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			list = dao.selectAll();
//			for(Customer customer : list) {
//				System.out.println(customer);
//			}
//		}catch (NotFoundException e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			list = dao.selectByName("n1");
//			for(Customer customer : list) {
//				System.out.println(customer);
//			}
//		}
//		catch (NotFoundException e) {
//			e.printStackTrace();
//		}
		
		try {
			dao.update(c);
			System.out.println(dao.selectById(c.getId()));
		}catch (ModifyException e) {
			e.printStackTrace();
		}
		catch(NotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
