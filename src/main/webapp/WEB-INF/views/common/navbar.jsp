<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="container px-5">
        	<a class="navbar-brand" href="/"><img src="/assets/img/logo.png" style="width:28px; height:28px">plantip</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                	<!-- 
                    <li class="nav-item"><a class="nav-link" href="index.html">식물도감</a></li>
                     -->
                    <li class="nav-item"><a class="nav-link" href="/counselList.do?reqPage=1">식물상담</a></li>
                    <li class="nav-item dropdown">
                    	<c:choose>
                    		<c:when test="${empty sessionScope.m }">
		                        <a class="nav-link" href="/loginFrm.do" role="button" aria-expanded="false">로그인</a>             			
                    		</c:when>
                    		<c:otherwise>
                    			<a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${sessionScope.m.memberName } 님</a>
		                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
		                            <li><a class="dropdown-item" href="/logout.do">로그아웃</a></li>
		                            <li><a class="dropdown-item" href="/mypageView.do">마이페이지</a></li>
		                        </ul>
                    		</c:otherwise>
                    	</c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>