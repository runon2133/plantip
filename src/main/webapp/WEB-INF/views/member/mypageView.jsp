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
            <section class="mt-4 py-5">
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
                                    <!-- Post title-->
                                    <h5 class="fw-bolder mb-2">회원 정보</h5>
                                    <!-- Post meta content-->
                                    <div class="text-muted mb-2 fs-7">※ 프로필 이미지, 이름, 이메일 수정 및 회원탈퇴가 가능합니다.</div>
                                </header>
                                <!-- Post content -->
                                <section class="mt-5 mb-5">
                                     <div class="row gx-5 justify-content-center">
			                            <div class="col-lg-8 col-xl-5">
			                                <form action="/updateMember.do" id="contactForm" name="form" method="post" enctype="multipart/form-data" autocomplete="off">
			                                	<!-- Member img -->
			                                	<h3 class="input-title">프로필 이미지</h3>
						                        <div class="img col mt-3 mb-3 mb-xl-2">
						                            <div class="text-center profile" id="image_preview">
						                            	<c:choose>
						                            		<c:when test="${empty sessionScope.m.memberImg }">
								                                <img class="img-fluid rounded-circle mb-3" id="profileImg" src="/assets/img/user.svg">					                            	
						                            		</c:when>
						                            		<c:otherwise>
						                            			<img class="img-fluid rounded-circle mb-3" id="profileImg" src="/resources/upload/member/${sessionScope.m.memberImg }">
						                            		</c:otherwise>
						                            	</c:choose>
						                            </div>
						                            <div class="text-center">
														<label class="btn btn-light btn-sm mb-4" for="profile" style="font-size: 0.85rem;">변경</label>
														<label class="btn btn-light btn-sm mb-4" id="imgDelete" style="font-size: 0.85rem;">삭제</label>
														<input type="file" id="profile" name="profile" accept=".gif, .jpg, .jpeg, .png" style="display: none" />
														<input type="hidden" name="originalImg" id="originalImg" value='${sessionScope.m.memberImg }'>                            
						                            </div>
						                        </div>
			                                    <!-- Name input -->
			                                    <h3 class="input-title">이름</h3>
			                                    <div class="mb-4">
			                                        <input class="member-form" id="memberName" name="memberName" type="text" value="${sessionScope.m.memberName }" placeholder="이름(실명)" maxlength="10" />
			                                        <div class="input-chk" id="memberNameChk"></div>
			                                    </div>
			                                    <!-- ID input -->
			                                   	<h3 class="input-title">아이디</h3>
			                                    <div class="mb-4">
			                                        <input class="member-form readonly" id="memberId" type="text" value="${sessionScope.m.memberId }" readonly="readonly" disabled/>
			                                        <input type="hidden" name="memberId" value="${sessionScope.m.memberId }">
			                                        <input type="hidden" name="memberPw" value="${sessionScope.m.memberPw }">
						                        	<div class="input-chk" id="memberIdChk"></div>
			                                    </div>
			                                    <!-- Email input -->
			                                    <h3 class="input-title">이메일</h3>
			                                    <div class="input-group mb-4">
				                                    <input class="form-control member-form" id="memberEmail" name="memberEmail" type="email" value="${sessionScope.m.memberEmail }" placeholder="email@example.com" style="font-size: 0.9rem">
				                                    <button class="btn btn-primary border-right-radius disabled" id="emailChkbtn" type="button">중복 확인</button>
				                                    <div class="input-chk" id="memberEmailChk"></div>
				                                </div>
			                                    <!-- Submit Button -->
			                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="updateMemberSubmit" type="submit">저장</button></div>
			                                </form>
			                                
			                                <!-- 회원등급이 1(관리자)이 아닌 경우에만 회원 탈퇴 가능 -->
			                                <c:if test="${sessionScope.m.memberLevel ne 1}">
				                                <!-- Modal trigger -->
				                                <div class="mt-4 text-center">
				                                	<a href="#" class="fw-normal text-muted fs-7" data-bs-toggle="modal" data-bs-target="#modal">회원 탈퇴</a>
				                                </div>			                                
			                                </c:if>
			                            </div>
			                        </div>
                                </section>
                            </article>
                        </div>
                    </div>
                </div>
            </section>
			
			<!-- Modal -->
			<div class="modal fade" id="modal" tabindex="-5" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered modal-sm">
					<div class="modal-content">
			    		<div class="modal-header">
			        		<h5 class="modal-title fs-7" id="exampleModalLabel">회원 탈퇴</h5>
			        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      		</div>
			      		<div class="modal-body fs-7 text-center">
			      			<div>탈퇴 이후에는 어떠한 방법으로도 삭제된 회원정보를 복원할 수 없습니다.</div>
			      			<div class="mt-3">탈퇴 하시겠습니까?</div>
			      		</div>
			      		<div class="justify-content-center modal-footer">
			        		<button type="button" class="btn btn-lg btn-info fs-7 w-100" data-bs-dismiss="modal">취소하기</button>
			        		<a href="/deleteMember.do" class="btn btn-lg btn-primary fs-7 w-100">탈퇴하기</a>
			      		</div>
			    	</div>
				</div>
			</div>
        </main>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    <!-- script -->
		<script type="text/javascript" src="/js/member/mypageView.js"></script>
    </body>
</html>