<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reports Admin</title>
<style type="text/css">
tr.shade{
background-color: #dddddd;
}

</style>

</head>
<body>
<h1>Boards admin</h1>
<a href="${pageContext.request.contextPath}/mw/admin">Admin home</a>
<br/>

<a href="${pageContext.request.contextPath}/mw/auth/logout">Log out 1</a>

<p>
<span id="err" style="color:red">${error}</span>
</p>

			<form:form method="post" action="${pageContext.request.contextPath}/mw/admin/boards/confirmChangeOwnership"
					modelAttribute="boardOwnershipForm">
				<form:hidden path="boardId"/>
				<form:hidden path="ownerId" placeholder="id of new owner" />
				<h3>Change ownership of board:</h3>
				Board name: ${boardOwnershipForm.boardName}<br/>
				Transferring from: ${boardOwnershipForm.previousOwnerName}<br/>
				Transferring to: ${boardOwnershipForm.newOwnerName}<br/>
				<c:choose>
					<c:when test="${boardOwnershipForm.newOwnerBrands == null}">
						${boardOwnershipForm.newOwnerName} has no brands to select so default styling will be used.
					</c:when>
					<c:otherwise>
						<label for="newOwnerBrand">Select which of ${boardOwnershipForm.newOwnerName} brands to use:</label>
						<form:select path="newOwnerBrand" itemLabel="name" itemValue="id" items="${boardOwnershipForm.newOwnerBrands}" multiple="false" size="1"></form:select>
					</c:otherwise>
				</c:choose>	
				<br/>
				<input type="submit" value="Confirm changes"/>
			</form:form>


</body>
</html>