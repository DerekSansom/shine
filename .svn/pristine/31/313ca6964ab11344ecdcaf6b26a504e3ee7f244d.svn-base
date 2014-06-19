<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<jsp:include page="adminHead.jsp"></jsp:include>
<body>
<h1>Boards admin</h1>

<jsp:include page="adminNav.jsp"></jsp:include>

<p>
<span id="err" class="error">${error}</span>
</p>

			<form:form method="post" action="${pageContext.request.contextPath}/mw/admin/boards/changeOwnership"
					modelAttribute="boardOwnershipForm">
			   <label for="boardId">Id of board to change ownership of</label>
				<form:input path="boardId" placeholder="id of board to change ownership" />
<br/>				
			   <label for="userId">Id of new owner</label>
				<form:input path="ownerId" placeholder="id of new owner" />
				<input type="submit"/>
			</form:form>


</body>
</html>