package com.my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;

public class CustomerDAO {
	public void insert(Customer c) throws AddException {
		// 1. DB연결
		// 2. SQL구문 송신
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = MyConnection.getConnection();

			String insertSQL = "INSERT INTO customer(id, pwd, name, gender) VALUES(?,?,?,?)";

			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			pstmt.setString(4, c.getGender());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getErrorCode() == 1) {// pk중복
				throw new AddException("이미 존재하는 ID입니다.");
			}
			throw new AddException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, null);
		}

	}

	public void update(Customer c) throws ModifyException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			String updateSQL = "UPDATE customer SET pwd = ?, name = ?, gender = ? "+/*, reg_dt = ?, status = ?*/ "WHERE id = ?";
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, c.getPwd());
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getGender());
//			pstmt.setDate(4, (Date)c.getReg_dt());
//			pstmt.setInt(5, c.getStatus());
			pstmt.setString(4, c.getId());
			
			pstmt.executeUpdate();
			//여기부터
		}catch (Exception e) {
			throw new ModifyException("수정 오류");
		}finally {
			MyConnection.close(con, pstmt, null);
		}
		
	}

	/**
	 * 아이디에 해당 고객을 검색하여 반환한다.
	 * 
	 * @param id 아이디
	 * @return 고객객체
	 * @throws NotFoundexception DB연결실패이거나 아이디에 해당 고객이 없으면 예외 발생한다.
	 */
	public Customer selectById(String id) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		try {
			con = MyConnection.getConnection();

			String selectByIdSQL = "SELECT * FROM customer WHERE id = ?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6));

			} else {
				throw new NotFoundException("찾으려는 아이디가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new NotFoundException("id로 검색 오류 : " + e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

		return c;
	}

	/**
	 * 이름에 해당단어를 포함한 고객들을 검색한다.
	 * @param word 단어
	 * @return 고객정보들
	 * @throws NotFoundException DB연결실패이거나 이름에 해당하는 고객이 없으면 예외 발생한다.
	 */
	public List<Customer> selectByName(String word) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		List<Customer> list = new ArrayList<Customer>();
		try {
			con = MyConnection.getConnection();
			String selectByNameSQL = "SELECT * FROM customer WHERE INSTR(name, ?) <> 0";
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6)));
			}
			
			if(list.size() == 0) {
				throw new NotFoundException(word + "에 해당하는 이름은 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("이름으로 검색오류 : " + e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

		return list;
	}

	/**
	 * 모든 고객의 정보 반환
	 * 
	 * @return 모든 고객 정보
	 * @throws DB연결실패이거나 고객이 존재하지 않을 때 예외 발생한다. 
	 */
	public List<Customer> selectAll() throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Customer> list = new ArrayList<Customer>();

		try {
			con = MyConnection.getConnection();
			String selectAllSQL = "SELECT * FROM customer";
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6)));
			}
			
			if(list.size() == 0) {
				throw new NotFoundException("고객이 한 명도 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("전체 검색 오류 : " + e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

		return list;
	}

}
