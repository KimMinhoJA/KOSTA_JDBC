package com.my.service;

import com.my.dao.ProductDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Product;

public class ProductService {
	private ProductDAO dao;
	private static ProductService service = new ProductService();
	
	private ProductService() {
		dao = new ProductDAO();
	}
	
	public static ProductService getInstance() {
		return service;
		
	}
	
	public Product findByNo(String prod_no) throws NotFoundException{
		return dao.selectByNo(prod_no);
	}
}
