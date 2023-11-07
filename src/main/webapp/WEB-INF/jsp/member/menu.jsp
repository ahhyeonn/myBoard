<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<a href="${path}/list.do">게시판</a>
<a href="${path}/chart.do">차트</a>
<c:choose>
    <c:when test="${sessionScope.memId == null}">
        <a href="${path}/member/login.do">로그인</a>
    </c:when>
    <c:otherwise>
        ${sessionScope.memNm}님이 로그인중입니다.
        <a href="${path}/member/logout.do">로그아웃</a>
    </c:otherwise>
</c:choose>
    
<hr>