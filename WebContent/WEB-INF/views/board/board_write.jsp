<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/htmlHead.jsp" flush="false"/>
<body>
	<div style="width: 100%;">
 		<jsp:include page="../layout/header.jsp" flush="false"/> 
 		<jsp:include page="../layout/top.jsp" flush="false"/> 
		
		<div id="container" style="height: 60%; border: solid 1px;">
			<form id="writeFrm" action="${pageContext.request.contextPath }/write" method="post" accept-charset="utf-8">
				<table class="boardContainer" >
				<colgroup>
				<col width="10%" />
				<col width="30%" />
				<col width="10%" />
				<col width="*" />
				</colgroup>
	
					<tr>
						<th>작성자</th>
						<td><input type="text"  id="writer" name="writer"  value="" style="width: 100%"></input></td>
						<th>분류</th>
						<td>공지사항</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text"  id="title" name="title"  value="" style="width: 100%"></input></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea id="content" name="content" style="width: 100%; height: 300px;"></textarea></td>
					</tr>
				</table>
			</form>
			<div align="right">
				<button id="writeBtn">작성</button>
				<button class="write" type="reset" id="cancleBtn">취소</button>
			</div>
		</div>
		<jsp:include page="../layout/bottom.jsp" flush="false"/> 
	</div>
	<script src="${pageContext.request.contextPath}/js/board.js"></script> 
</body>
</html>