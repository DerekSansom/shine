<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<h1>Boards locations</h1>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>

		<table class="sp-table-even-rows">
						<tr>
							<th>Board name</th>
							<th>Created date</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${boards}" var="board">
							<tr>
								<td>
									<c:out value="${board.name}"/>
								</td>
								<td>
									<fmt:formatDate pattern="dd MMM yyyy k:mm" value="${board.created}"/>
								</td>
								<td>
								
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/portal/boards/${board.id}">
									<img src="${pageContext.request.contextPath}/img/fugue/icons/pencil.png" alt="Edit location">
										Edit location
									</a>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/locations/${board.id}/run">
									<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="AutoLocate">
										Run AutoLocate
									</a>
									
								</td>
							</tr>
					</c:forEach>
		</table>

	</body>
</html>