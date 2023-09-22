<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>To do 수정하기</title>
	<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

    <main>
		<h1>To do 수정하기</h1>
		
		<form action="/update" method="post">
			<p>제목</p>
			<input name="title" value="${todo.todoTitle}">

			<p style="margin-top: 20px;">메모</p>
			<textarea name="memo" style="resize: none;" cols="20" rows="5">${todo.todoMemo}</textarea>
			
			<input type="hidden" name="todoNo" value="${todo.todoNo}">

			<br>
			<button class="insert-btn" style="background-color: blue;">수정하기</button>
		</form>
	</main>
</body>
</html>