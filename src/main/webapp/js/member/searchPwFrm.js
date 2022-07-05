
$(function() {
	
	var idChk = false;
	var emailChk = false;
	
	//아이디 유효성검사
	$("#memberId").keyup(function() {
		var memberId = $(this).val();
		var idReg = /^[a-z0-9]{4,16}$/;
		//유효할 경우
		if(memberId != '' && idReg.test(memberId)) {
			$("#memberIdChk").html("");
			$("#memberIdChk").css("display", "none");
			idChk = true;
			searchPwAbled();
		//유효하지 않을 경우
		} else {
			$("#memberIdChk").html("아이디를 정확히 입력하세요.");
			$("#memberIdChk").css("display", "block");
			idChk = false;
			searchPwDisabled();
		}
	});
	
	//이메일 유효성검사
	$("#memberEmail").keyup(function() {
        var memberEmail = $(this).val();
        var emailReg =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        //유효할 경우
        if(memberEmail != '' && emailReg.test(memberEmail)) {
        	$("#memberEmailChk").html("");
			$("#memberEmailChk").css("display", "none");
			emailChk = true;
			searchPwAbled();
		//유효하지 않을 경우
		} else {
			$("#memberEmailChk").html("이메일을 정확히 입력하세요.");
			$("#memberEmailChk").css("display", "block");
			emailChk = false;
			searchPwDisabled();
		}				
	});
	
	//이메일 전송하기 버튼 활성화
	function searchPwAbled() {
		if(idChk == true && emailChk == true) {
			$("#searchPwSubmit").removeClass("disabled");
		}
	}
	
	//이메일 전송하기 버튼 비활성화
	function searchPwDisabled() {
		$("#searchPwSubmit").addClass("disabled");
	}
	
	//이메일 전송하기 버튼 클릭 시
	$("#searchPwSubmit").on("click", function() {
		return searchPwChk();
	});
	
	function searchPwChk() {
		if(idChk == false || emailChk == false) {
			return false;			
		} else {
			return true;
		}
	} 
	
});