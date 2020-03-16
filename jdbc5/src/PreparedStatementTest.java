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
//			String name = "���ȣ";
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
		// ���� ����
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

		// 'id1', 'p1'�� ��Ȯ�� �Է��� ��� "�α��� ����"
		// ��ġ���� ������ "�α��� ����"

		try {
//			con = MyConnection.getConnection();
//			pstmt = con.prepareStatement("SELECT pwd FROM customer WHERE id = ?");
//			
//			//��ġ�ϴ� ���
//			pstmt.setString(1, "id1");
//			ResultSet rs = pstmt.executeQuery();
//			rs.next();
//			if("p1".equals(rs.getString("pwd"))) {
//				
//				System.out.println("�α��� ����");
//			}else {
//				System.out.println("�α��� ����");
//			}
//			
//			pstmt.setString(1, "id1");
//			rs = pstmt.executeQuery();
//			rs.next();
//			if("aaa".equals(rs.getString("pwd"))) {
//				
//				System.out.println("�α��� ����");
//			}else {
//				System.out.println("�α��� ����");
//			}
//			
//			
//			pstmt.setString(1, "id999");
//			rs = pstmt.executeQuery();
//			if(rs.next())
//			if("p1".equals(rs.getString("pwd"))) {
//				
//				System.out.println("�α��� ����");
//			}else {
//				System.out.println("�α��� ����");
//			}else {
//				System.out.println("�α��� ����");
//			}
//			
			// �������� �Ͻ� �ڵ�
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
				System.out.println("�α��� ����");
			} else {
				System.out.println("�α��� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
