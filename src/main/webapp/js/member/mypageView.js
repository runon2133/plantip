
$(function() {
	
	$(".mypage-nav").eq(0).addClass("mypage-nav-active");
	
	//확장자 체크
	function checkFile(obj, ext) {
	    var check = false;
	    var extName = obj.val().substring(obj.val().lastIndexOf(".") + 1).toLowerCase();
	    var str = ext.split(",");
	    
	    for(var i = 0; i < str.length; i++) {			    
	        if(extName == str[i].trim()) {
	            check = true;
	            break;
	        } else {
	        	check = false;
	        }
	    }
	    
	    if(!check) {
	        alert("지원하지 않는 확장자입니다.");
	    }
	    
	    return check;
	}
	
	//용량 체크
	function checkFileSize(obj, size) {
	    var check = false;
		var objSize = obj.files[0].size;
	    var checkSize = size;
	    
	    
	    if(objSize > checkSize) {
	        alert("10MB 이하 이미지만 가능합니다.");
	        check = false;
	    } else {
	        check = true;
	    }
	    
	    return check;
	}
	
	var MaxSize = 10;
	var FileExt = "jpg, png, jpeg, gif";
	
	//프로필 사진 변경 시
	$("#profile").change(function() {
		if($("#profile").val() != '') {
	        var extPlan = FileExt;
	        var checkSize = 1024 * 1024 * MaxSize;
	        var uploadFileSize = this.files[0].size;
	        
	        if(checkFile($(this), extPlan) && checkFileSize(this, checkSize)) {
	        	if(this.files && this.files[0]) {
		        	var reader = new FileReader();
		        	reader.onload = function(e) {
		        		$("#profileImg").attr("src", e.target.result);		        		
		        	};
		        	reader.readAsDataURL(this.files[0]);			        		
	        	} else {
	        		$("#profileImg").attr("src", "/assets/img/user.svg");
	        	}
	        } else {	
	        	$("#profile").val('');
		    	$("#profileImg").attr("src", "/assets/img/user.svg");
	            return;
	        }
	    } else {
	    	$("#profile").val('');
	    	$("#profileImg").attr("src", "/assets/img/user.svg");
            return;
	    }				
	});

	//프로필 사진 삭제 시
	$("#imgDelete").click(function() {
		$("#profile").val('');
		$("#profileImg").attr("src", "/assets/img/user.svg");
		$("#originalImg").val('');
	});
	
	var nameChk = true;
	var emailChk = true;
	
	//이름 유효성검사
	$("#memberName").keyup(function() {
        var memberName = $(this).val();
        var nameReg = /^[가-힣]{2,10}$/;
        //유효할 경우
        if (memberName != '' && nameReg.test(memberName)) {
            $("#memberNameChk").html("");
            $("#memberNameChk").css("display", "none");
            nameChk = true;
            updateMemberAbled();
        //유효하지 않을 경우
        } else {
        	$("#memberNameChk").html("이름을 정확히 입력하세요.");
        	$("#memberNameChk").css("display", "block");
            nameChk = false;
            updateMemberDisabled();
        }
    });
	
	//이메일 유효성검사
	$("#memberEmail").keyup(function() {
		updateMemberDisabled();
        var memberEmail = $(this).val();
        var emailReg =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        //유효할 경우
        if(memberEmail != '' && emailReg.test(memberEmail)) {
        	$("#emailChkbtn").removeClass("disabled");
        	$("#memberEmailChk").html("");
			$("#memberEmailChk").css("display", "none");
		//유효하지 않을 경우
		} else {
			$("#emailChkbtn").addClass("disabled");
			$("#memberEmailChk").html("이메일을 정확히 입력하세요.");
			$("#memberEmailChk").css("display", "block");
			emailChk = false;
		}				
	});
	
	$("#emailChkbtn").click(function() {
		var memberEmail = $("#memberEmail").val();
		//Email 중복검사
		$.ajax({
			url: "/checkEmail.do",
			data: {memberEmail: memberEmail},
			success: function(data) {
				//중복이 아닌 경우
				if(data == 0) {
					alert("사용가능한 이메일입니다.");
					$("#memberEmailChk").html("");
					$("#memberEmailChk").css("display", "none");
					emailChk = true;
					updateMemberAbled();
				//중복일 경우
				} else if(data == 1) {
					alert("사용중인 이메일입니다.");
					$("#memberEmailChk").html("이미 사용중인 이메일입니다.");
					$("#memberEmailChk").css("display", "block");
					emailChk = false;
					updateMemberDisabled();
				}
			}
		});
	});
	
	//회원정보 저장 버튼 활성화
	function updateMemberAbled() {
		if(nameChk == true && emailChk == true) {
			$("#updateMemberSubmit").removeClass("disabled");
		}
	}
	
	//회원정보 저장 버튼 비활성화
	function updateMemberDisabled() {
		$("#updateMemberSubmit").addClass("disabled");
	}
	
	//회원정보 저장 버튼 클릭 시
	$("#updateMemberSubmit").click(function() {
		return updateMemberChk();
	});
	
	function updateMemberChk() {
		if(nameChk == false || emailChk == false) {
			return false;				
		} else {
			return true;
		}
	}

});