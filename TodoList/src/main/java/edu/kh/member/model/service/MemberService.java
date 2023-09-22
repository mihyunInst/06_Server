package edu.kh.member.model.service;

import static edu.kh.todo.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.member.model.dto.Member;
import edu.kh.todo.common.JDBCTemplate;
import edu.kh.member.model.dao.MemberDAO;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 로그인
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String inputId, String inputPw) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, inputId, inputPw);
		
		close(conn);
		
		return loginMember;
	}

	/** 회원가입
	 * @param member
	 * @return result
	 */
	public int signup(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signup(conn, member);
		
		if(result > 0) commit(conn);
		else 		rollback(conn);
		
		close(conn);
		
		return result;
	}

}
