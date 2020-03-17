package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.my.exception.NotFoundException;
import com.my.sql.MyConnection;
import com.my.vo.Product;

public class ProductDAO {
	public Product selectByNo(String prod_no) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product p = null;
		
		try {
			con = MyConnection.getConnection();
			String selectByNoSQL = "SELECT * FROM product WHERE prod_no = ?";
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setString(1, prod_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Product(rs.getString("prod_no"), rs.getString("prod_name"), rs.getInt("prod_price"));
			}
			if(p == null)
				throw new NotFoundException("찾고자하는 상품번호가 없습니다.");
			return p;
			
		}catch (Exception e) {
			throw new NotFoundException("상품 찾기 오류 : ");
		}finally {
			MyConnection.close(con, pstmt, rs);
		}
	}
}
