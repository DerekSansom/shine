<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--CSS-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

<title>Streetpin Landing Page</title>

</head>
<body>
<h1>StreetPin Landing page home</h1>

<p>
<span id="err" style="color:red">${error}</span>
</p>
<p class="blue">This is styled blue</p>



<ul>

<li><a href="${pageContext.request.contextPath}/mw/admin/boards">boards</a></li>
<li><a href="${pageContext.request.contextPath}/mw/admin/reports">reports</a></li>

</ul>


</body>
</html>