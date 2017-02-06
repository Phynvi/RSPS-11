<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/htmlHead.jsp" flush="false"/>
<body>
	<div class="wrapper">
 		<jsp:include page="../layout/header.jsp" flush="false"/> 
 		<jsp:include page="../layout/top.jsp" flush="false"/> 
		
		<div id="container">
			<table class="tbContainer">
			<thead class=tbhead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>	
			<c:if test="${list != null && list != '[]'}">
				<c:forEach items="${list }" var="list" varStatus="status">
						<tr>
							<td>
								${list.BOARD_ID }
								<input type="hidden" id="BOARD_ID"  name="BOARD_ID"  value="${list.BOARD_ID }">
							</td>
							<td><span class="boardtitle" data-id="${list.BOARD_ID }" >${list.TITLE }</span></td>
							<td>${list.WRITER }</td>
							<td>${list.WRITE_DATE }</td>
						</tr>
						<tr class="b_content" id="content${list.BOARD_ID }">
							<td colspan="4">${list.CONTENT}</td>
						</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list == null || list == '[]'}">
				<tr>
					<td colspan="5">게시글이 없습니다.</td>
				</tr>				
			</c:if>
			</tbody>
			</table>
			<div align="right">
				<a href="${pageContext.request.contextPath}/write"><button class="write">작성하기</button></a>
			</div>
		</div>
		<jsp:include page="../layout/bottom.jsp" flush="false"/> 
	</div>
	<script src="${pageContext.request.contextPath}/js/board.js"></script> 
</body>
</html>