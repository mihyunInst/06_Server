package edu.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.member.model.service.MemberService;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;


@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 인코딩 처리
			req.setCharacterEncoding("UTF-8");
			
			// 입력된 값(파라미터) 얻어오기
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			// 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 로그인 서비스 호출 후 결과 반환 받기
			Member loginMember = service.login(inputId, inputPw);
			
			System.out.println("loginMember ::" + loginMember);
			// Session 객체 생성
			HttpSession session = req.getSession();
			
			if(loginMember != null){ // 로그인 성공
				
				// session에 로그인한 회원 정보 추가
				session.setAttribute("loginMember", loginMember);
				
				// session 만료 시간 지정(초 단위로 지정)
				session.setMaxInactiveInterval(60 * 60); // 테스트 후 1시간으로 변경
				
				
				TodoService todoService = new TodoService();
			
				List<Todo> todoList = todoService.selectAll(loginMember.getMemberNo());
				
				session.setAttribute("todoList", todoList);
				
				
				resp.sendRedirect("/"); // 메인 페이지 재요청
				
				
			}else { // 실패
				
				// 로그인 실패 메시지를 session에 추가
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				
				// 현재 요청 이전 페이지로 redirect
				String referer = req.getHeader("referer"); // 리퍼러 -> 각 페이지 방문시 남는 흔적을 말한다.
				/* 
				 * request.getHeader 로 request header 안에 들어있는 데이터를 가져오는데
					Referer 라는 파라미터를 입력해서 Referer 라는 값을 가져옴
				 * */
				
				System.out.println("referer:: " + referer); // http://localhost:8080/  이런식으로 저장되어있음
				
				resp.sendRedirect(referer);
				
			}
			
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
