
$(function() {
	
	var emailChk = false;
	
	//이메일 유효성검사
	$("#memberEmail").keyup(function() {
        var memberEmail = $(this).val();
        var emailReg =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        //유효할 경우
        if(memberEmail != '' && emailReg.test(memberEmail)) {
        	$("#memberEmailChk").html("");
			$("#memberEmailChk").css("display", "none");
			emailChk = true;
			searchIdAbled();
		//유효하지 않을 경우
		} else {
			$("#memberEmailChk").html("이메일을 정확히 입력하세요.");
			$("#memberEmailChk").css("display", "block");
			emailChk = false;
			searchIdDisabled();
		}				
	});
	
	//이메일 전송하기 버튼 활성화
	function searchIdAbled() {
		if(emailChk == true) {
			$("#searchIdSubmit").removeClass("disabled");
		}
	}
	
	//이메일 전송하기 버튼 비활성화
	function searchIdDisabled() {
		$("#searchIdSubmit").addClass("disabled");
	}
	
	//이메일 전송하기 버튼 클릭 시
	$("#searchIdSubmit").on("click", function() {
		return searchIdChk();
	});
	
	function searchIdChk() {
		if(emailChk == false) {
			return false;				
		} else {
			return true;
		}
	}

});