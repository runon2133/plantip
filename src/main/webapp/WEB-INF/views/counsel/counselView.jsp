<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
    	<!-- Swiper JS -->
	    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

        <!-- Head -->
        <jsp:include page="/WEB-INF/views/common/head.jsp"/>
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation -->
            <jsp:include page="/WEB-INF/views/common/navbar.jsp"/>     
        	<!-- Page content -->
            <section class="mt-6 py-5">
            	<div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-6">
                            <!-- Counsel section -->
                            <article>
                                <header class="pb-1 mb-2" style="border-bottom: 1px solid rgb(0, 0, 0, 0.1);">
                                    <!-- 상담 글 제목 -->
	                                <div class="fw-bolder pb-1">${cvd.counsel.counselTitle }</div>
                                    <!-- 프로필 이미지 -->    
                                    <div class="d-flex mb-4 mt-4">
		                                <div class="flex-shrink-0">
		                                    <c:choose>
			                            		<c:when test="${empty m.memberImg }">
					                                <img class="img-fluid rounded-circle" style="width: 45px; height: 45px;" id="profileImg" src="/assets/img/user.svg">
			                            		</c:when>
			                            		<c:otherwise>
			                            			<img class="img-fluid rounded-circle" style="width: 45px; height: 45px;" id="profileImg" src="/resources/upload/member/${m.memberImg }">
			                            		</c:otherwise>
			                            	</c:choose>
		                                </div>
		                                <div class="ms-3">
		                                    <!-- 작성자 -->
		                                    <div class="fs-7 mb-1 fw-bolder">${m.memberId }</div>
		                                	<!-- 등록일, 조회수 -->
		                                    <div class="fs-7">${cvd.counsel.counselRegdate } · 조회 ${cvd.counsel.counselReadcount }
			                                    <c:if test="${cvd.counsel.counselWriter eq sessionScope.m.memberId }">
			                                    	<a href="javascript:void(0)" onclick="updateCounselFrm('${cvd.counsel.counselNo }');" class="style-a fs-7">· 수정</a>
			                                    	<a href="javascript:void(0)" onclick="deleteCounsel('${cvd.counsel.counselNo }');" class="style-a fs-7">· 삭제</a>
			                                    </c:if>
		                                    </div>
		                                </div>
	                                </div>
                                </header>
                                <!-- 상담 글 사진 -->
                                <c:if test="${not empty cvd.counselImgList }">
                                	<figure class="my-4">
										<!-- Swiper -->
										<div class="swiper mySwiper">
							        		<div class="swiper-wrapper">                             		
                                			<c:forEach items="${cvd.counselImgList }" var="img">
	                                			<div class="swiper-slide">
	                                				<img class="img-fluid" style="width: 25rem !important; height: 25rem !important;" src="/resources/upload/counsel/${img.filename }">
	                                			</div>
                                			</c:forEach>
									      	</div>
									      	<div class="swiper-pagination"></div>
									    </div>
                                	</figure>
                                </c:if>
                                <!-- 상담 글 내용 -->
                                <section class="my-4">
                                    <p class="fs-7">${cvd.counsel.counselContentBr }</p>
                                </section>
                                <!-- 공감 수, 댓글 수 -->
                                <section class="d-flex py-3" style="border-top: 1px solid rgb(0, 0, 0, 0.1);">
	                               	<div class="flex-shrink-0">
	                               		<span class="counsel-like">
                               				<c:choose>
                               					<c:when test="${counselLike eq 0 }">
                               						<img class="mb-1 img-1" src="/assets/img/like.png" style="width: 22px !important;">
				                               		<img class="mb-1 img-2" src="/assets/img/like-up.png" style="width: 22px !important;">
				                               		<span class="text-muted fs-7"> 공감</span>
                               					</c:when>
                               					<c:when test="${counselLike eq 1 }">
                               						<img class="mb-1 img-3" src="/assets/img/like-up.png" style="width: 22px !important;">
                               						<span class="fs-7" style="color: #00b564;"> 공감</span>	
                               					</c:when>
                               				</c:choose>
	                               		</span>
	                               		<input type="hidden" value="${cvd.counsel.counselNo }">
	                               		<input type="hidden" value="${sessionScope.m.memberId }">
	                               		<input type="hidden" value="${counselLike }">
		                               	<span class="text-muted fs-7"> ${likeCnt }</span>                               		
	                               	</div>
	                               	<div class="ms-3">
		                                <img src="/assets/img/comment.png" style="width: 22px !important;"><span class="text-muted fs-7"> 댓글 ${replyCnt }</span>                                	                               	
	                               	</div>
                                </section>
                            </article>
                            <!-- Reply section -->
                            <section>
                                <div class="card bg-light">
                                    <div class="card-body" id="replySection">
                                        <!-- 댓글 등록 -->
	                                    <form action="/insertReply.do" method="post" class="input-group mb-4" autocomplete="off">
	                                    	<c:choose>
	                                    		<c:when test="${empty sessionScope.m }">
		                                    		<textarea class="form-control fs-7 moveLogin" rows="3" placeholder="댓글을 남겨보세요."></textarea>	                                    		
	                                    		</c:when>
	                                    		<c:otherwise>
			                                    	<textarea class="form-control fs-7" rows="3" id="replyContent" name="replyContent" placeholder="댓글을 남겨보세요." maxlength="500"></textarea>	                                    		
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                    	<input type="hidden" name="replyLevel" value="1">
	                                    	<input type="hidden" name="counselNo" value="${cvd.counsel.counselNo }">
	                                    	<input type="hidden" name="replyNoRef" value="0">
		                                    <button class="btn btn-primary border-right-radius fs-7 disabled" id="replyBtn" type="submit">등록</button>
		                                </form>                                        
                                        <!-- 댓글 리스트 -->
                                        <c:forEach items="${cvd.replyList }" var="reply">
                                        	<!-- 댓글 -->
                                        	<c:if test="${reply.replyLevel eq 1 }">
		                                        <div class="d-flex mb-4">
		                                        	<c:choose>
			                                        	<c:when test="${reply.deleteChk eq 1 }">
			                                        		<div class="flex-shrink-0">
			                                        			<img class="rounded-circle" style="width: 45px; height: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/assets/img/user.svg">
			                                        		</div>
			                                        		<div class="ms-3 row">
			                                        			<div class="fs-7 replyContentBr align-self-center" style="padding: 0; color: #717171;">삭제된 댓글입니다.</div>
			                                        		</div>
			                                        	</c:when>
		                                        		<c:otherwise>
				                                            <div class="flex-shrink-0">
				                                            	<c:choose>
								                            		<c:when test="${empty reply.memberImg }">
										                                <img class="rounded-circle" style="width: 45px; height: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/assets/img/user.svg">
								                            		</c:when>
								                            		<c:otherwise>
								                            			<img class="rounded-circle" style="width: 45px; height: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/resources/upload/member/${reply.memberImg }">
								                            		</c:otherwise>
								                            	</c:choose>
				                                            </div>
				                                            <div class="ms-3">
				                                            	<c:choose>
				                                            		<c:when test="${empty reply.replyWriter }">
					                                            		<div class="fs-7 mb-1" style="color: #717171;">탈퇴 회원</div>				                                            		
				                                            		</c:when>
				                                            		<c:otherwise>
						                                                <div class="fs-7 fw-bolder mb-1">${reply.replyWriter }</div>			                                            		
				                                            		</c:otherwise>
				                                            	</c:choose>
				                                                <div>
				                                                	<!-- 댓글 수정 -->
					                                                <div class="fs-7 mb-2 replyContentBr">${reply.replyContentBr }</div>
					                                                <form action="/updateReply.do" method="post" style="display: none;" class="input-group mb-2 updateReplyForm" autocomplete="off">
					                                                	<textarea class="form-control fs-7 updateReplyContent" style="width: 20.42vw;" rows="3" name="replyContent" placeholder="댓글을 남겨보세요." maxlength="500"></textarea>
								                                    	<input type="hidden" name="replyNo" value="${reply.replyNo }">
				                                    					<input type="hidden" name="counselNo" value="${cvd.counsel.counselNo }">
								                                    	<button class='btn btn-secondary fs-7 updateReplyCancel' type='reset'>취소</button>
				                                    					<button class="btn btn-primary border-right-radius fs-7 updateReplyBtn" type="submit">등록</button>                                              
				                                                	</form>
				                                                </div>
				                                                <!-- 수정, 삭제, 답글달기 -->
				                                                <div class="fs-9 mb-2 text-muted">${reply.replyRegdate }
				                                                	<!-- · <img class="mb-1" src="/assets/img/like.png" style="width: 17px !important;"><span> 공감 0</span> -->
				                                                	<c:if test="${not empty sessionScope.m }">			                                                	
				                                                		<c:if test="${reply.replyWriter eq sessionScope.m.memberId }">
				                                                			<a class="style-a modify-return" href="javascript:void(0)" onclick="modifyReply(this, '${reply.replyContentBr }');">· 수정</a>
				                                                			<a class="style-a delete-return" href="javascript:void(0)" onclick="deleteReply(this, '${reply.replyNo }', '${reply.counselNo }');">· 삭제</a>		                                                		
				                                                		</c:if>
						                                                	<a class="style-a reReply" href="javascript:void(0)">· 답글달기</a>
				                                                	</c:if>
				                                                	<!-- 답글 등록 -->
				                                                	<div style="display: none;">
						                                        		<form action="/insertReply.do" method="post" class="input-group mt-3 mb-2 reReplyForm" autocomplete="off">
									                                    	<textarea class="form-control border-right-radius fs-7 reReplyTextarea reReplyContent" style='width: 20.42vw' rows="3" name="replyContent" placeholder="댓글을 남겨보세요." maxlength="500"></textarea>
									                                    	<input type="hidden" name="replyLevel" value="2">
									                                    	<input type="hidden" name="counselNo" value="${reply.counselNo }">
									                                    	<input type="hidden" name="replyNoRef" value="${reply.replyNo }">
									                                    	<input type="hidden" name="replyTo" value="${reply.replyWriter }">
									                                    	<button class='btn btn-secondary fs-7 reReplyCancel' type='reset'>취소</button>
					                                    					<button class="btn btn-primary border-right-radius fs-7 reReplyBtn disabled" type="submit">등록</button>
										                                </form>
						                                        	</div>
				                                                </div>
				                                                <!-- 답글 리스트 -->
		                                        				<c:forEach items="${cvd.replyList }" var="reReply">
		                                        					<!-- 답글 -->
		                                        					<c:if test="${(reReply.replyLevel eq 2) && (reply.replyNo eq reReply.replyNoRef) }">
		                                        						<div class="d-flex mt-4">
		                                        							<c:choose>
		                                        								<c:when test="${reReply.deleteChk eq 1 }">
		                                        									<div class="flex-shrink-0">
									                                        			<img class="rounded-circle" style="width: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/assets/img/user.svg">
									                                        		</div>
									                                        		<div class="ms-3 row">
									                                        			<div class="fs-7 replyContentBr align-self-center" style="padding: 0; color: #717171;">삭제된 댓글입니다.</div>
									                                        		</div>
		                                        								</c:when>
		                                        								<c:otherwise>
								                                                    <div class="flex-shrink-0">
								                                                    	<c:choose>
														                            		<c:when test="${empty reReply.memberImg }">
																                                <img class="rounded-circle" style="width: 45px; height: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/assets/img/user.svg">
														                            		</c:when>
														                            		<c:otherwise>
														                            			<img class="rounded-circle" style="width: 45px; height: 45px; border: 1px solid #f8f8f8; background-color: #fff;" src="/resources/upload/member/${reReply.memberImg }">
														                            		</c:otherwise>
														                            	</c:choose>
								                                                    </div>
								                                                    <div class="ms-3">
								                                                    	<div class="fs-7 fw-bolder mb-1">${reReply.replyWriter }</div>
								                                                    	<div>
										                                                	<!-- 답글 수정 -->
											                                                <div class="fs-7 mb-2 replyContentBr">
											                                                	<div class="fs-7" style="color: #00b564;">@${reReply.replyTo }</div>
											                                                	${reReply.replyContentBr }
											                                                </div>
											                                                <form action="/updateReply.do" method="post" style="display: none;" class="input-group mb-2 updateReplyForm" autocomplete="off">
											                                                	<textarea class="form-control fs-7 updateReplyContent" style="width: 17.2vw;" rows="3" name="replyContent" placeholder="댓글을 남겨보세요." maxlength="500"></textarea>
														                                    	<input type="hidden" name="replyNo" value="${reReply.replyNo }">
										                                    					<input type="hidden" name="counselNo" value="${cvd.counsel.counselNo }">
														                                    	<button class='btn btn-secondary fs-7 updateReplyCancel' type='reset'>취소</button>
										                                    					<button class="btn btn-primary border-right-radius fs-7 updateReplyBtn" type="submit">등록</button>                                              
										                                                	</form>
										                                                </div>
										                                                <!-- 수정, 삭제, 답글달기 -->
										                                                <div class="fs-9 mb-2 text-muted">${reReply.replyRegdate }
										                                                	<!-- · <img class="mb-1" src="/assets/img/like.png" style="width: 17px !important;"><span> 공감 0</span> -->
										                                                	<c:if test="${not empty sessionScope.m }">			                                                	
										                                                		<c:if test="${reReply.replyWriter eq sessionScope.m.memberId }">
										                                                			<a class="style-a modify-return" href="javascript:void(0)" onclick="modifyReply(this, '${reReply.replyContentBr }');">· 수정</a>
										                                                			<a class="style-a delete-return" href="javascript:void(0)" onclick="deleteReply(this, '${reReply.replyNo }', '${reReply.counselNo }');">· 삭제</a>		                                                		
										                                                		</c:if>
												                                                	<a class="style-a reReply" href="javascript:void(0)">· 답글달기</a>
										                                                	</c:if>
										                                                	<!-- 답글 등록 -->
										                                                	<div style="display: none;">
												                                        		<form action="/insertReply.do" method="post" class="input-group mt-3 mb-2 reReplyForm" autocomplete="off">
															                                    	<textarea class="form-control border-right-radius fs-7 reReplyTextarea reReplyContent" style='width: 17.2vw' rows="3" name="replyContent" placeholder="댓글을 남겨보세요." maxlength="500"></textarea>
															                                    	<input type="hidden" name="replyLevel" value="2">
															                                    	<input type="hidden" name="counselNo" value="${reReply.counselNo }">
															                                    	<input type="hidden" name="replyNoRef" value="${reply.replyNo }">
															                                    	<input type="hidden" name="replyTo" value="${reReply.replyWriter }">
															                                    	<button class='btn btn-secondary fs-7 reReplyCancel' type='reset'>취소</button>
											                                    					<button class="btn btn-primary border-right-radius fs-7 reReplyBtn disabled" type="submit">등록</button>
																                                </form>
												                                        	</div>
										                                                </div>
								                                                    </div>
		                                        								</c:otherwise>
		                                        							</c:choose>
						                                                </div>
		                                        					</c:if><!-- 답글 if -->
		                                        				</c:forEach><!-- 답글 forEach -->
				                                        	</div>
		                                        		</c:otherwise>
		                                        	</c:choose>
		                                        </div> 
                                        	</c:if><!-- 댓글 if -->
                                        </c:forEach><!-- 댓글 forEach -->
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>           	
            </section>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- script -->
	    <script type="text/javascript" src="/js/counsel/counselView.js"></script>
    </body>
</html>