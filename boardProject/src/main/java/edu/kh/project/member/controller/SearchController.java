package edu.kh.project.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@WebServlet("/search")
public class SearchController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String query = req.getParameter("query");
			System.out.println("query:" + query);
			
			MemberService service = new MemberService();
			
			List<Member> memberList = service.searchMember(query);
			
			req.setAttribute("memberList", memberList);

			req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
