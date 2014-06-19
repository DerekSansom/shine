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
<span id="err" style="color:red">${error}</span>
</p>

				<h3>Ownership of board changed:</h3>
				<em>Board name:</em> ${boardOwnershipForm.boardName}<br/>
				<em>Transferred from:</em> ${boardOwnershipForm.previousOwnerName}<br/>
				<em>Transferred to:</em> ${boardOwnershipForm.newOwnerName}<br/>
				<em>New Brand:</em> ${boardOwnershipForm.newOwnerName}
</body>
</html>