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
        	<!-- Page Content -->
            <section class="py-5 mt-4">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                    	<!-- Mypage Navigation -->
                        <jsp:include page="/WEB-INF/views/common/mypageNav.jsp"/>
                        <!-- Mypage content -->
                        <div class="col-lg-9">
                            <!-- Post content -->
                            <article>
                                <!-- Post header -->
                                <header class="mt-4 mb-4">
                                    <!-- Post title -->
                                    <h5 class="fw-bolder mb-2">회원 탈퇴</h5>
                                    <!-- Post meta content -->
                                    <div class="text-muted mb-2 fs-7">※ 회원 탈퇴에 앞서 아래 내용을 반드시 확인해 주세요.</div>
                                </header>
                                <!-- Post content-->
                                <section class="mt-5 mb-5">
                                     <div class="row gx-5 justify-content-center">
			                            <div class="col-lg-8 col-xl-5">
				                            <div class="card mb-5 mb-xl-0">
				                                <div class="card-body p-5">
				                                    <div class="small text-uppercase fw-bold mb-4">
				                                        <i class="bi bi-star-fill text-warning"></i>
				                                        Pro
				                                    </div>
				                                    <ul class="list-unstyled mb-4">
				                                        <li class="mb-2">
				                                            <i class="bi bi-check text-primary"></i>
				                                            <strong>5 users</strong>
				                                        </li>
				                                        <li class="mb-2">
				                                            <i class="bi bi-check text-primary"></i>
				                                            5GB storage
				                                        </li>
				                                        <li class="mb-2">
				                                            <i class="bi bi-check text-primary"></i>
				                                            Unlimited public projects
				                                        </li>
				                                        <li class="mb-2">
				                                            <i class="bi bi-check text-primary"></i>
				                                            Community access
				                                        </li>
				                                        <li class="text-muted">
				                                            <i class="bi bi-x"></i>
				                                            Monthly status reports
				                                        </li>
				                                    </ul>   
				                                </div>
				                            </div>
			                                <!-- Check box -->
			                                <div class="form-check text-center mb-4">
									            <label class="form-check-label fs-7" for="same-address">
									            	<input type="checkbox" class="form-check-input" id="same-address">안내를 모두 확인하였으며 탈퇴에 동의합니다.
									            </label>
									        </div>
			                                <!-- Button -->
			                                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
			                                    <a class="btn btn-primary btn-lg px-5" href="#">탈퇴하기</a>
			                                    <a class="btn btn-info btn-lg px-5" href="#">취소하기</a>
			                                </div>
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
		<script type="text/javascript" src="/js/member/withdrawView.js"></script>	    
    </body>
</html>