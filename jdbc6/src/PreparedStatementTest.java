import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.my.sql.MyConnection;

public class PreparedStatementTest {
	public static void main(String[] args) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = MyConnection.getConnection();
//			String sql = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
//			pstmt = con.prepareStatement(sql);
//			String id = "test";
//			String pwd = "test";
//			String name = "김민호";
//
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			pstmt.setString(3, name);
//			
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			MyConnection.close(con, pstmt, null);
//			
//		}
		// 성별 변경
		Connection con = null;
		PreparedStatement pstmt = null;
//		try {
//			con = MyConnection.getConnection();
//			pstmt = con.prepareStatement("UPDATE customer SET gender=? WHERE MOD(TO_NUMBER(SUBSTR(id, -1, 1)) ,2) = ?");
//			String gender = "F";
//			pstmt.setString(1, gender);
//			pstmt.setInt(2, 1);
//			
//			int num = pstmt.executeUpdate();
//			System.out.println(num);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 'id1', 'p1'를 정확히 입력한 경우 "로그인 성공"
		// 일치하지 않으면 "로그인 실패"

		try {
//			con = MyConnection.getConnection();
//			pstmt = con.prepareStatement("SELECT pwd FROM customer WHERE id = ?");
//			
//			//일치하는 경우
//			pstmt.setString(1, "id1");
//			ResultSet rs = pstmt.executeQuery();
//			rs.next();
//			if("p1".equals(rs.getString("pwd"))) {
//				
//				System.out.println("로그인 성공");
//			}else {
//				System.out.println("로그인 실패");
//			}
//			
//			pstmt.setString(1, "id1");
//			rs = pstmt.executeQuery();
//			rs.next();
//			if("aaa".equals(rs.getString("pwd"))) {
//				
//				System.out.println("로그인 성공");
//			}else {
//				System.out.println("로그인 실패");
//			}
//			
//			
//			pstmt.setString(1, "id999");
//			rs = pstmt.executeQuery();
//			if(rs.next())
//			if("p1".equals(rs.getString("pwd"))) {
//				
//				System.out.println("로그인 성공");
//			}else {
//				System.out.println("로그인 실패");
//			}else {
//				System.out.println("로그인 실패");
//			}
//			
			// 선생님이 하신 코드
			Scanner sc = new Scanner(System.in);
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("Password : ");
			String pwd = sc.next();
			
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM customer WHERE id = ? and pwd = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("로그인 성공");
			} else {
				System.out.println("로그인 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
