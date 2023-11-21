<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>비밀번호 찾기</title>

</head>
<body>

<%@ include file="/WEB-INF/jsp/member/menu.jsp"%>
<form name="form1" method="post" action="/sessionTest.do">
	<div class="container" id="tempPw" align="center">
		<label for="inputEmail" class="control-label">회원가입 시 작성했던 이메일 주소를 입력하세요.</label>
		<table border="0" width="400px">
			<tr>
				<td align="center">
					<input type="email" class="form-control" id="memMail" name="memMail" data-rule-required="true" maxlength="40">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button type="button" class="btn btn-primary me-1 mb-1" id="tempPwBtn" name="tempPwBtn">임시 비밀번호 전송</button>
				</td>
			</tr>
		</table>
    </div>
</form>
</body>

<script>


//임시 비밀번호 전송
$("#tempPwBtn").click(function() {
	const email = $("#memMail").val(); // 이메일 주소값 가져옴
//		console.log("이메일 : " + email); // 이메일 오는지 확인
	
	const checkInput = $(".mailCheck") // 인증번호 입력하는곳 

	$.ajax({
//			console.log("얍!!!!!!");
		type : "get",
		url : "/member/pwCheck.do", 
		data: { email: email }, 
		success : function (data) {
//				console.log("data : " +  data);
			checkInput.attr("disabled",false);
			code = data;
			alert("임시 비밀번호가 전송되었습니다.");
		}			
	}); // ajax끝 
}); // 이메일 보내기 끝


	
	
	
</script>
</html>





