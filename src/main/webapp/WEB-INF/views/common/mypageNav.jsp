<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Mypage Navigation -->
	<div class="col-lg-3">
		<div class="d-flex align-items-center mt-lg-4 mb-3 bottom-line">
			<div class="ms-3">
				<div class="fs-5 fw-bold">MY PAGE</div>
				<div class="mb-4"></div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-4 mb-4">
			<div class="ms-3">
				<div class="fs-7 fw-bold">계정 정보</div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-3 mb-2">
			<div class="ms-3">
				<div>
					<a class="fs-7 mypage-nav" href="/mypageView.do">회원 정보</a>
				</div>
				<div class="mb-1"></div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-2 mb-3 bottom-line">
			<div class="ms-3">
				<div>
					<a class="fs-7 mypage-nav" href="/changePwView.do">비밀번호 변경</a>
				</div>
				<div class="mb-4"></div>
			</div>
		</div>

		<div class="d-flex align-items-center mt-lg-4 mb-4">
			<div class="ms-3">
				<div class="fs-7 fw-bold">활동 관리</div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-3 mb-2">
			<div class="ms-3">
				<div>
					<a class="fs-7 mypage-nav" href="/likeCounselList.do?reqPage=1">관심 글</a>
				</div>
				<div class="mb-1"></div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-2 mb-2">
			<div class="ms-3">
				<div>
					<a class="fs-7 mypage-nav" href="/writeCounselList.do?reqPage=1">작성 글</a>
				</div>
				<div class="mb-1"></div>
			</div>
		</div>
		<div class="d-flex align-items-center mt-lg-2 mb-3 bottom-line">
			<div class="ms-3">
				<div>
					<a class="fs-7 mypage-nav" href="/writeReplyList.do?reqPage=1">작성 댓글</a>
				</div>
				<div class="mb-4"></div>
			</div>
		</div>
	</div>
</body>
</html>