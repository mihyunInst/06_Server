package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	
	
	// 로그인 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			// 인코딩 처리
			req.setCharacterEncoding("UTF-8");
			
			// 입력된 값(파라미터) 얻어오기
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			
			// 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 로그인 서비스 호출 후 결과 반환 받기
			Member loginMember = service.login(inputEmail, inputPw);
			
			System.out.println("loginMember ::" + loginMember);
			// Session 객체 생성
			HttpSession session = req.getSession();
			
			if(loginMember != null){ // 로그인 성공
				
				// session에 로그인한 회원 정보 추가
				session.setAttribute("loginMember", loginMember);
				
				// session 만료 시간 지정(초 단위로 지정)
				session.setMaxInactiveInterval(60*60); // 테스트 후 1시간으로 변경 60*60
				/*	세션은 지정한 유효시간만큼 접근하지 않게 되면 웹컨테이너에 의해 자동으로 종료. 
				 * --> 세션을 제거해주지 않으면 컨테이너에 세션이 생성만 되어 메모리 부족이 되거나, 메모리 누수로 이어져 서버가 다운
				 * 즉 컴퓨터의 화면보호기와 같인 일정시간 이상 세션에 대해 아무런 행동을 취하지 않게 되면 자동으로 종료. 
				 * 세션이 종료되는 시점에 세션에 저장되어 있는 값들 또한 함께 제거됨.
				 * 만약 세션이 종료된 이후 세션에 접근하려 하면 새로운 세션이 생성됨.
				 * */
				
				// forward  : 요청 처리 후 자체적인 화면이 존재하여 
				//			  이를 이용해서 응답
				
				// redirect : 요청 처리 후 자체적인 화면이 없어서
				//			  화면이 있는 다른 요청을 다시 호출(요청)
				
				
				/* **** 요청 주소(주소창에 적는 주소) 작성 **** */
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
