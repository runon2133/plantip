
$(function() {
	
	$(".mypage-nav").removeClass("mypage-nav-active");
	$(".mypage-nav").eq(2).addClass("mypage-nav-active");
	
	var pwChk = false;
	var newPwChk = false;
	
	//비밀번호 유효성검사
	$("#memberPw").keyup(function() {
        var memberPw = $(this).val();
        var pwReg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
        //유효할 경우
        if(memberPw != '' && pwReg.test(memberPw)) {
        	//현재 비밀번호 일치 확인
			$.ajax({
				url: "/checkPw.do",
				data: {memberPw: memberPw},
				success: function(data) {
					//일치하는 경우
					if(data == 0) {
						$("#memberPwChk").html("");
	                    $("#memberPwChk").css("display", "none");
	                    pwChk = true;
	                    updatePwAbled();
					//일치하지 않는 경우
					} else if(data == 1) {
						$("#memberPwChk").html("비밀번호 확인 후 다시 시도하세요.");
						$("#memberPwChk").css("display", "block");
						pwChk = false;
	                    updatePwDisabled();
					}
				}
			});
        //유효하지 않을 경우
        } else {
            $("#memberPwChk").html("비밀번호를 정확히 입력하세요.");
            $("#memberPwChk").css("display", "block");
            pwChk = false;
            updatePwDisabled();
        }
    });
	
	//새 비밀번호 유효성검사
	$("#newPw").keyup(function() {
        var newPw = $(this).val();
        var pwReg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
        //유효할 경우
        if(newPw != '' && pwReg.test(newPw)) {
            $("#newPwChk").html("");
            $("#newPwChk").css("display", "none");
        //유효하지 않을 경우
        } else {
            $("#newPwChk").html("비밀번호를 정확히 입력하세요.");
            $("#newPwChk").css("display", "block");
        }
    });
	
	//새 비밀번호 일치 확인
	$("#newPwConfirm").keyup(function() {
		var newPw = $("#newPw").val();
		var newPwConfirm = $(this).val();
        //새 비밀번호와 일치할 경우
        if(newPw == newPwConfirm) {
            $("#newPwConfirmChk").html("");
            $("#newPwConfirmChk").css("display", "none");
            newPwChk = true;
            updatePwAbled();
        //유효하지 않을 경우
        } else {
            $("#newPwConfirmChk").html("위 비밀번호와 일치하지 않습니다.");
            $("#newPwConfirmChk").css("display", "block");
            newPwChk = false;
            updatePwDisabled();
        }
	});
	
	//새 비밀번호 저장 버튼 활성화
	function updatePwAbled() {
		if(pwChk == true && newPwChk == true) {
			$("#updatePwSubmit").removeClass("disabled");
		}
	}
	
	//새 비밀번호 저장 버튼 비활성화
	function updatePwDisabled() {
		$("#updatePwSubmit").addClass("disabled");
	}
	
	//새 비밀번호 저장 버튼 클릭 시
	$("#updatePwSubmit").click(function() {
		return updatePwChk();
	});
	
	function updatePwChk() {
		var memberPw = $("#memberPw").val();
		var newPw = $("#newPw").val();
		
		if(pwChk == false || newPwChk == false) {
			return false;
		} else {
			if(memberPw == newPw) {
				alert("기존 비밀번호와 신규 비밀번호가 서로 동일합니다.");
				return false;						
			}
			return true;
		}
	}
	
});