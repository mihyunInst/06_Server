package edu.kh.todo.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.todo.common.JDBCTemplate.*;

import edu.kh.member.model.dao.MemberDAO;
import edu.kh.member.model.dto.Member;
import edu.kh.todo.model.dto.Todo;

public class TodoDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public TodoDAO() {
		try {
			prop = new Properties();
			
			String filePath 
				= MemberDAO.class.getResource("/edu/kh/todo/sql/todo-sql.xml").getPath(); 
				
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<Todo> selectAll(Connection conn, int memberNo) throws Exception{
		List<Todo> todoList = new ArrayList<Todo>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Todo todo = new Todo();
				
				todo.setTodoNo( rs.getInt("TODO_NO"));
				todo.setTodoTitle( rs.getString("TODO_TITLE"));
				todo.setTodoMemo( rs.getString("TODO_MEMO"));
				todo.setTodoDate( rs.getString("TODO_DATE"));
				
				todoList.add(todo);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return todoList;
	}


	public int insert(Connection conn, String title, String memo, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, memo);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);	
		}
		
		return result;
	}


	public int delete(Connection conn, String todoNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);	
		}
		
		return result;
	}


	public Todo selectOne(Connection conn, String todoNo, int memberNo) throws Exception {
		
		Todo todo = null;
		
		try {
			
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, todoNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todo = new Todo();
				todo.setTodoNo( rs.getInt("TODO_NO"));
				todo.setTodoTitle( rs.getString("TODO_TITLE"));
				todo.setTodoMemo( rs.getString("TODO_MEMO"));
				todo.setTodoDate( rs.getString("TODO_DATE"));
			}
			 
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return todo;
	}


	public int update(Connection conn, String title, String memo, int memberNo, String todoNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, memo);
			pstmt.setString(3, todoNo);
			pstmt.setInt(4, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);	
		}
		
		return result;
	}

}