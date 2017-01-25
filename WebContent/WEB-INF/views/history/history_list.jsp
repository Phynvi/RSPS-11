<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/htmlHead.jsp" flush="false"/>
<body>
	<div style="width: 100%;">
 		<jsp:include page="../layout/header.jsp" flush="false"/> 
 		<jsp:include page="../layout/top.jsp" flush="false"/> 
		
		<div id="container" style="height: 60%; border: solid 1px;">
			<table class="tbContainer">
			<thead class=tbhead>
				<tr>
					<th>게임수</th>
					<th>PC가 낸 것</th>
					<th>내가 낸 것</th>
					<th>결과</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>	
			<c:if test="${list != null }">
				<c:forEach items="${list }" var="list" varStatus="status">
						<tr>
							<td>
								${list.RN }
								<input type="hidden" id="HISTORY_ID"  name="HISTORY_ID"  value="${list.HISTORY_ID }">
							</td>
							   <c:choose>
							       <c:when test="${list.P_TYPE == '1'}">
							           <td>가위</td>
							       </c:when>
							       <c:when test="${list.P_TYPE == '2'}">
							           <td>바위</td>
							       </c:when>
							       <c:when test="${list.P_TYPE == '3'}">
							           <td>보</td>
							       </c:when>
							   </c:choose>
						
							   <c:choose>
							       <c:when test="${list.U_TYPE == '1'}">
							           <td>가위</td>
							       </c:when>
							       <c:when test="${list.U_TYPE == '2'}">
							           <td>바위</td>
							       </c:when>
							       <c:when test="${list.U_TYPE == '3'}">
							           <td>보</td>
							       </c:when>
							   </c:choose>
				
							  <td>${list.VS_RESULT }</td>
							<td>${list.REG_DATE }</td>
						</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list == null }">
				<tr>
					<td colspan="5">게임한 기록이 없습니다.</td>
				</tr>				
			</c:if>
			</tbody>
			</table>
		<div id="pageNavigation" align="center" class="pagination">
		<c:if test="${totalPage == 1 }">
			<a>${totalPage }</a>
		</c:if>
		<c:if test="${ totalPage > 1 }">
		<c:set var="totalpage" value="${totalPage}"/>
		<c:set var="currentpage" value="${currentPage}"/>
			<c:forEach begin="1" end="${totalpage }" var="i">
				<c:if test="${i == currentpage }">
					<a href="${pageContext.request.contextPath}/history?page=${i}" class="pageNo"><strong>${i}</strong></a>
				</c:if>
				<c:if test="${i != currentpage }">
				<a href="${pageContext.request.contextPath}/history?page=${i}" class="pageNo">${i}</a>
				</c:if>
			</c:forEach>
		</c:if>
		
		</div>
		</div>
		<jsp:include page="../layout/bottom.jsp" flush="false"/> 
	</div>
</body>
</html>