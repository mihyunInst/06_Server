<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Servlet/JSP 내장 객체와 범위</title>
</head>
<body>
    <h1>Servlet/JSP 내장 객체와 범위</h1>

    <pre>
        Servlet/JSP에는 4종류의 범위를 갖는 내장 객체가 존재
        -> 각 종류마다 영향을 끼치는 범위가 다름
        -> 개발자가 객체를 별도로 생성하지 않아도 JSP에서 바로 사용할 수 있는 객체 ( JSP를 서블릿으로 변환할 때 자동으로 객체가 생성됨 )


        1. page (pageContext) : 현재 페이지 (한 페이지)
            -> 현재 JSP에서만 사용 가능

        2. request : 요청 받은 페이지(Servlet/JSP)와
                    이를 위임 받은 페이지(Servlet/JSP)에서 사용 가능
                    (최소 2페이지 이상)

        3. session : 현재 사이트에 접속한 브라우저당 1개씩 생성
                    브라우저가 종료되거나
                    session 만료될 때 까지 유효

        4. application : 하나의 웹 애플리케이션마다 1개씩 생성되는 객체
                    서버 켜질 때 생성되어
                    종료될 때 없어짐


        *****************************
        *** 내장 객체의 우선 순위 ***
        *****************************

        page > request > session > application


        EL로 각 scope에 있는 값을 얻어올 수 있다!

        방법 1) \${ 범위이름Scope.속성명 }
                    pageScope
                    requestScope
                    sessionScope
                    applicationScope

        방법 2) \${ 속성명 }
            -> 각 범위에 세팅된 속성 중 
                속성명이 일치하는 경우
                우선순위가 높은 scope의 속성 값이 얻어와짐
                
                
        <%-- 
       	** JSP 내장객체는 JSP 내에서 선언하지 않고도 어떻게 사용 가능한가?**

		- 우리가 작성한 JSP 코드는 자바 서블릿 코드로 변환되면서 _jspService() 메서드 안으로 들어간다.
		
		- 따라서, _jspService() 매개변수인 request, response와 
		
		- 지역변수로 선언된 pageContext, session, application, page, config, out 등은 모두 내장객체가 되는 것. 
		--%>
    </pre>


    <ul>
        <li>
            page scope <br>

            <%
                // pageContext : page scope 객체
                pageContext.setAttribute("pageValue", 5);

                pageContext.setAttribute("str", "page 범위에 세팅된 문자열");
            %>

            pageValue : ${pageValue} <%-- 5 --%>
            <br>
            pageValue : ${pageScope.pageValue} <%-- 5 --%>
        </li>

        <br>

        <li>
            request scope <br>
            reqValue : ${requestScope.reqValue}
        </li>

        <br>

        <li>
            session scope <br>
            sessionValue : ${sessionScope.sessionValue} <%-- 999 --%>
        </li>

        <br>

        <li>
            application scope <br>
            appValue : ${applicationScope.appValue} <%-- 10000 --%>
        </li>
        
    </ul>

    <hr>

    <h3>우선 순위 확인</h3>

    <h4>각각의 범위에 세팅된 str</h4>
    ${pageScope.str} <br>
    ${requestScope.str} <br>
    ${sessionScope.str} <br>
    ${applicationScope.str} <br>

    <h4>\${str} 확인 : ${str}</h4>
    <%-- 우선순위에 의해 가장 우선순위가 높은 page가 나온다. --%>

    <hr>

    <h3>
        <a href="scopeCheck">내장 객체별 생명 주기 확인</a>
    </h3>

</body>
</html>