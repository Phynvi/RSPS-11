<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/htmlHead.jsp" flush="false"/>
<body>
	<div style="width: 100%;">
 		<jsp:include page="../layout/header.jsp" flush="false"/> 
 		<jsp:include page="../layout/top.jsp" flush="false"/> 
		
		<div id="container" style="height: 60%; border: solid 1px;">
			<div style="display: flex;  padding-top: 10px;" align="center">
				<span style="padding-bottom: 3px; padding-right: 5px;">당신이 낼 것은?</span> 
				<select id="type" name="u_type">
					<option value="1">가위</option>
					<option value="2">바위</option>
					<option value="3">보</option>
				</select>
				<button id="start">Go</button>
			</div>
			<div   class="resultDescription"></div>
		</div>
		<input type="hidden" id="path" value="${pageContext.request.contextPath}">
		<jsp:include page="../layout/bottom.jsp" flush="false"/> 
	</div>
<script src="${pageContext.request.contextPath}/js/game.js"></script> 
</body>
</html>