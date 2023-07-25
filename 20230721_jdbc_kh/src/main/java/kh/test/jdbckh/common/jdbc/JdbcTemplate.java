package kh.test.jdbckh.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
	private static Connection conn = null; // static 딱 하나만 존재

	// Singleton 패턴으로 Connection 객체가 많이 생성됨을 방지
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "kh", "kh");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn != null) {
			System.out.println("DB 연결 성공");
		} else {
			System.out.println("~~~DB 연결 실패~~~~");
		}
		return conn;
	}

	public static void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	생략 가능 범위
//	public void close(PreparedStatement pstmt) {
//		try {
//			if (pstmt != null)
//				pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if (rset != null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
