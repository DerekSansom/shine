<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<h1>Boards locations</h1>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>
		
		<h3>Log results</h3>
		<ul>
			<c:forEach items="${logs}" var="message">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</body>
</html>