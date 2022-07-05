
$(function() {
	
	var idChk = false;
	var pwChk = false;
	
	//아이디 유효성검사
	$("#memberId").keyup(function() {
		var memberId = $(this).val();
		var idReg = /^[a-z0-9]{4,16}$/;
		//유효할 경우
		if(memberId != '' && idReg.test(memberId)) {
			$("#memberIdChk").html("");
			$("#memberIdChk").css("display", "none");
			idChk = true;
			loginAbled();
		//유효하지 않을 경우
		} else {
			$("#memberIdChk").html("영문소문자, 숫자를 조합하여 입력하세요.(4~16자)");
			$("#memberIdChk").css("display", "block");
			idChk = false;
			loginDisabled();
		}
	});

	//비밀번호 유효성검사
	$("#memberPw").keyup(function() {
        var memberPw = $(this).val();
        var pwReg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
        //유효할 경우
        if(memberPw != '' && pwReg.test(memberPw)) {
            $("#memberPwChk").html("");
            $("#memberPwChk").css("display", "none");
            pwChk = true;
            loginAbled();
        //유효하지 않을 경우
        } else {
            $("#memberPwChk").html("영문, 숫자, 특수문자를 조합하여 입력하세요.(8~16자)");
            $("#memberPwChk").css("display", "block");
            pwChk = false;
            loginDisabled();
        }
    });
	
	//로그인 버튼 활성화
	function loginAbled() {
		if(idChk == true && pwChk == true) {
			$("#loginSubmit").removeClass("disabled");
		}
	}
	
	//로그인 버튼 비활성화
	function loginDisabled() {
		$("#loginSubmit").addClass("disabled");
	}
	
	//로그인 버튼 클릭 시
	$("#loginSubmit").on("click", function() {
		return loginChk();
	});
	
	function loginChk() {
		if(idChk == false || pwChk == false) {
			return false;				
		} else {
			return true;
		}
	}

});