package edu.kh.todo.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.todo.common.JDBCTemplate.*;

import edu.kh.member.model.dto.Member;
import edu.kh.todo.model.dao.TodoDAO;
import edu.kh.todo.model.dto.Todo;

public class TodoService {

	private TodoDAO dao = new TodoDAO();
	
	/** 전체 조회하기
	 * @param memberNo
	 * @return todoList
	 * @throws Exception
	 */
	public List<Todo> selectAll(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectAll(conn, memberNo);
		
		close(conn);
		
		return todoList;
	}

	/** 등록하기
	 * @param title
	 * @param memo
	 * @param memberNo 
	 * @return
	 */
	public int insert(String title, String memo, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insert(conn, title, memo, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	public int delete(String todoNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.delete(conn, todoNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;	
	}

	public Todo selectOne(String todoNo, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		Todo todo = dao.selectOne(conn, todoNo, memberNo);
		
		close(conn);
		
		return todo;
		
	}

	public int update(String title, String memo, int memberNo, String todoNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.update(conn, title, memo, memberNo, todoNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

}
