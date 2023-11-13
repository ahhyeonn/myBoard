<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>회원 가입</title>
</head>
<body>
	<div>
		<h1>회원가입</h1>
			<div class="container">
				<form name="frm" method="post" action="/member/join.do">
					<div class="form-group" id="divId">
	                    <label for="inputId" class="col-lg-2 control-label">아이디</label>
	                    <div class="col-lg-5">
	                        <input type="text" class="form-control onlyAlphabetAndNumber me-1 mb-1" id="memId" name="memId" data-rule-required="true" placeholder="20자이내의 알파벳, 숫자만 입력 가능합니다." maxlength="20">
	                    </div>
	                    <div>
	                    	<button id="idChkBtn" class="btn btn-primary btn-sm me-1 mb-1">아이디 중복 확인</button>
	                    </div>
	                </div>
	                <div class="form-group" id="divPassword">
	                    <label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
	                    <div class="col-lg-10">
	                        <input type="password" class="form-control" id="memPw" name="memPw" data-rule-required="true" placeholder="패스워드" maxlength="30">
	                    </div>
	                </div>
	                <!-- 
	                <div class="form-group" id="divPasswordCheck">
	                    <label for="inputPasswordCheck" class="col-lg-2 control-label">패스워드 확인</label>
	                    <div class="col-lg-10">
	                        <input type="password" class="form-control" id="passwordCheck" data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
	                    </div>
	                </div>
	                 -->
	                <div class="form-group" id="divName">
	                    <label for="inputName" class="col-lg-2 control-label">이름</label>
	                    <div class="col-lg-10">
	                        <input type="text" class="form-control onlyHangul" id="memNm" name="memNm" data-rule-required="true" placeholder="한글만 입력 가능합니다." maxlength="15">
	                	</div>
	                </div>
	                <div class="form-group" id="divEmail">
	                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
	                    <div class="col-lg-10">
	                        <input type="email" class="form-control" id="memMail" name="memMail" data-rule-required="true" placeholder="이메일" maxlength="40">
	                    </div>
	                </div>
	             
	                <div class="form-group">
	                    <div class="col-lg-offset-2 col-lg-10">
	                        <button type="submit" onclick="joinBtn()" class="btn btn-primary">회원가입</button>
	                    </div>
	                </div>
				</form>
			</div>

</body>
</html>

<script type="text/javascript">

function joinBtn(){
	console.log("버튼 눌렸당");
	
	//정규식 형식
	var regExpId = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,20}$/; //영문과 숫자 조합의 8-20자, 특수문자(!@#$%^&*)도 사용// 		/^[a-zA-z0-9!@#$%^&*()?_~]{6-20}$/
	var regExpPwd = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{8,20}$/;
	var regExpName = /[가-힝]{2,5}$/; //한글만 가능
	var regExpPhone = /^\d{3}-\d{4}-\d{4}$/; // 010-1111-1111
	
	//폼 입력값 가져오기
	var frm = document.getElementById('frm');
	
	var memId = frm.memId.value;
	var memPw = frm.memPw.value;
	var memNm = frm.memNm.value;
	var memMail = frm.memMail.value;
	
	console.log("frm",frm);
	console.log("memId",memId);
	console.log("memNm",memNm);
	console.log("memMail",memMail);
	
	
	
	/*
	if(memId == "" || memId == null){
		alert("아이디를 입력하세요");
		$("#memId").select();
		return;
	}
	
	if(!regExpId.test(memId)){
		alert("아이디는 영문과 숫자 조합의 6-20자로 입력하세요 ");
		$("#memId").select();
		return;
	}
	
	if(memPw == "" || memPw == null){
		alert("비밀번호를 입력하세요");
		$("#memPw").select();
		return;
	}
	if(!regExpPwd.test(memPw)){
		alert("비밀번호는 영문과 숫자, 특수문자(!@#$%^&*) 조합의 8-20자로 입력하세요 ");
		$("#memPw").select();
		return;
	}
	
	
	if(memNm == "" || memNm == null){
		alert("이름을 입력하세요");
		$("#memNm").select();
		return;
	}
	if(!regExpName.test(memNm)){
		alert("이름은 한글만 가능합니다. 2-5자로 입력하세요 ");
		$("#memNm").select();
		return;
	}
	
	
	if(memMail == "" || memMail == null){
		alert("메일주소를 입력하세요");
		$("#memMail").select();
		return;
	}if(!regExpPhone.test(memMail)){
		alert("형식을 지켜주세요 ");
		$("#memMail").select();
		return;
	}
	*/
	frm.submit();
}


function idCheck() {
	var memId = frm.memId.value;
	data = {"memId":memId}
	console.log("memId",memId);

	$.ajax({
		url:"/member/idCheck.do",
		contentType:"application/json; charset=UTF-8",
		data:JSON.stringify(data),
		type:"post",
		dataType:"json",
		success:function(result){
			console.log("result");
			
			
// 			if(result>0){
// 				alert("이미 존재하는 아이디입니다.");
// 				$("#memId").focus();
// 				return;
// 			}
		}
		
	});
		
}




</script>
</body>
</html>