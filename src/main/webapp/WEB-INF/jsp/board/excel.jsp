<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>엑셀</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/member/menu.jsp" %>
<P>엑셀 테스트 페이지</P>
<form th:action="@{/board/insertExcel.do}" name="excelFrm" method="POST" enctype="multipart/form-data">
	<div class="container">
		<table>
			<tr>
				<td>
			      	<input type="file" th:name="file" class="form-control form-control-sm">
				</td>
				<td>
		      		<button type="submit" class="btn btn-primary" id="excelBtn" name="excelBtn">업로드</button>
				</td>
			</tr>
		</table>
	</div>
</form>

</body>

<script>

//엑셀 파일 전송
$("#excelBtn").click(function() {
   console.log("엑셀 버튼!!!!!!!!");

   var form = new FormData(document.forms.namedItem("excelFrm"));

   $.ajax({
      type: "post",
      url: "<c:url value='/'/>insertExcel.do",
      data: form,
      processData: false,
      contentType: false,
      success: function(response) {
         console.log("response");
         alert("EXCEL 파일 업로드 완료");
      }
   });
})

	
</script>

</html>

