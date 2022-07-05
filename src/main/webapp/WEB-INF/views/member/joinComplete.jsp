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
                            <h5 class="fw-bolder">가입완료</h5>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-4">
                                <div class="card text-center">
	                                <p class="lead fw-normal text-muted fs-7 mt-5 mb-2">가입이 완료되었습니다.</p>
	                                <p class="lead fw-normal text-muted fs-7 mb-2">플랜팁의 서비스를 이용하세요.</p>
	                                <!-- Submit Button -->
                                    <div class="d-grid" style="margin: 2rem;">
                                    	<button class="btn btn-primary btn-lg" id="moveLogin">로그인</button>
                                    </div>         
                                </div>
                            </div>
                        </div>
                    </div>
            	</div>
            </section>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <script>
        	$("#moveLogin").click(function() {
        		location.href = "/loginFrm.do";
        	});
        </script>
    </body>
</html>