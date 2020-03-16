package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.NotFoundException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAO {
	public void insert(OrderInfo info) {
		
	}
	
	public OrderInfo selectByNo(int order_no) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = MyConnection.getConnection();
			String selectByNoSQL = "SELECT order_no, order_id, order_dt"
					+ ", c.name"
					+ ", order_prod_no, order_quantity"
					+ ", prod_name, prod_price"
					+ " FROM order_info NATURAL JOIN order_line"
					+ " JOIN customer c ON order_info.order_id = c.id"
					+ " JOIN product p ON order_line.order_prod_no = p.prod_no"
					+ " WHERE order_no = ?";
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setInt(1, order_no);
			rs = pstmt.executeQuery();
			boolean isFirst = false;
			OrderInfo info = new OrderInfo();
			List<OrderLine> lines = new ArrayList<>();
			info.setLines(lines);
			
			while(rs.next()) {
				if(!isFirst) { //첫 번째 행
					info.setOrder_no(rs.getInt("order_no"));
					Customer c = new com.my.dao.CustomerDAO().selectById(rs.getString("order_id"));
					info.setOrder_customer(c);
					info.setOrder_dt(rs.getDate("order_dt"));
					isFirst = true;
				}
				Product p = new Product();
				p.setProd_no(rs.getString("order_prod_no"));
				p.setProd_name(rs.getString("prod_name"));
				p.setProd_price(rs.getInt("prod_price"));
				
				OrderLine line = new OrderLine();
				line.setOrder_product(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				
				lines.add(line);
			}
			if(info.getLines().size() == 0)
				throw new NotFoundException("주문번호에 해당하는 주문이 없습니다.");
			
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("주문번호 검색 오류 : " + e.getMessage());
		}finally{
			MyConnection.close(con, pstmt, rs);
		}
	}
	
	/**
	 * id를 이용해서 id에 해당하는 고객의 주문 정보를 반환해준다.
	 * @param id 아이디
	 * @return 고객의 주문정보
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectById(String id) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectByIdSQL = "SELECT order_no, order_id, order_dt, name, order_prod_no, order_quantity, prod_name, prod_price"
					+ " FROM order_info NATURAL JOIN order_line" 
					+ " JOIN customer c ON order_info.order_id = c.id"
					+ " JOIN product p ON order_line.order_prod_no = p.prod_no"
					+ " WHERE order_id = ?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			List<OrderInfo> infos = new ArrayList<OrderInfo>();
			boolean isFirst = true;
			OrderInfo info = null;
			List<OrderLine> lines = null;
			
			while(rs.next()) {
				if((isFirst || rs.getInt("order_no") != info.getOrder_no())) {
					lines = new ArrayList<OrderLine>();
					info = new OrderInfo();
					infos.add(info);
					info.setOrder_no(rs.getInt("order_no"));
					info.setOrder_dt(rs.getDate("order_dt"));
					Customer c = new com.my.dao.CustomerDAO().selectById(rs.getString("order_id"));
					info.setOrder_customer(c);
					info.setLines(lines);
					isFirst = false;
				}
				
				Product p = new Product();
				p.setProd_no(rs.getString("order_prod_no"));
				p.setProd_name(rs.getString("prod_name"));
				p.setProd_price(rs.getInt("prod_price"));
				
				OrderLine line = new OrderLine();
				line.setOrder_product(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				lines.add(line);
			}
			if(infos.size() == 0)
				throw new NotFoundException(id + "고객의 주문 정보가 없습니다.");
			return infos;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new NotFoundException("주문전체검색 오류 : " + e.getMessage());
		}finally {
			MyConnection.close(con, pstmt, rs);
		}
	}
}
