<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>Shine</title>

</head>

<body>
	<h2>Shine</h2>

	<h3><c:out value="${player.username}"></c:out> Registered </h3>
Still in development!

<ul>
<li><h3>Test pages:</h3></li>

</ul>
<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>

