import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null; // �۽�
		ResultSet rs = null; // ����

		// 1) JDBC ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2 DB����
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ����Ŭ�� JDBC url
			String user = "hr"; // hr����
			String password = "hr"; // hr���� ���
			con = DriverManager.getConnection(url, user, password);

			// 3. SQL���� �۽�
			String selectSQL = "SELECT employee_id, first_name, salary, hire_date FROM employees";
			stmt = con.createStatement();
			// ��� �ۼ���
			rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {// �������� �����ϸ� true ������ false -> Ŀ���� �̵���Ű�� �޼ҵ�
				System.out.println(rs.getInt("employee_id") + "\t:\t" + rs.getString("first_name") + "\t:\t"
						+ rs.getFloat(3) + "\t:\t" + rs.getDate("hire_date") + "\t:\t" + rs.getString("hire_date"));

			}

			// ������ �߿���
			rs.close();
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ������ �߿���
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

	}

}
