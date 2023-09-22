package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.student.model.dto.Student;
import edu.kh.jsp.student.model.service.StudentService;

@WebServlet("/jstl/student/selectAll")
public class SelectAllController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 서비스 객체 생성
		StudentService service = new StudentService();
		
		
		try {
			
			// 학생 전체 조회 서비스
			List<Student> stdList = service.selectAll();
			
			// request scope에 stdList를 담아서 JSP로 위임
			req.setAttribute("stdList", stdList);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	
		// 요청 위임
		req.getRequestDispatcher("/WEB-INF/views/student/select.jsp").forward(req, resp);
		
		
	}
}
