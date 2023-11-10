<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정하기</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<%@ include file="/WEB-INF/jsp/member/menu.jsp" %>
<style type="text/css">
a{
 text-decoration: auto;
}
</style>
</head>
<body>
    <br />
    <h1 class="text-center">게시글 수정하기</h1>
    <br />
    <br />
    <div class="container">
        <form action="updateTest.do" id="viewForm" method="post"
            encType="multiplart/form-data">
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <th>글번호</th>
                        <td><input name="boardNo" type="text" value="${vo.boardNo}"
                            class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" value="${vo.title}"
                            name="title" class="form-control" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea name="content" class="form-control"
                                style="height: 200px;">${vo.content}</textarea></td>
                    </tr>
                    <tr>
                    	<th>첨부파일</th>
			            <td><input type="file" name="uploadFile" class="files form-control form-control-sm"></td> 
		          	</tr>
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <button id="btn_modify" type="button" class="btn btn-primary me-1 mb-1">수정</button>
                            <button id="btn_previous" type="button" class="btn btn-secondary me-1 mb-1">취소</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
<script type="text/javascript">
    $(document).on('click', '#btn_modify', function(e) {
        if (confirm("정말 수정하시겠습니까?") == true) {
            $("#viewForm").submit();
        } else {
            return; 
        }
    });
 
    //목록 클릭 시 list로 이동
    $("#btn_previous").click(function previous() {
        $(location).attr('href', 'list.do');
 
    });
</script>
</html>
