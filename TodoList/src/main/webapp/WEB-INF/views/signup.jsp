<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	
	<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
	<main>
		<h1>회원가입</h1>
		
		<form action="/signup" method="post" class="signup-form" onsubmit="return validate()">
			<p>아이디</p>
			<input type="text" name="inputId" id="inputId">
			<span id="idMsg">영어 대/소문자, 숫자, 특수문자 포함 6~14글자</span>
			
			<p>비밀번호</p>
			<input type="password" name="inputPw" id="inputPw">
			
			<p>비밀번호 확인</p>
			<input type="password" name="inputPw2" id="inputPw2">
			<span id="pwMessage"></span>
			
			<p>닉네임</p>
			<input type="text" name="inputName" id="inputName">
			<span id="nameMessage"></span>
			<br>
			
			<button>가입하기</button>
		</form>
	</main>

	
	<script src="/resources/js/signup.js"></script>
	
	
</body>
</html>