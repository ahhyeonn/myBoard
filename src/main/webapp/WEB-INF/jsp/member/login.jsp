<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>로그인페이지</title>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/member/menu.jsp"%>
	<form name="form1" method="post" action="/sessionTest.do">
	<div class="container">
		<table border="0" width="400px">
			<tr>
				<td>아이디</td>
				<td><input class="form-control form-control-sm" style="width:200px;" name="memId" id="memId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input class="form-control form-control-sm" style="width:200px;" type="password" name="memPw" id="memPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
<%-- 					<button class="btn btn-primary me-1 mb-1" type="button" >로그인</button> <c:if --%>
<%-- 						test="${msg == 'failure'}"> --%>
					<button class="btn btn-primary me-1 mb-1" type="button" id="btnLogin">로그인</button> 
					<c:if test="${msg == 'failure'}"> 
						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if> <c:if test="${msg == 'logout'}">
						<div style="color: red">로그아웃되었습니다.</div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-outline-primary me-1 mb-1" type="button" id="btnLogin">비밀번호 찾기</button> 
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 임시 비밀번호 test -->
	<div class="form-group" id="divEmail">
          <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
          <div class="col-lg-10">
              <input type="email" class="form-control" id="memMail" name="memMail" data-rule-required="true" placeholder="이메일 주소를 입력해주세요." maxlength="40">
          </div>
      </div>
      <div class="form-group" id="divEmailCheckBox">
          <div class="col-lg-10 mailCheckBox">
              <input type="text" class="form-control" placeholder="인증번호 6자리를 입력해주세요." id="mailCheck" name="mailCheck"/>
          </div>
          <div class="divMailCheckBtn">
              <button type="button" class="btn btn-secondary me-1 mb-1" id="mailCheckBtn" name="mailCheckBtn">임시비밀번호 전송</button>
          </div>
          <span id="mail-check-warn"></span>
	</form>

</body>

<script>

$(document).ready(function() {
	$("#btnLogin").click(function() {
		// 태크.val() : 태그에 입력된 값
		// 태크.val("값") : 태그의 값을 변경 
		var memId = $("#memId").val();
		var memPw = $("#memPw").val();
		if (memId == "") {
			alert("아이디를 입력하세요.");
			$("#memId").focus(); // 입력포커스 이동
			return; // 함수 종료
		}
		if (memPw == "") {
			alert("비밀번호를 입력하세요.");
			$("#memPw").focus();
			return;
		}
		// 폼 내부의 데이터를 전송할 주소
		document.form1.action = "${path}/member/loginCheck.do"
		// 제출
		document.form1.submit();
	});
});
	

//임시 비밀번호 test
$("#mailCheckBtn").click(function() {
	const email = $("#memMail").val(); // 이메일 주소값 가져옴
//		console.log("이메일 : " + email); // 이메일 오는지 확인
	
	const checkInput = $(".mailCheck") // 인증번호 입력하는곳 

	$.ajax({
//			console.log("얍!!!!!!");
		type : "get",
		url : "/member/pwCheck.do", 
		data: { email: email }, 
//			url : '<c:url value ="/member/mailCheck.do?email="/>'+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
		success : function (data) {
//				console.log("data : " +  data);
			checkInput.attr("disabled",false);
			code = data;
			alert("임시 비밀번호가 전송되었습니다.");
		}			
	}); // end ajax
}); // end send eamil


// 인증번호 비교 
// blur -> focus가 벗어나는 경우 발생
$("#mailCheck").blur(function () {
	const inputCode = $(this).val();
	const $resultMsg = $("#mail-check-warn");
	
	if(inputCode == code){
		$resultMsg.html("인증번호가 일치합니다.");
		$resultMsg.css("color","blue");
		$("#mailCheckBtn").attr("disabled",true);
		$("#memMail").attr("readonly",true);
	} else {
		$resultMsg.html("인증번호가 불일치 합니다. 다시 확인해주세요!");
		$resultMsg.css("color","red");
	}
});
	
	
	
	
</script>
</html>





