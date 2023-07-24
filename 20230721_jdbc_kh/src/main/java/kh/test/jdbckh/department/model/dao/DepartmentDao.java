package kh.test.jdbckh.department.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.test.jdbckh.department.model.vo.DepartmentVo;

public class DepartmentDao {

	// DB에서 tb_department 테이블의 전달받은 학과번호를 통해 학과 1개의 상세정보를 읽어옴
	public DepartmentVo selectOneDepartment(String departmentNo) {
		DepartmentVo result = null;

		String query = "Select departmentNo, departmentName, category, openYn, capacity" + "From tb_department"
				+ "Where department_no = " + "'" + departmentNo + "'";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			// 1. Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DBMS 연결하여 Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "kh", "kh");

			// 3. Connection 객체로부터 query 전달할 수 있는 Statement/PrepareStatement 객체 생성
			pstmt = conn.prepareStatement(query);

			// 4. query 문 실행
			// 4-1. select 문이면 executeQuery()
			// ResultSet rset = stmt.executeQuery(“select * from employee”);
			// ResultSet rset = pstmt.executeQuery();

			// 4-2. insert/update/delete 문이면 executeUpdate()
			// int result = stmt.executeUpdate(“insert into employee values(....)”);
			// int result = pstmt.executeUpdate();

			// [4-1. select문]에 해당함
			rset = pstmt.executeQuery();

			// query 결과가 단일행이기 때문에 while문 사용하지 않음
			if (rset.next()) {
				result = new DepartmentVo();

				result.setDepartmentNo(rset.getString("department_No"));
				result.setDepartmentName(rset.getString("department_Name"));
				result.setCategory(rset.getString("category"));
				result.setOpenYn(rset.getString("open_Yn"));
				result.setCapacity(rset.getInt("capacity"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} /* 5. 객체 닫기 */ finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	// =================================================

	public List<DepartmentVo> selectDepartmentList() throws SQLException {

		// result 값 초기화
		List<DepartmentVo> result = null;
		
		// 각 객체별 초기화
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. Driver 로딩. Driver가 없다면 Class Not Found Err 발생
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DBMS와 연결하는 Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "kh", "kh");
			
			// 3. Statement/PreparedStatement 객체 생성 
			String query = "Select departmentNo, departmentName, category, openYn, capacity"
							+ "From tb_department";
			pstmt = conn.prepareStatement(query);
			
			// 4. query문 실행
			// select query문이면 ResultSet-executeQuery()
			// insert/update/delete문이면 int-executeUpdate()
			ResultSet rset = pstmt.executeQuery();
			
			// 5. ResultSet에서 row(record)=한줄 읽어오기 위해 cursor(포인트)를 이동함
			result = new ArrayList<DepartmentVo>();
			while (rset.next() == true) {
				DepartmentVo vo = new DepartmentVo();
				vo.setDepartmentNo(rset.getString("department_No"));
				vo.setDepartmentName(rset.getString("department_Name"));
				vo.setCategory(rset.getString("category"));
				vo.setOpenYn(rset.getString("open_Yn"));
				vo.setCapacity(rset.getInt("capacity"));
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			// 1. Driver (ojdbc.jar) 없을 때 발생
			e.printStackTrace();
			
		} catch (SQLException e) {
			// 2. DBMS에 연결 실패하면 발생
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;

	}
}
