<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<%@ include file="/WEB-INF/jsp/member/menu.jsp" %>
<style type="text/css">
a{
 text-decoration: auto;
}
</style>
</head>
<body>
<% String memId = (String)session.getAttribute("memId"); %>
<c:set var="memId" value="<%=memId%>" />

<br/>
    <h1 class="text-center">게시글 작성</h1>
<br/>
<br/>
<div class="container">
        <form id="form_test" action="insertTest.do" method="post" encType="multipart/form-data">
            <input type="hidden" id="memId" name="memId" value="<%=memId%>" />
            <table class="table table-bordered">
                <tbody>
                    <tr> 
                        <th>제목</th>
                        <td><input type="text" placeholder="제목을 입력하세요."
                            name="title" class="form-control" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea placeholder="내용을 입력하세요 ." name="content"
                                class="form-control" style="height: 200px;"></textarea></td>
                    </tr>
                    <tr>
                    	<th>첨부파일</th>
                    	<div class="fileDrop"> 
				            <td><input type="file" class="files form-control form-control-sm" name="uploadFile" multiple ></td> 
                    	</div>
                    	<div id="uploadedList"></div>
		          	</tr>
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <button id="btn_register" type="button" class="btn btn-success me-1 mb-1">등록</button>
<!--                             <button id="btn_file" type="button" class="btn btn-success me-1 mb-1">파일확인용</button> -->
                            <button id="btn_previous" type="button" class="btn btn-danger me-1 mb-1">취소</button>
                    </tr>
                </tbody>
            </table>
        </form>
        
        
</div>



</body>
<script type="text/javascript">

    //글쓰기
    $(document).on('click', '#btn_register', function(e) {
        var title = document.getElementsByName("title")[0].value;
        var content = document.getElementsByName("content")[0].value;
        var memId = document.getElementsByName("memId")[0].value;
        
        console.log("title:",title);
        console.log("content:",content);
        console.log("memId:",memId);
        
        if(title == "" || title == null){
        	alert("제목을 작성하세요");
        	document.getElementById("title").focus();
        	return;
        }
        
        if(content == "" || content == null){
        	alert("내용을 작성하세요");
        	document.getElementById("content").focus();
        	return;
        }
    	
    	
        $("#form_test").submit();
        console.log('gogo');
       
    });
 
    //이전 클릭 시 testList로 이동
    $("#btn_previous").click(function previous() {
        $(location).attr('href', 'list.do');
 
    });
    
    
    
    //파일업로드
    /*
	var formData = new FormData(document.getElementById('myForm'));	
	
    $.ajax({
	   type:"POST",
	   enctype: 'multipart/form-data',
	   processData:false,
	   contentType:false,
	   url:"/test",
	   data:formData,
	   success:function(data){
	   console.log("success") 
	   
	   }
    
	}); 
    */
    
		/*
	$(document).ready(function(){
		
		$("#btn_file").on("click",function(e){
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			
			
			
			var files = inputFile[0].files;
			console.log(files);
			
			//add File Data to formData
			for(var i = 0; i < files.length; i++){
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				   url:"/uploadAjax.do",
				   processData:false,
				   contentType:false,
				   data:formData,
				   type:"POST",
// 				   enctype: 'multipart/form-data',
				   success:function(data){
// 				   	console.log("success"); 
				   	alert("업로드 성공!!");
				   }
			}); //ajax
		});
	});    
			*/
			
    
    
    
    
</script>
</html>














