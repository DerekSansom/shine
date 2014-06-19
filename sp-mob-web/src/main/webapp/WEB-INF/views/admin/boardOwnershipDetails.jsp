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

			<form:form method="post" action="${pageContext.request.contextPath}/mw/admin/boards/confirmChangeOwnership"
					modelAttribute="boardOwnershipForm">
				<form:hidden path="boardId"/>
				<form:hidden path="ownerId" placeholder="id of new owner" />
				<h3>Change ownership of board:</h3>
				<em>Board name:</em> ${boardOwnershipForm.boardName}<br/>
				<em>Transferring from:</em> ${boardOwnershipForm.previousOwnerName}<br/>
				<em>Transferring to:</em> ${boardOwnershipForm.newOwnerName}<br/>
				<c:choose>
					<c:when test="${boardOwnershipForm.newOwnerBrands == null}">
						${boardOwnershipForm.newOwnerName} has no brands to select so default styling will be used.
					</c:when>
					<c:otherwise>
						<label for="newBrand">Select which of ${boardOwnershipForm.newOwnerName} brands to use:</label>
						<form:select path="newBrand" itemLabel="name" itemValue="id" items="${boardOwnershipForm.newOwnerBrands}" multiple="false" size="1"></form:select>
					</c:otherwise>
				</c:choose>	
				<br/>
				<input type="submit" value="Confirm changes"/>
			</form:form>


</body>
</html>