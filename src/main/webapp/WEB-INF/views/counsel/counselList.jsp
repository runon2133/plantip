<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <section class="mt-6 py-5" style="border-bottom: 1px solid rgba(0, 0, 0, 0.05)">
                <div class="container px-5">
                    <section>
                    	<h6 class="counsel-title1 fw-bolder fs-4 mt-5"><span>식물과 나누는 대화</span></h6>
                    	<h6 class="counsel-title2 fw-bolder fs-1 mb-5"><span>식물 상담</span></h6>
                    	<c:choose>
                    		<c:when test="${empty sessionScope.m }">
                    			<a class="btn btn-primary btn-lg" href="/loginFrm.do" style="width: 10rem; border-radius: 50px;">상담하기</a>
                    		</c:when>
                    		<c:otherwise>
		                    	<a class="btn btn-primary btn-lg" href="/counselFrm.do" style="width: 10rem; border-radius: 50px;">상담하기</a>                		
                    		</c:otherwise>
                    	</c:choose>
                    </section>
            	</div>
            </section>
            <section>
            	<div class="container px-5 mb-5">
                	<form class="search-form py-5" action="/searchCounsel.do" method="get">
                 		<div class="search-form-flex">
                 			<input class="search-form-input" type="text" placeholder="검색어를 입력하세요." name="keyword" autocomplete="off" required>
                 			<input type="hidden" name="reqPage" value=1>
                 			<button class="search-form-submit" type="submit">
                 				<img class="submit-img" src="https://huga.s3.ap-northeast-2.amazonaws.com/v3.1/images/plants/search-white-ico%403x.png">
                 			</button>
                 		</div>
                	</form>
		            <table class="table fs-7">
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
										<td colspan="12" class="text-center">작성 글이 없습니다.</td>
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
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </body>
</html>