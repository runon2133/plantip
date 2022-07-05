
$(function() {
	
	var nameChk = false;
	var idChk = false;
	var pwChk = false;
	var emailChk = false;
	
	//이름 유효성검사
	$("#memberName").keyup(function() {
        var memberName = $(this).val();
        var nameReg = /^[가-힣]{2,10}$/;
        //유효할 경우
        if (memberName != '' && nameReg.test(memberName)) {
            $("#memberNameChk").html("");
            $("#memberNameChk").css("display", "none");
            nameChk = true;
            joinAbled();
        //유효하지 않을 경우
        } else {
        	$("#memberNameChk").html("이름을 정확히 입력하세요.");
        	$("#memberNameChk").css("display", "block");
            nameChk = false;
            joinDisabled();
        }
    });
	
	//아이디 유효성검사
	$("#memberId").keyup(function() {
		var memberId = $(this).val();
		var idReg = /^[a-z0-9]{4,16}$/;
		//유효할 경우
		if(memberId != '' && idReg.test(memberId)) {
			//Id 중복검사
			$.ajax({
				url: "/checkId.do",
				data: {memberId: memberId},
				success: function(data) {
					//중복이 아닌 경우
					if(data == 0) {
						$("#memberIdChk").html("");
						$("#memberIdChk").css("display", "none");
						idChk = true;
						joinAbled();
					//중복일 경우
					} else if(data == 1) {
						$("#memberIdChk").html("이미 사용중인 아이디입니다.");
						$("#memberIdChk").css("display", "block");
						idChk = false;
						joinDisabled();
					}
				}
			});
		//유효하지 않을 경우
		} else {
			$("#memberIdChk").html("아이디를 정확히 입력하세요.");
			$("#memberIdChk").css("display", "block");
			idChk = false;
			joinDisabled();
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
            joinAbled();
        //유효하지 않을 경우
        } else {
            $("#memberPwChk").html("비밀번호를 정확히 입력하세요.");
            $("#memberPwChk").css("display", "block");
            pwChk = false;
            joinDisabled();
        }
    });
	
	//이메일 유효성검사
	$("#memberEmail").keyup(function() {
        var memberEmail = $(this).val();
        var emailReg =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        //유효할 경우
        if(memberEmail != '' && emailReg.test(memberEmail)) {
			//Email 중복검사
			$.ajax({
				url: "/checkEmail.do",
				data: {memberEmail: memberEmail},
				success: function(data) {
					//중복이 아닌 경우
					if(data == 0) {
						$("#memberEmailChk").html("");
						$("#memberEmailChk").css("display", "none");
						emailChk = true;
						joinAbled();
					//중복일 경우
					} else if(data == 1) {
						$("#memberEmailChk").html("이미 사용중인 이메일입니다.");
						$("#memberEmailChk").css("display", "block");
						emailChk = false;
						joinDisabled();
					}
				}
			});
		//유효하지 않을 경우
		} else {
			$("#memberEmailChk").html("이메일을 정확히 입력하세요.");
			$("#memberEmailChk").css("display", "block");
			emailChk = false;
			joinDisabled();
		}				
	});
	
	//회원가입 버튼 활성화
	function joinAbled() {
		if(nameChk == true && idChk == true && pwChk == true && emailChk == true) {
			$("#joinSubmit").removeClass("disabled");
		}
	}
	
	//회원가입 버튼 비활성화
	function joinDisabled() {
		$("#joinSubmit").addClass("disabled");
	}
	
	//회원가입 버튼 클릭 시
	$("#joinSubmit").on("click", function() {
		return joinChk();
	});
	
	function joinChk() {
		if(nameChk == false || idChk == false || pwChk == false || emailChk == false) {
			return false;				
		} else {
			return true;
		}
	}			
	
});