package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAO {
	public void insert(OrderInfo info) throws AddException{
		Connection con = null;
		//클래스 : OrderInfo
		//체이블 : order_info, order_line
		//트랜잭션 시작, Connection의 autoCommit을 해제
		try {
		con = MyConnection.getConnection();
		con.setAutoCommit(false);
		
		insertOrderInfo(info, con);	//order_info테이블 주문 기본 추가
		insertOrderLine(info, con);	//order_line테이블 주문 상세 추가

		//트랜잭션 완료
		con.commit();
		}catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(con, null, null);
		}
	}
	
	
	
	/**
	 * 
	 * @param info
	 * @throws AddException
	 */
	public void insertOrderInfo(OrderInfo info, Connection con) throws AddException{
		PreparedStatement pstmt = null;
		
		try {
			String insertOrderInfoSQL = "INSERT INTO order_info(order_no, order_id) VALUES(order_seq.NEXTVAL, ?)";
			pstmt = con.prepareStatement(insertOrderInfoSQL);
			pstmt.setString(1, info.getOrder_customer().getId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			throw new AddException();
		}finally {
			MyConnection.close(null, pstmt, null);
		}
		
	}

//	public void insertOrderLine(OrderInfo info, Connection con) throws AddException{
//		PreparedStatement pstmt = null;
//		try {
//			String insertOrderLineSQL = "INSERT INTO order_line(order_no, order_prod_no, order_quantity) VALUES(order_seq.CURRVAL, ?, ?)";
//			pstmt = con.prepareStatement(insertOrderLineSQL);
//			for(OrderLine line : info.getLines()) {
//				pstmt.setString(1, line.getOrder_product().getProd_no());
//				pstmt.setInt(2, line.getOrder_quantity());
//				pstmt.executeUpdate();
//			}
//		}catch (Exception e) {
//			throw new AddException();
//		}finally {
//			MyConnection.close(null, pstmt, null);
//		}
//		
//		
//	}
	public void insertOrderLine(OrderInfo info, Connection con) throws AddException{
		PreparedStatement pstmt = null;
		try {
			String insertOrderLineSQL = "INSERT INTO order_line(order_no, order_prod_no, order_quantity) VALUES(order_seq.CURRVAL, ?, ?)";
			pstmt = con.prepareStatement(insertOrderLineSQL);
			for(OrderLine line : info.getLines()) {
				pstmt.setString(1, line.getOrder_product().getProd_no());
				pstmt.setInt(2, line.getOrder_quantity());
//				pstmt.executeUpdate();
				pstmt.addBatch(); //일괄처리할 작업에 추가
			}
			
			pstmt.executeBatch();//일괄처리
		}catch (Exception e) {
			throw new AddException();
		}finally {
			MyConnection.close(null, pstmt, null);
		}
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
