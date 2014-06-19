<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Portal</title>
<style type="text/css">

</style>

</head>
<body>
<h1>Portal Home Screen</h1>

<p>
<span id="err" style="color:red">${error}</span>
</p>



<ul>

<li><a href="${pageContext.request.contextPath}/mw/auth/logout">Log out</a></li>
<li><a href="${pageContext.request.contextPath}/mw/admin/boards">boards</a></li>
<li><a href="${pageContext.request.contextPath}/mw/admin/reports">reports</a></li>

</ul>


</body>
</html>