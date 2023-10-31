package kh.test.jdbckh.department.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.test.jdbckh.department.model.service.DepartmentService;
import kh.test.jdbckh.department.model.vo.DepartmentVo;
import kh.test.jdbckh.student.model.service.StudentService;
import kh.test.jdbckh.student.model.vo.StudentVo;

@WebServlet("/dept/get")
public class DepartmentGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 전달받은 parameter 읽어내기
		String departmentNo = request.getParameter("departmentNo");
		System.out.println(departmentNo);
		// 2. 전달받은 데이터를 활용해 DB학생 상세 정보 가져오기
		DepartmentService service = new DepartmentService();
//		DepartmentVo vo = service.selectOne(departmentNo);
		// 3. DB로부터 전달받은 데이터를 JSP에 전달함.
//		request.setAttribute("dvo", vo);
		// 4. JSP 파일 forward로 열기
		request.getRequestDispatcher("/WEB-INF/view/dept/get.jsp").forward(request, response);
	}

//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//	}

}
