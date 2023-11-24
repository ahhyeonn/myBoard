<%@page import="egovframework.example.board.vo.CmntVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세보기</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<style type="text/css">
a{
 text-decoration: auto;
}

</style>

<%@ include file="/WEB-INF/jsp/member/menu.jsp" %>

</head>

<body>
<% 

	String memId = (String)session.getAttribute("memId"); 
	CmntVo cmntVo = (CmntVo)request.getAttribute("cmntVo");
	
%>
    <br />
    <h1 class="text-center">게시글 상세보기</h1>
    <br />
    <br />
    <div class="container" id="boardArea">
    	<form action="updateTest.do" id="viewForm" method="post" encType="multiplart/form-data">
            <table class="table table-bordered">
                <tbody>
                	<input type="hidden" name="memId" id="memId" value="<%=memId %>" />
                    <tr>
                        <th>글번호</th>
                        <td><input name="boardNo" type="text" value="${vo.boardNo}"
                            class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" value="${vo.title}"
                            name="title" class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input type="text" value="${vo.memId}"
                            name="title" class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea readonly name="content" class="form-control"
                                style="height: 200px;">${vo.content}</textarea></td>
                    </tr>
<!--                     <tr> -->
<%--                         <c:if test="${vo.fileName ne null}"> --%>
<!--                             <tr> -->
<!--                                 <th>다운로드</th> -->
<%--                                 <td><a href="fileDownload.do?fileName=${vo.fileName}"> --%>
<%--                                     <input type="text" id="filename" value="${vo.fileName}" name="fileName" class="form-control" readonly="readonly" /></a> --%>
<!--                             </tr> -->
<%--                         </c:if> --%>
<!--                     </tr> -->
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <button id="btn_previous" type="button" class="btn btn-secondary me-1 mb-1">목록</button>
                            <button id="btn_modify" type="button" class="btn btn-success me-1 mb-1">수정</button>
<%--                             <a href="/update?no=${vo.boardNo}" role="button" class="btn btn-success me-1 mb-1">수정2</a> --%>
                            <button id="btn_delete" type="button" class="btn btn-danger me-1 mb-1">삭제</button>
                        </td>
                    </tr>
                </tbody>
            </table>
    	</form>
    </div>

    
    
<!-- ///////////// 댓글 시작 /////////////-->

<!-- 댓글 출력 시작 -->
	<div class="container" id="cmcmArea">
		<h5>[댓글 목록]</h5>
		<div id="commentArea" class="list-group">
            <c:forEach items="${comment}" var="result" varStatus="stat" >
	            <hr/>
<%--            		<c:if test="${result.cmntDelYn == 'n'}"> --%>
	            	<!-- 댓글 목록 -->	
	            	<c:if test="${result.cmntDepth == 0}">
		            	<div id="comment_${result.cmntNo}">
				            <input type="hidden" id="boardNo" value="${result.cmntNo}" />
		            		<p></p>
							<p>댓글 내용: </p>
							<p name="cmtCnt">${result.cmntContent}</p>
							<p>작성자: ${result.memId}</p>
							<p>작성일시: <fmt:formatDate value="${result.cmntDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							<p></p>
<%-- 							<button type="button" id="replyOpen" data-id="replyWrite${stat.count}" class="btn btn-outline-secondary btn-sm hiddenContent">답글 달기</button> --%>
							<button type="button" id="replyOpen" data-id="comment_${result.cmntNo}" class="btn btn-outline-secondary btn-sm hiddenContent">답글 달기</button>
							<button type="button" class="btn btn-outline-primary btn-sm cmntBtnMod" 
									data-bs-toggle="modal" 
									data-bs-target="#myModal"
									onclick="showCommentContents(${result.cmntNo})"
									>
									수정</button>
							<button type="button" class="btn btn-outline-danger btn-sm cmntBtnDel" id="deleteComment-${result.cmntNo}" onclick="deleteComment(${result.cmntNo})">삭제</button>
		            	</div>
	            	<!-- 대댓글 작성 -->
		            	<div class="container" id="hiddenContent_${result.cmntNo}" >
					    	<form class="modifyViews" method="post" name="id123" id="replyWrite${stat.count}" action="/insertReply.do">
								<div style="margin-top: 30px;" >
									<div class="mb-3 d-flex align-items-center">
										<input type="hidden" id="replyWriter"  value="<%=memId%>" /> 
										<input type="hidden" id="boardNo1" value="${vo.boardNo}" />
<%-- 										<input type="hidden" id="parentCmntNo" value="${result.cmntNo}" /> --%>
										<input type="hidden" id="replyNo_${result.cmntNo}" name="cmntNo2" value="${result.cmntNo}" /> 
										<input id="reContent" name="reContent" class="form-control me-1" type="text" placeholder="댓글을 입력해 주세요" aria-label="default input example" style="width: 95%;"></ipnut>
									  <button type="button" onclick="replyGogo(${result.cmntNo})" class="btn btn-primary btn-sm">등록</button>
									</div>
								</div>
					    	</form>
						</div>
	            	</c:if>
	            	<!-- 대댓글 목록 -->
	            	<c:if test="${result.cmntDepth == 1}">
		            	<div id="comment1_${result.cmntNo}" style="margin-left: 5%;">
				            <input type="hidden" id="boardNo" value="${result.cmntNo}" />
							<p>↳댓글 내용: </p>
							<p name="cmtCnt">${result.cmntContent}</p>
							<p>작성자: ${result.memId}</p>
							<p>작성일시: <fmt:formatDate value="${result.cmntDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							<p></p>
							<button type="button" class="btn btn-outline-primary btn-sm cmntBtnMod" 
									data-bs-toggle="modal" 
									data-bs-target="#myModal"
									onclick="showCommentContents(${result.cmntNo})"
									>
									수정</button>
							<button type="button" class="btn btn-outline-danger btn-sm cmntBtnDel" id="deleteComment1-${result.cmntNo}" onclick="deleteComment(${result.cmntNo})">삭제</button>
		            	</div>
	            	</c:if>
	            	
<%--            		</c:if> --%>
            	</c:forEach>
						<!-- 대댓글 끝 -->
				<!-- 삭제된 댓글 출력 -->
           		<c:if test="${result.cmntDelYn == 'y'}">
           			<div class="col-md-7 py-2" id="comment" data-cmntno="${result.cmntNo}">삭제된 댓글 입니다</div>
           			<hr/>
           		</c:if>
		</div>
	</div>

<!-- 댓글 출력 끝 -->

<!-- 댓글 작성 시작-->
    <div class="container">
    	<form method="post" id="cmntWrite" action="/insertCmnt.do">
			<div id="commentDiv" style="margin-top: 30px;" >
				<div class="mb-3 d-flex align-items-center">
					<input type="hidden" id="cmntWriter" value="<%=memId%>" />
					<input type="hidden" id="boardNo" value="${vo.boardNo}" />
<%-- 					<input type="hidden" id="cmntNo" value="${cmnt.cmntNo}" />  --%>
					<textarea id="cmntContent" class="form-control me-1" type="text" placeholder="댓글을 입력해 주세요" aria-label="default input example" style="width: 95%;" ></textarea>
				  <button type="button" id="cmntBtn" class="btn btn-primary ">등록</button>
				</div>
			</div>
    	</form>
	</div>
<!-- 댓글 작성 끝-->

<!-- 댓글 수정 모달 시작 -->
<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document" style="max-width: 500px">
	    <div class="modal-content position-relative">
			<div class="modal-body p-0">
				<div class="rounded-top-lg py-3 ps-4 pe-6 bg-light">
				  <h4 class="mb-1" id="modalExampleDemoLabel">댓글 수정창</h4>
				</div>
				<div class="p-4 pb-0">
				  <form>
					<input class="form-control" id="cmntNo1" name="cmntNo1" readonly />
					<div class="mb-3">
					  <label class="col-form-label" for="message-text">댓글 내용</label>
					  <textarea class="form-control" id="writeModComment" name="writeModComment" type="text" placeholder="댓글 내용을 입력해주세요"></textarea>
					</div>
				  </form>
				</div>  
	        </div>
			<div class="modal-footer">
				<button class="btn btn-primary cmntModalMod" id="commentModModalConfirm" type="button" value="true">수정</button>
				<button class="btn btn-secondary modalCancle" id="commentModModalCancle" type="button" data-bs-dismiss="modal">취소</button>
			</div>
	    </div>
	</div>
</div>
<!-- 댓글 수정 모달 끝 -->


<!-- //////////// 댓글 끝 /////////////// -->
</body>


<script type="text/javascript">


	

    // 게시글 삭제
    $(document).on('click', '#btn_delete', function(e) {
        var boardNo = ${vo.boardNo};
        if (confirm("정말 삭제하시겠습니까 ?") == true) {
            $("#viewForm").attr("action", "deleteTest.do?boardNo="+boardNo);
            $("#viewForm").submit();
//             console.log("삭제");
        } else {
            return;
        }
    });
    
    //수정 클릭 시 update로 이동
    $("#btn_modify").click(function() {
    	window.location.href = "/update.do?boardNo=" + ${vo.boardNo};
 
    });
    
 
    //목록 클릭 시 list로 이동
    $("#btn_previous").click(function previous() {
        $(location).attr('href', 'list.do');
 
    });
    
    
  	//댓글작성
    $("#cmntBtn").on("click", function() {
       console.log("댓글 올라가세용");
       
       var cmntContent = $("#cmntContent").val();
       console.log("댓글내용 : "+ cmntContent);
       
       
       var memId =$("#memId").val();
       console.log("작성자 : " + memId)
       
       var boardNo = $("input[name='boardNo']").val();
       console.log("글번호 : " + boardNo );
       
       var data = {
		             "cmntContent"	:	cmntContent
		             ,"memId"		:	memId
		             ,"boardNo"		:	boardNo
		//              ,"cmntNo":cmntNo
            	 };
//        console.log(data);

// 		var cmntDelYn = $("#cmntDelYn").val();
// 		console.log("cmntDelYn: " + cmntDelYn);
         
       $.ajax({
           url:"<c:url value='/'/>insertCmnt.do" ,
           data:data,
           type:"POST",
             success:function(data){
                $("#cmntContent").val("");
//                 alert("댓글 작성 성공");

             //href + "(여기 한칸 무조건 띄워야함 무조건) #div id" : 
                $("#cmcmArea").load(window.location.href + " #cmcmArea");
             }
//              ,
//              error:function(request,status,error){
                 //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//                 }
    
         });
         
       });

  	
  	
	 
	//댓글삭제
	function deleteComment(cmntNo) {
// 		$(".cmntBtnDel").on("click", function() {
// 			console.log("댓글 사라지세용");
			
			var boardNo = $("input[name='boardNo']").val();

			var data = {
							"boardNo" : boardNo
							,"cmntNo" : cmntNo
						};
			console.log(data);
			
			$.ajax({
				 url:"<c:url value='/'/>deleteCmnt.do" ,
				 data:data,
				 dataType:"JSON",
				 type:"POST",
				 success:function(data){
	        	 $("#cmntContent").val("");
 	        	 alert("댓글 삭제 완료");

				//href + "(여기 한칸 무조건 띄워야함 무조건) #div id" : 
	        	 $("#cmcmArea").load(window.location.href + " #cmcmArea");
	         	}
// 	        	,complete: function () {
// 	        		alert("나와라!");
// 	        	}	
		
		  });
// 	 }); 
	}
 
	 
	//댓글 수정 버튼(모달창)
	 $(document).on("click", "#updateCmntModal", function() {
			console.log("댓글 수정 버튼이에욥");
			
			var boardNo = $("input[name='boardNo']").val();
	 		console.log("글번호 : " + boardNo);
			
	 		
	 		var cmntNo = $(this).data("cmntNo");
	 		console.log("댓글번호 : " + cmntNo);
	 		
	 		var cmntContent = $(this).data("cmntContent");
	 		console.log("댓글내용 : " + cmntContent);
	 		
	 		//댓글 수정 모달에서 취소버튼 누르면 입력 값 비우기
	 	 	$("#commentModModalCancle").click(function(){
	 	 		$("#writeModComment").val("");
	 	 	});
	 		
	 }); 
	
	//모달창에 값 가져오기
	 function showCommentContents(x){
		 let comment_val = $('#comment_'+x).find('p[name=cmtCnt]').text();
		 console.log("=======================");
		 console.log("댓글번호: " + x);
		 console.log("댓글내용: " + comment_val);
		 
		 $("#cmntNo1").val(x);
	     $("#writeModComment").val(comment_val);
	     
	     $('#cmntNo').val(x);
	     
	 }
	
	//댓글 수정
			
		$('#commentModModalConfirm').on( 'click', function() {
         console.log("=====================================");
         console.log("댓글 수정!!!!!!");
         
         var writeModComment = $("#writeModComment").val();
         console.log("확인: " + writeModComment);
         
         var boardNo = $("input[name='boardNo']").val();
         console.log("글번호 : " + boardNo);
         
         var cmntNo = $("#cmntNo").val();
         console.log("댓글번호 : " + cmntNo);         
         
         
         var data = {
		               "boardNo" : boardNo
		               ,"cmntNo" : cmntNo
		               ,"cmntContent" : writeModComment
             	  };
         
         console.log(data);
         
         $.ajax({
//              url:"<c:url value='/'/>updateCmnt.do" ,
             url:"<c:url value='/'/>insertCmnt.do" ,

             data:data,
             type:"POST",
             success:function(data){
               $("#myModal").modal('hide');
               
            	//href + "(여기 한칸 무조건 띄워야함 무조건) #div id" : 
               $("#cmcmArea").load(window.location.href + " #cmcmArea");
               }   
        });
      });
			
		$(document).ready(function() {	
		    // 모든 수정 창 숨김
		    $(".modifyViews form").css("display", "none");  
		    
		    
		    

	//대댓글 입력 창 나타내기
	$(document).on("click", "button[data-id]", function() {
	    console.log("대댓글 입력 클릭!!!");
	    
	    var cmntNo = $(this).data("id");
	    console.log("댓글 cmntNo:", cmntNo);  //여기까지는 됨
	    
	    
	    //각 해당 댓글번호의 등록 폼 띄우기   
	    
	    
// 	    $("#" + cmntNo).css("display", "block");
// 	    document.getElementById("hiddenContent").style.display = 'block';
	    
	    
// 	    //댓글번호 출력
//         var modiReNo = $("#" + cmntNo + " input[name='cmntNo']").val();
// 	    console.log("댓글 번호:", modiReNo);
	    
	    
// 	    $("#" + cmntNo).css("display", "block");
	    
	    
	    
	    
	    
// 	    if($("#replyWrite" + cmntNo).is(":visible")){	
// 		    $("#replyWrite" + cmntNo).slideUp();
// 	    }else{
// 		    $("#replyWrite" + cmntNo).slideDown();
// 	    }

// 	    var getID = document.getElementsByName(id123);
// 	    getID.style.display=(getID.style.display=='block') ? 'none' : 'block';
		
		});
	});

	
	
		//대댓글 작성
		function replyGogo(re){
				console.log("대댓글 올라가세요");
				
//	 			var reContent = $("#reContent").val();
//	 	        console.log("대댓글내용 : "+ reContent);
		        
		        let reContent = $('#hiddenContent_'+re).find('input[name=reContent]').val();
		        console.log("대댓글내용 : "+ reContent);
		       
		        var replyWriter =$("#replyWriter").val();
		        console.log("대댓글작성자 : " + replyWriter)
		       
		        var boardNo1 = $("#boardNo1").val();
		        console.log("글번호 : " + boardNo1);
		        
		        let replyNo = $('#hiddenContent_'+re).find('input[name=cmntNo2]').val();
				console.log("댓글번호 : " + replyNo);
				
				
				 var data = {
			             "cmntContent"	:	reContent
			             ,"memId"		:	replyWriter
			             ,"boardNo"		:	boardNo1
			             ,"cmntNo"		:	replyNo
	          		 };
		       
		       console.log("data:" + data);
				
				
				/*
				//대댓글 내용
			    let reply_val = $('#hiddenContent_'+re).find('input[name=reContent]').val(); //원댓글 번호를 가져옴
		        console.log("=======================");
		        console.log("댓글번호: " + re);
		        console.log("대댓글내용: " + reply_val);
		       
		        //대댓글 번호 가져오기
		        $("#replyNo").val(re);	
		       
		        //대댓글 내용 가져오기
		        $("#reContent").val(reply_val);
		        
//	 	        $('#replyNo').val(re);
		       
		        var data = {
						"cmntContent"	:	reContent
						,"memId"		:	replyWriter
						,"boardNo"		:	boardNo
						,"cmntNo"		:	replyNo
//						,"upCmntNo"		:	upCmntNo
					};
		       */
		       
		       
				$.ajax({
					 url:"<c:url value='/'/>insertReply.do" ,
					 data:data,
					 dataType:"JSON",
					 type:"POST",
			         success:function(data){
//		 	        	 $("#cmntContent1").val("");
//	 		        	 alert("대댓글 작성 성공");

						//href + "(여기 한칸 무조건 띄워야함 무조건) #div id" : 
			        	 $("#cmcmArea").load(window.location.href + " #cmcmArea");
			         }
			
				  });
			
			
		}

	


	 
    
</script>
</html>





