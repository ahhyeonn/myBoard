<%@page import="egovframework.example.board.vo.CmntVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글 수 차트</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<canvas id="myChart" width="400" height="400"></canvas>
===차트===
</body>
<script>
  
$(document).ready(function(){ 
	getGraph();
});

function getGraph(){
	  var dateList = [];
	  var countList = [];
	  
 	  console.log("차트 왔당!!");
 	  
	  
	  $.ajax({
		  url:"<c:url value='/'/>chartAjax.do" ,
//  		  contentType:"application/json; charset=UTF-8",
//  		  data:JSON.stringify(data),
		  type:"get",
		  dataType:"json",
		  success:function(mav){
			  console.log("ajax성공");
// 			  console.log("ㅇㅅㅇ:" + data[0].reDate1);
// 			  console.log("얍얍: " + data[0][0].reCnt); 
			  console.log("얍: " + mav); 
// 			  console.log("얍: " + data[1]); 
			  
			  			  
			  for (var i = 0; i < data.length; i++) {
				  console.log("얍얍: " + data[i]); 
			}
			  
			  
			  
			  /*
			  for (var i = 0; i < data.length; i++) {
				  for (var j = 0; j < data[i].length; j++) {
				    var reCnt = data[i][j].reCnt;
				    console.log("reCnt: " + reCnt);
				  }
				}
			  */
			  
			  
			  // 그래프로 나타낼 자료 리스트에 담기
			  for (var i = 0; i < data.length;i++){  
// 				  console.log("얍: " + data[i].reCnt); 
				  
				  dateList.push(data[i].reDate1);
				  countList.push(data[i].reCnt);
				  
				  console.log("reDate1 : " + reDate1);
				  console.log("reCnt : " + reCnt);  	
			  }
			  
			  
			  
			  
			  
			  // 그래프
			  new Chart(document.getElementById("myChart"), {
		    	  type: 'line',
		    	  data: {
		    	    labels: dateList, // X축 
		    	    datasets: [{ 
		    	        data: countList, // 값
		    	        label: "댓글수",
		    	        borderColor: 'rgba(255, 99, 132, 1)',
		    	        fill: false
		    	      }
		    	    ]
		    	  },
		    	  options: {
		    	    title: {
		    	      display: true,
		    	      text: '게시글 댓글 수'
		    	    }
		    	  }
		    	}); //그래프
		  },
		  error:function(){
			  alert("실패");
		  }  
   		  
	  }) // ajax	  
} // getGraph

</script>
</html>