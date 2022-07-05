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
                    <div class="py-5 px-4 mb-5 gx-5 row justify-content-center">
                        <div class="col-lg-6">
	                        <div class="text-center mb-5">
	                            <h5 class="fw-bolder">상담하기</h5>
	                        </div>
	                        <div>
	                            <div>
	                                <form action="/insertCounsel.do" method="post" enctype="multipart/form-data" autocomplete="off">
	                                	<!-- 상담 글 사진 -->
	                                	<h3 class="input-title">사진 올리기 <span class="input-span">(</span><span class="input-span" id="imgCnt">0</span><span class="input-span">/3)</span></h3>
				                        <div class="col mt-3 mb-4">
				                            <div class="img-section row">			                            	
				                            	<div class="filebox clearfix">
												    <div class="inputFile">
												    	<label for="addImgs"><img src="/assets/img/img-select.png" style="margin-right: 10px; margin-bottom: 5px; cursor: pointer;"></label>
												        <input type="file" id="addImgs" name="addImgs" class="upload-hidden" accept=".gif, .jpg, .jpeg, .png" style="display: none" multiple>
												    </div>
												    <div class="preview" id="preview">
												    	
												    </div>
												</div>
				                            </div>
				                        </div>
	                                    <!-- 상담 제목 -->
	                                    <h3 class="input-title">상담 제목 <span class="input-span">(</span><span class="input-span" id="titleCnt">0</span><span class="input-span">/30)</span></h3>
	                                    <div class="mb-4">
	                                        <input class="member-form" id="counselTitle" name="counselTitle" type="text" placeholder="상담 제목을 입력하세요." maxlength="30" />
	                                        <input type="hidden" name="counselWriter" value="${sessionScope.m.memberId }">
	                                    </div>
	                                    <!-- 상담 내용 -->
	                                    <h3 class="input-title">상담 내용 <span class="input-span">(</span><span class="input-span" id="contentCnt">0</span><span class="input-span">/500)</span></h3>
	                                    <div class="mb-4">
	                                        <textarea class="member-form" id="counselContent" name="counselContent" placeholder="상담 내용을 입력하세요." rows="8"  style="height: 250px;" maxlength="500"></textarea>
	                                    </div>
	                                    <!-- Button -->
		                                <div class="d-grid gap-4 d-sm-flex justify-content-sm-center">
		                                    <a class="btn btn-info btn-lg px-5 col-lg-5" href="/counselList.do?reqPage=1">취소</a>
		                                    <button class="btn btn-primary btn-lg px-5 col-lg-5 disabled" id="counselSubmit" type="submit">저장</button>
		                                </div>
	                                </form>
	                            </div>
	                        </div>
                        
                        </div>
                    </div>
            	</div>
            </section>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- script -->
		<script type="text/javascript" src="/js/counsel/counselFrm.js"></script>
    </body>
</html>