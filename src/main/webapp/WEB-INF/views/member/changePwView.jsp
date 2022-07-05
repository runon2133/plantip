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
        	<!-- ChangePw section -->
            <section class="mt-4 py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                        <!-- Mypage Navigation -->
                        <jsp:include page="/WEB-INF/views/common/mypageNav.jsp"/>
                        <!-- Mypage content -->
                        <div class="col-lg-9">
                            <!-- Page content -->
                            <article>
                                <!-- Post header -->
                                <header class="mt-4 mb-4">
                                    <!-- Post title -->
                                    <h5 class="fw-bolder mb-2">비밀번호 변경</h5>
                                    <!-- Post meta content-->
                                    <div class="text-muted mb-2 fs-7">※ 현재 비밀번호를 확인해야 비밀번호 수정이 가능합니다.</div>
                                </header>
                                <!-- Post content-->
                                <section class="mt-5 mb-5">
                                     <div class="row gx-5 justify-content-center">
			                            <div class="col-lg-8 col-xl-5">
			                                <form action="/updatePw.do" id="contactForm" name="form" method="post" autocomplete="off">
			                                	<!-- PW input -->
			                                    <h3 class="input-title">현재 비밀번호</h3>
			                                    <div class="mb-4">
			                                    	<input class="member-form" id="memberPw" type="password" placeholder="현재 비밀번호를 입력하세요" maxlength="16" />
			                                        <div class="input-chk" id="memberPwChk"></div>
			                                    </div>
			                                    <!-- New PW input -->
			                                    <h3 class="input-title">새 비밀번호</h3>
			                                    <div class="mb-4">
			                                    	<input class="member-form" id="newPw" name="newPw" type="password" placeholder="영문, 숫자, 특수문자 조합 8~16자" maxlength="16" />
			                                        <div class="input-chk" id="newPwChk"></div>
			                                    </div>
			                                    <!-- New PW Check input -->
			                                    <h3 class="input-title">새 비밀번호 확인</h3>
			                                    <div class="mb-4">
			                                    	<input class="member-form" id="newPwConfirm" type="password" placeholder="새 비밀번호 재입력" maxlength="16" />
			                                        <div class="input-chk" id="newPwConfirmChk"></div>
			                                    </div>
			                                    <!-- Submit Button -->
			                                    <div class="d-grid"><button class="btn btn-primary btn-lg disabled" id="updatePwSubmit" type="submit">저장</button></div>
			                                </form>
			                            </div>
			                        </div>
                                </section>
                            </article>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    <!-- script -->
		<script type="text/javascript" src="/js/member/changePwView.js"></script>
    </body>
</html>