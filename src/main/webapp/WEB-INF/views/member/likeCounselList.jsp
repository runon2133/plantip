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
        	<!-- Page content-->
            <section class="mt-4 py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                    	<!-- Mypage Navigation -->
                        <jsp:include page="/WEB-INF/views/common/mypageNav.jsp"/>
                        <div class="col-lg-9">
                            <article>
                                <!-- Post header -->
                                <header class="mt-4 mb-4">
                                    <!-- Post title -->
                                    <h5 class="fw-bolder mb-2">관심 글</h5>
                                    <!-- Post meta content -->
                                    <div class="text-muted mb-2 fs-7">※ 관심 글을 조회합니다.</div>
                                </header>
                                <!-- Post content-->
                                <section>
					            	<div class="container px-5 mb-5">
							            <table class="table fs-8">
								            <thead>
								            	<tr class="text-center" style="color: rgba(0, 0, 0, 0.55);">
								            		<th class="col-6">제목</th>
								            		<th class="col-2">조회</th>
								            		<th class="col-2">작성자</th>
								            		<th class="col-2">작성일</th>
								            	</tr>
								            </thead>
								            <tbody class="tbody">
								            	<c:choose>
								            		<c:when test="${not empty cld.list }">
								              		  	<c:forEach items="${cld.list }" var="c">
															<tr>
																<td class="counsel-title"><a href="/updateReadCount.do?counselNo=${c.counselNo }&memberId=${sessionScope.m.memberId }">${c.counselTitle }</a> [${c.replyCount }]</td>
																<td class="text-center">${c.counselReadcount }</td>
																<td class="text-center">${c.counselWriter }</td>
																<td class="text-center">${c.counselRegdate }</td>
															</tr>
														</c:forEach>								            		
								            		</c:when>
								            		<c:otherwise>
								            			<tr>
															<td colspan="12" class="text-center">관심 글이 없습니다.</td>
														</tr>
								            		</c:otherwise>
								            	</c:choose>
								            </tbody>
							            </table>
					            	</div>
					            	<div class="container px-5 pb-5 mb-5">
					            		<nav aria-label="Page navigation example">
											${cld.pageNavi }
										</nav>
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
	    <script>
	    	$(function() {
	    		$(".mypage-nav").eq(2).addClass("mypage-nav-active");
	    	});
	    </script>
    </body>
</html>