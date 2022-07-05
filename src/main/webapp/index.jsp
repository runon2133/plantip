<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
	    <!-- Swiper -->
	    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
		<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
		<!-- webFont -->
		<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Abel" rel="stylesheet">
		
        <!-- Head -->
        <%@include file="/WEB-INF/views/common/head.jsp" %>
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation-->
        	<%@include file="/WEB-INF/views/common/navbar.jsp" %>
	        <section id="section1">
	        	<header class="masthead">
		            <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
		                <div class="d-flex justify-content-center">
		                    <div class="text-center">
		                    	<h2 class="text-dark-50 fw-bolder mx-auto mt-2 mb-2">식물과의 대화를</h2>
		                        <h2 class="text-dark-50 fw-bolder mx-auto mb-5"><a style="color: #00b564;" class="counsel-move" href="/counselList.do?reqPage=1">시작</a>하세요.</h2>
							    <a href="#section2" class="scroll">scroll<span></span></a>
						      		                        
		                    </div>
		                </div>
		            </div>
		        </header>
		    </section>
		    <section id="section2">
		        <header class="bg-white mt-6 py-6 mb-5">
	                <div class="container px-4">
	                    <div class="row gx-5 align-items-center justify-content-center">
	                        <div class="col-lg-8 col-xl-4 col-xxl-4">
	                            <div class="my-5 text-center text-xl-start">
	                                <h1 class="title fw-bolder text-dark">플랜팁을</h1>
	                                <h1 class="title fw-bolder text-dark">소개합니다.</h1>
	                                <p class="sub-text text-info mt-3 fs-4">plantip : [ plant + tip ]</p>
	                                <p class="sub-text text-dark-50 mt-5 fs-5">플랜팁은 사람, 식물이 가까워질 수 있도록 서로의 문제를 해결하고 '식물에 관한 조언'을 공유하는 공간입니다.</p>
	                            </div>
	                        </div>
	                        <div class="col-xl-8 col-xxl-8 text-center ">
	                        
	                        	<!-- Swiper -->
							    <div class="swiper mySwiper">
									<div class="swiper-wrapper">
										<div class="swiper-slide"><img class="feature" src="/assets/img/1.png"></div>
										<div class="swiper-slide"><img class="feature" src="/assets/img/2.png"></div>
										<div class="swiper-slide"><img class="feature" src="/assets/img/3.png"></div>
										<div class="swiper-slide"><img class="feature" src="/assets/img/4.png"></div>
										<div class="swiper-slide"><img class="feature" src="/assets/img/5.png"></div>
										<div class="swiper-slide"><img class="feature" src="/assets/img/6.png"></div>
									</div>
							    </div>
	                        
	                        </div>
	                    </div>
	                </div>
	            </header>
	        </section>
        </main>
        <!-- Footer-->
        <%@include file="/WEB-INF/views/common/footer.jsp" %>
        <!-- Initialize Swiper -->
	    <script>
			var swiper = new Swiper(".mySwiper", {
				slidesPerView: 2,
				centeredSlides: true,
				spaceBetween: 30,
				grabCursor: true,
			});
	    </script> 
    </body>
</html>