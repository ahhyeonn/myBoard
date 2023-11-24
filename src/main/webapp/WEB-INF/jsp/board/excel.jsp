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

<!-- <form action="/downloadExcel.do" method="get"> -->
	<div class="contatiner">
		<table>
			<tr>
				<td>
					<button type="button" id="excelDownloadBtn" name="excelDownloadBtn" class="btn btn-success">EXCEL 다운로드</button>
				</td>
			</tr>
		</table>
	</div>
<!-- </form> -->


<!-- 상품 페이지 -->






<form id="excelFrm" name="excelFrm" method="POST" enctype="multipart/form-data">
	<div class="container">
		<table>
			<tr>
				<td>
			      	<input type="file" name="file" class="form-control form-control-sm">
				</td>
				<td>
		      		<button type="submit" class="btn btn-primary" id="excelBtn" onclick="" name="excelBtn">EXCEL 업로드</button>
				</td>
			</tr>
		</table>
	</div>
</form>




</body>

<script>

//엑셀 파일 전송
$("#excelBtn").click(function() {
   console.log("엑셀 업로드 버튼!!!!!!!!");

//    var data = new FormData(document.forms.namedItem("excelFrm"));
   //파일 값이 null로 보내지는 에러 배열로 보내서 해결
   var form = $("#excelFrm")[0];
   var frmData = new FormData(form);

   $.ajax({
      url: "<c:url value='/'/>insertExcel.do",
      data: frmData,
      type: "post",
   	  enctype: "multipart/form-data",
      processData: false,
      contentType: false,
      success: function(data) {
         console.log("data" + data);
         alert("EXCEL 파일 업로드 완료");
      },
      error: function(e) {
    	  alert("error");
      }
   });
})


//엑셀 파일 다운로드
$('#excelDownloadBtn').on( 'click', function() {
	document.location.href = "/excel/PROD_example.xlsx";
})
	
</script>

</html>

