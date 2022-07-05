
//비로그인시 로그인 페이지로 이동
$(".moveLogin").click(function() {
	alert("로그인이 필요합니다.");
	location.href = "/loginFrm.do";
});

//답글달기
$(".reReply").click(function() {
	$(".reReplyBtn").addClass("disabled");
	
	//전체 댓글달기 폼 숨김
	$(".reReplyForm").parent().hide();
	//댓글달기 나타냄
	$(".reReply").show();
	//기존 댓글달기 내용 리셋
	$(".reReplyTextarea").val('');
	
	//전체 수정 폼 숨김
	$(".updateReplyForm").hide();
	//전체 수정 전 내용으로 되돌림
	$(".updateReplyForm").prev().show();
	//수정, 삭제, 답글달기 나타냄
	$(".modify-return").show();
	$(".delete-return").show();
	$(".reReply").show();
	
	//해당 댓글달기 인덱스
	var idx = $(".reReply").index(this);
	//해당 댓글달기 숨김
	$(this).hide();
	//댓글달기 폼 나타냄
	$(".reReplyForm").eq(idx).parent().css("display", "block");
});

//답글달기 취소
$(".reReplyCancel").click(function() {
	//해당 댓글달기 취소 인덱스
	var idx = $(".reReplyCancel").index(this);
	//댓글달기 폼 숨김
	$(".reReplyForm").eq(idx).parent().css("display", "none");
	//댓글달기 나타냄
	$(".reReply").eq(idx).show();
});

//수정
function modifyReply(obj, replyContentBr) {
	//수정 등록 활성화
	$(".updateReplyBtn").removeClass("disabled");
	//<br> 표시 줄바꿈으로 변경
	replyContent = replyContentBr.replaceAll("<br>", "\r\n");
	//수정 선택 시 기존내용 불러옴
	$(obj).parent().prev().children().eq(1).children().eq(0).val(replyContent);
	
	//전체 댓글달기 폼 숨김
	$(".reReplyForm").parent().hide();
	//댓글달기 나타냄
	$(".reReply").show();
	//기존 댓글달기 내용 리셋
	$(".reReplyTextarea").val('');
	//전체 수정 폼 숨김
	$(".updateReplyForm").hide();
	//전체 수정 전 내용으로 되돌림
	$(".updateReplyForm").prev().show();
	//수정, 삭제, 답글달기 나타냄
	$(".modify-return").show();
	$(".delete-return").show();
	$(".reReply").show();
	
	//textarea를 화면에 표현
	$(obj).parent().prev().children().eq(1).show();
	//기존 본문 내용을 숨김
	$(obj).parent().prev().children().eq(0).hide();
	//수정 숨기기
	$(obj).hide();
	//삭제 숨기기
	$(obj).next().hide();
	//답글달기 숨기기
	$(obj).next().next().hide();
}

//수정 취소
$(".updateReplyCancel").click(function() {
	//해당 댓글수정 취소 인덱스
	var idx = $(".updateReplyCancel").index(this);
	//댓글수정 폼 숨김
	$(".updateReplyForm").eq(idx).css("display", "none");
	//기존 댓글 나타냄
	$(".updateReplyForm").eq(idx).prev().show();
	//수정, 삭제, 답글달기 나타냄
	$(".updateReplyForm").eq(idx).parent().next().children().eq(0).show();
	$(".updateReplyForm").eq(idx).parent().next().children().eq(1).show();
	$(".updateReplyForm").eq(idx).parent().next().children().eq(2).show();
	
});

//댓글 삭제
function deleteReply(obj, replyNo, counselNo) {
	if(confirm("댓글을 삭제하시겠습니까?")) {
		location.href = "/deleteReply.do?replyNo=" + replyNo + "&counselNo=" + counselNo;
	}
}

//상담글 삭제
function deleteCounsel(counselNo) {
	if(confirm("해당 상담글을 삭제하시겠습니까?")) {
		location.href="/deleteCounsel.do?counselNo=" + counselNo;	    			
	}
}

//상담글 수정 페이지 이동
function updateCounselFrm(counselNo) {
	location.href="/updateCounselFrm.do?counselNo=" + counselNo;
}


$(function() {
	//Swiper
	var swiper = new Swiper(".mySwiper", {
		pagination: {
	          el: ".swiper-pagination",
	    },
	});
	
	//댓글 등록 유효성 체크
	var replyChk = false;
	
	//댓글 등록 유효성검사
	$("#replyContent").keyup(function() {
        var replyContent = $(this).val();
      	//내용이 비어있지 않은 경우
        if(replyContent != '') {
            replyChk = true;
            replyAbled();
        //내용이 비어있는 경우
        } else {
            replyChk = false;
            replyDisabled();
        }	
	});
	
	//댓글 등록 버튼 활성화
	function replyAbled() {
		if(replyChk == true) {
			$("#replyBtn").removeClass("disabled");
		}
	}
	
	//댓글 등록 버튼 비활성화
	function replyDisabled() {
		$("#replyBtn").addClass("disabled");
	}
	
	
	//댓글 수정 유효성 체크
	var updateReplyChk = true;
	
	//댓글 수정 유효성검사
	$(".updateReplyContent").keyup(function() {
        var updateReplyContent = $(this).val();
      	//내용이 비어있지 않은 경우
        if(updateReplyContent != '') {
        	updateReplyChk = true;
            updateReplyAbled();
        //내용이 비어있는 경우
        } else {
        	updateReplyChk = false;
        	updateReplyDisabled();
        }	
	});
	
	//댓글 수정 등록 버튼 활성화
	function updateReplyAbled() {
		if(updateReplyChk == true) {
			$(".updateReplyBtn").removeClass("disabled");
		}
	}
	
	//댓글 수정 등록 버튼 비활성화
	function updateReplyDisabled() {
		$(".updateReplyBtn").addClass("disabled");
	}
	
	
	//답글달기 유효성 체크
	reReplyChk = false;
	
	//답글달기 유효성검사
	$(".reReplyContent").keyup(function() {
        var reReplyContent = $(this).val();
      	//내용이 비어있지 않은 경우
        if(reReplyContent != '') {
        	reReplyChk = true;
            reReplyAbled();
        //내용이 비어있는 경우
        } else {
        	reReplyChk = false;
        	reReplyDisabled();
        }	
	});
	
	//답글달기 등록 버튼 활성화
	function reReplyAbled() {
		if(reReplyChk == true) {
			$(".reReplyBtn").removeClass("disabled");
		}
	}
	
	//답글달기 등록 버튼 비활성화
	function reReplyDisabled() {
		$(".reReplyBtn").addClass("disabled");
	}
	
	
	//공감
	$(".counsel-like").click(function() {
		var counselLike = $(this).next().next().next().val();
		var counselNo = $(this).next().val();
		var memberId = $(this).next().next().val();
		
		if(memberId == "") {
			alert("로그인이 필요합니다.");
    		location.href = "/loginFrm.do";
		} else {
			if(counselLike == 0) {
				//공감하기
				$.ajax({
					url: "/likeUp.do",
					data: {counselNo: counselNo, memberId: memberId},
					type: "post",
					success: function(data) {
						var keys = Object.keys(data);
						alert(data[keys[0]]);
						location.href=data[keys[1]];
					}
				});
			} else if(counselLike == 1) {
				//공감 취소
				$.ajax({
					url: "/likeDown.do",
					data: {counselNo: counselNo, memberId: memberId},
					type: "post",
					success: function(data) {
						var keys = Object.keys(data);
						alert(data[keys[0]]);
						location.href=data[keys[1]];
					}
				});
			}						
		}
	});
		
});