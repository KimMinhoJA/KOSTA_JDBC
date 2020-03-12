import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null; // 송신
		ResultSet rs = null; // 수신

		// 1) JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2 DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클용 JDBC url
			String user = "hr"; // hr계정
			String password = "hr"; // hr계정 비번
			con = DriverManager.getConnection(url, user, password);

			// 3. SQL구문 송신
			String selectSQL = "SELECT employee_id, first_name, salary, hire_date FROM employees";
			stmt = con.createStatement();
			// 결과 송수신
			rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {// 다음행이 존재하면 true 없으면 false -> 커서를 이동시키는 메소드
				System.out.println(rs.getInt("employee_id") + "\t:\t" + rs.getString("first_name") + "\t:\t"
						+ rs.getFloat(3) + "\t:\t" + rs.getDate("hire_date") + "\t:\t" + rs.getString("hire_date"));

			}

			// 순서가 중요함
			rs.close();
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 순서가 중요함
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
