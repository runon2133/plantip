<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
    	<!-- Head -->
        <jsp:include page="/WEB-INF/views/common/head.jsp"/>
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
        	<!-- Navigation -->
            <jsp:include page="/WEB-INF/views/common/navbar.jsp"/>    
        	<!-- Page content -->
            <section class="mt-6 py-5">
                <div class="container px-4">
                    <div class="rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <h5 class="fw-bolder">로그인</h5>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-4">
                                <form action="/login.do" id="contactForm" name="form" method="post" autocomplete="off">
                                    <!-- ID input -->
                                    <h3 class="input-title">아이디</h3>
                                    <div class="form-floating mb-4">
                                    	<input class="form-control" id="memberId" name="memberId" type="text" placeholder="아이디를 입력하세요" maxlength="16" />
                                        <label for="id">아이디를 입력하세요.</label>
                                        <div class="input-chk" id="memberIdChk"></div>
                                    </div>
                                    <!-- PW input -->
                                    <h3 class="input-title">비밀번호</h3>
                                    <div class="form-floating mb-4">
                                        <input class="form-control" id="memberPw" name="memberPw" type="password" placeholder="비밀번호를 입력하세요"  maxlength="16" />
                                        <label for="pw">비밀번호를 입력하세요.</label>
                                        <div class="input-chk" id="memberPwChk"></div>
                                    </div>
                                    <!-- Submit Button -->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg disabled" id="loginSubmit" type="submit">로그인</button></div>
                                    <div class="d-flex align-items-center justify-content-center mt-3">
	                                    <div class="idpw-search">
	                                        <ul>
	                                        	<li class="idpw-li"><a href="/searchIdFrm.do">아이디 찾기</a>
	                                        	<li><div class="w-px h-3 bg-gray-400"></div>
	                                        	<li class="idpw-li"><a href="/searchPwFrm.do">비밀번호 찾기</a>
	                                        </ul>
	                                    </div>
                                	</div>
                                	<div class="join-member mt-3">
                                		<a href="/joinFrm.do">계정이 없으신가요?</a>
                                	</div>
                                </form>
                            </div>
                        </div>
                    </div>
            	</div>
            </section>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    <!-- script -->
		<script type="text/javascript" src="/js/member/loginFrm.js"></script>
    </body>
</html>