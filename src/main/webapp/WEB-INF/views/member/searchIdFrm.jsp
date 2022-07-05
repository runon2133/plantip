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
                            <h5 class="fw-bolder">아이디 찾기</h5>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-4">
                                <form action="/searchId.do" id="contactForm" name="form" method="post" autocomplete="off">
                                    <!-- Email input -->
                                    <h3 class="input-title">이메일</h3>
                                    <div class="form-floating mb-4">
                                        <input class="form-control" id="memberEmail" name="memberEmail" type="email" placeholder="email@example.com" />
                                        <label for="memberEmail">example@email.com</label>
                                        <div class="input-chk" id="memberEmailChk"></div>
                                    </div>
                                    <!-- Submit Button -->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg disabled" id="searchIdSubmit" type="submit">이메일 전송하기</button></div>
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
		<script type="text/javascript" src="/js/member/searchIdFrm.js"></script>
    </body>
</html>