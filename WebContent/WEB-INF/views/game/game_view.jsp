<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/htmlHead.jsp" flush="false"/>
<body>
	<div class="wrapper">
 		<jsp:include page="../layout/header.jsp" flush="false"/> 
 		<jsp:include page="../layout/top.jsp" flush="false"/> 
		
		<div id="container">
			<div class="gameStart" align="center">
				<span class="gameQA">당신이 낼 것은?</span> 
				<select id="type" name="u_type" class="choice">
					<option value="1">가위</option>
					<option value="2">바위</option>
					<option value="3">보</option>
				</select>
				<button id="start">Go</button>
			</div>
			<div   class="resultDescription"></div>
		</div>
		<jsp:include page="../layout/bottom.jsp" flush="false"/> 
	</div>
<script src="${pageContext.request.contextPath}/js/game.js"></script> 
</body>
</html>