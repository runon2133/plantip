
$(function() {
				
	var titleChk = false;
	var contentChk = false;
	
	//확장자 체크
	function checkFile(obj, ext) {
	    var check = false;
	    var extName = obj;
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
		var objSize = obj;
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
	
	//사진 변경 시
	$("#addImgs").change(function() {
		$("#preview").empty();
		var maxFileCnt = 3; //첨부파일 최대 갯수
		var curFileCnt = this.files.length;  //현재 선택된 첨부파일 개수
		
		var count = 0;
		var files = this.files;
	    var arr = Array.prototype.slice.call(files);
	    
	    if (curFileCnt > maxFileCnt) {
	        alert("사진은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	        $("#addImgs").val('');
        	$("#preview").empty();
        	$("#imgCnt").html('0');
	        return;
	    } else {
		    for(var i = 0; i < files.length; i++) {
		    	var extPlan = FileExt;
		        var checkSize = 1024 * 1024 * MaxSize;
		        
		        var extName = arr[i].name.substring(arr[i].name.lastIndexOf(".") + 1).toLowerCase();
		        var extSize = files[i].size;
		        
		        if( !( checkFile(extName, extPlan) && checkFileSize(extSize, checkSize) ) ) {
		        	$("#addImgs").val('');
		        	$("#preview").empty();
		        	$("#imgCnt").html('0');
		            return;
		        }
	        	count += 1;
		    }			    	
	    }
	    $("#imgCnt").html(count);
	    preview(arr);
	    console.log(arr);
	});
	
	$(".upload-hidden").click(function() {
		$("#preview").empty();
	});
	
	//사진 올리기 미리보기
	function preview(arr) {
		arr.forEach(function(f) {
	        
	        //div에 이미지 추가
	        var str = '<div style="display: inline-flex;"><ul style="padding: 0; margin: 0;"><li>';
	        
	        //이미지 파일 미리보기
	        if(f.type.match('image.*')) {
	          var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
	          reader.onload = function(e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
	            str += '<img src="' + e.target.result + '" />';
	            str += '</li></ul></div>';
	            $(str).appendTo('#preview');
	          } 
	          reader.readAsDataURL(f);
	        } else {
	          return;
	        }
	    });
	}

	//제목 글자수 체크
	$("#counselTitle").keyup(function() {
        var counselTitle = $(this).val();
        $("#titleCnt").html(counselTitle.length);
        //제목이 비어있지 않은 경우
        if (counselTitle != '') {
            titleChk = true;
            counselAbled();
        //제목이 비어있는 경우    
        } else {
            titleChk = false;
            counselDisabled();
        }
    });
	
	//내용 글자수 체크
	$("#counselContent").keyup(function() {
        var counselContent = $(this).val();
        $("#contentCnt").html(counselContent.length);
      	//내용이 비어있지 않은 경우
        if (counselContent != '') {
            contentChk = true;
            counselAbled();
        //내용이 비어있는 경우    
        } else {
            contentChk = false;
            counselDisabled();
        }
    });
	
	//상담 저장 버튼 활성화
	function counselAbled() {
		if(titleChk == true && contentChk == true) {
			$("#counselSubmit").removeClass("disabled");
		}
	}
	
	//상담 저장 버튼 비활성화
	function counselDisabled() {
		$("#counselSubmit").addClass("disabled");
	}
	
	//상담 저장 버튼 클릭 시
	$("#counselSubmit").click(function() {
		return counselChk();
	});
	
	function counselChk() {
		if(titleChk == false || contentChk == false) {
			return false;				
		} else {
			return true;
		}
	}
	
});