package edu.kh.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// query string 으로 넘어온 파라미터
			String todoNo = req.getParameter("todoNo");
			
			TodoService service = new TodoService();
			
			int result = service.delete(todoNo);
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			if(result > 0) {
				
				List<Todo> todoList = service.selectAll( member.getMemberNo() );
				session.setAttribute("todoList", todoList);
				
				resp.sendRedirect("/");
				
			} else {
				session.setAttribute("message", "삭제 에러!");
				
				resp.sendRedirect("/");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
