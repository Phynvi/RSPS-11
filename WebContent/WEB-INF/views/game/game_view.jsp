<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%;">
		<div id="header" 	class="head">
			<h5 class="headText">${title }</h5>
		</div>
		<!-- 	<div id="menu" style="height: 10%; border: solid 1px; display: flex;" >
			<div id="menu1" style="width: 25%; border: solid 1px;">
				<span>${title }하기</span>
			</div>
			<div id="menu2" style="width: 25%; border: solid 1px;">
				<span>이력보기</span>			
			</div>
			<div id="menu3" style="width: 25%; border: solid 1px;">
				<span>문의사항</span>			
			</div>
			<div id="menu4" style="width: 25%; border: solid 1px;">
				<span>회원관리</span>			
			</div>
		</div>
		 -->
		
		<div id="menu" style="height: 10%; border: solid 1px; display: flex;" >
			<div id="menu1" style=" width: 33.3%; border: solid 1px;">
				<span>RSP game</span>
			</div>
			<div id="menu2" style="width: 33.3%; border: solid 1px;">
				<span>이력보기</span>			
			</div>
			<div id="menu3" style="width: 33.3%; border: solid 1px;">
				<span>문의사항</span>			
			</div>
		</div>
		
		<div id="container" style="height: 60%; border: solid 1px;">
		<form id="gameFrm" action="${pageContext.request.contextPath }/game" method="post">
		
			<div style="display: flex;  padding-top: 10px;" align="center">
				<span style="padding-bottom: 3px; padding-right: 5px;">당신이 낼 것은?</span> 
				<select id="type" name="u_type">
					<option value="1">가위</option>
					<option value="2">바위</option>
					<option value="3">보</option>
				</select>
				<input type="hidden" name="p_type" id="p_type" value="">
				<input type="hidden" name="vs_result" id="vs_result" value="">
				<button id="start">Go</button>
			</div>
			<div id="resultDescription">
				<c:if test="${list != null }">
					<c:forEach items="${list }" var="list">
                	  <c:choose>
                      <c:when test="${list.P_TYPE eq 1}">
                                <td>가위  </td>
                      </c:when>
                      <c:when test="${list.P_TYPE eq 2}">
                       	<span>  바위</span>
                      </c:when>
                      <c:when test="${list.P_TYPE eq 3}">
                        	<span> 보</span>
                      </c:when>
                  </c:choose>
                	  <c:choose>
                      <c:when test="${list.U_TYPE eq 1}">
                                <td>가위  </td>
                      </c:when>
                      <c:when test="${list.U_TYPE eq 2}">
                       	<span>  바위</span>
                      </c:when>
                      <c:when test="${list.U_TYPE eq 3}">
                        	<span> 보</span>
                      </c:when>
                  </c:choose>
                	  <c:choose>
                      <c:when test="${list.VS_RESULT eq 0}">
                              <span>비겼음</span>
                      </c:when>
                      <c:when test="${list.VS_RESULT eq 2}">
                       	<span>  졌음</span>
                      </c:when>
                      <c:when test="${list.VS_RESULT eq 1}">
                        	<span> 이겼음</span>
                      </c:when>
                  </c:choose>
				</c:forEach>
				</c:if>
				
			</div>
		</form>
		</div>
		
		<div id="footer" style="height:14.5% ; border: solid 1px;" align="right">
			<span>by. yul</span>
		</div>	
	
	</div>
	
</body>

<script type="text/javascript">
$(function(){
	  $('#start').on('click', function(){
		  var pcType = Math.floor(Math.random() * 3) + 1;
		  var pcTypeText = "";
		  var type = "";
		  var typeText = "";
		  var resultText = "";
		  
		  $("select option:selected").each(function () { 
			  	type = $(this).val();
			  	typeText = $(this).text();
			  });
	
		  if(pcType == 1){
			  pcTypeText = "가위";
		  }else if(pcType == 2){
			  pcTypeText = "바위";
		  }else{
			  pcTypeText = "보";
		  }
		  var result = type-pcType;
		  $('#p_type').val(pcType);
		  $('#vs_result').val(result);
		  $('#gameFrm').submit();
		  
		  // 임시처리
		  if(result == 0){
			  resultText = "당신은" + typeText +"를 냈고 PC도 " +  pcTypeText +"를 내서 비겼습니다.";
		  }else if(result == -1 || result == 2){
			  resultText = "당신은" + typeText +"를 냈고 PC는 " +  pcTypeText +"를 내서 당신이 졌습니다.";
		  }else if(result == 1 || result == -2){
			  resultText = "당신은" + typeText +"를 냈고 PC는 " +  pcTypeText +"를 내서 당신이 이겼습니다.";
		  }
//			  $('#gameFrm').submit();
		  $('#resultDescription').append('<p>' + resultText+ '</p>');
	  })
});
</script>
</html>