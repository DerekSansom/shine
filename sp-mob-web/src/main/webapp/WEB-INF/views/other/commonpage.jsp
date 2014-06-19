<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Common Page</h1>
<p>Unauthenticated users have access to this page.</p>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ac velit et ante 
laoreet eleifend. Donec vitae augue nec sem condimentum varius.</p>

<a href="${pageContext.request.contextPath}/mw/auth/logout">Log out with curl</a>
<a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out with jsec</a>

</body>
</html>