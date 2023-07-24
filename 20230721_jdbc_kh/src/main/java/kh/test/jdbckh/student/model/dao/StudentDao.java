package kh.test.jdbckh.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kh.test.jdbckh.student.model.vo.StudentVo;

public class StudentDao {
	// ppt 내용 구현

	// DB에서 tb_student 테이블의 전달받은 학번을 통해 학생1명의 상세정보를 읽어옴
	public StudentVo selectOneStudent(String studentNo) {
		StudentVo result = null;
		System.out.println("DAO SelectOneStudent() arg:" + studentNo);
//		String query = "select * from tb_student where student_no = " + "'" + studentNo + "'"; // PK, 단일행으로만 출력됨
//		
//		String query = "select * from tb_student "
//				+ "join tb_department using(department_no)"
//				+ "where student_no = "+"'"+studentNo+"'";
		String query = "select * from tb_student "
				+ "join tb_department using(department_no)"
				+ "where student_no = ?'";
		//위치홀더

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "kh", "kh");

//			if (conn == null) {
//				System.out.println("연결실패");
//			} else {
//				System.out.println("연결성공");
//			}
			pstmt = conn.prepareStatement(query);
			// pstmt 생성된 후 ----  execute 실행하기 전
			// 여기서 ? 위치홀더에 값 넣기
			pstmt.setString(0, query);
			
			
			
			rset = pstmt.executeQuery();
			if (rset.next())   {
				result = new StudentVo();
				// while문 동작필요없음. query결과가 단일행이기 때문임
				result.setAbsenceYn(rset.getString("Absence_Yn"));
				result.setCoachProfessorNo(rset.getString("Coach_Professor_No"));
				result.setDepartmentNo(rset.getString("Department_No"));
				result.setEntranceDate(rset.getDate("Entrance_Date"));
				result.setStudentAddress(rset.getString("Student_Address"));
				result.setStudentName(rset.getString("Student_Name"));
				result.setStudentNo(rset.getString("Student_No"));
				result.setStudentSsn(rset.getString("Student_Ssn"));
				
				result.setDepartmentName(rset.getString("Department_Name"));
				
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
//		System.out.println(result);
		return result;
	}

	public List<StudentVo> selectListStudent() {
		// DB에서 tb_student 테이블의 있는 모든 내용을 읽어서 꺼냄
		List<StudentVo> result = null;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			// 1. driver가 있다면 로딩함. 없다면 Class Not Found에러
//			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName을 기억 / try~catch
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 객체 생성 // dbms와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
			if (conn != null) {
				System.out.println("DB연결 성공!!!!!!!!!!!!");
			} else {
				System.out.println("--------------DB 연결 실패-------");
			}

			// 3. Statement/PreparedStatement 객체 생성 - conn 객체로부터 - query 문을 실어보냄
//			stmt = conn.createStatement();
//			pstmt = conn.prepareStatement("select * from tb_student");
			String query = "select * from tb_student";
			pstmt = conn.prepareStatement(query);

			// 4. query문을 실행해달라고 함 - 그 결과값을 return 받음
			// select query문이면 ResultSet모양
			// insert/update/delete문이면 int 모양
			ResultSet rs = pstmt.executeQuery();

			// 5. ResultSet에서 row(record)=한줄 읽어오기 위해 cursor(포인트)를 이동함
			result = new ArrayList<StudentVo>();
			while (rs.next() == true) {
				// 한줄 row/record를 읽을 준비 완료
//				System.out.println(rs.getString("STUDENT_NAME"));
				StudentVo vo = new StudentVo();

//				vo.setStudentName(rs.getString("student_name"));
				vo.setDepartmentNo(rs.getString("department_no"));
				vo.setStudentName(rs.getString("Student_Name"));
				vo.setAbsenceYn(rs.getString("absence_Yn"));
				vo.setCoachProfessorNo(rs.getString("coach_Professor_No"));
				vo.setStudentAddress(rs.getString("student_Address"));
				vo.setEntranceDate(rs.getDate("entrance_Date"));
				vo.setStudentSsn(rs.getString("student_Ssn"));
				vo.setStudentNo(rs.getString("student_No"));

				result.add(vo);

			}

		} catch (ClassNotFoundException e) {
			// 1. driver (ojdbc.jar) 없음
			e.printStackTrace();
		} catch (SQLException e) {
			// 2. dbms에 connection 실패
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(result);
		return result;
	}

}
// db랑 연동