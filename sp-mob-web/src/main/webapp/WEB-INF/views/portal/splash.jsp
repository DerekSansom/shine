<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/portal_style.css" />

<title>StreetPin Portal</title>
</head>
<body>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

	<div id="container-right">

		<div id="header">
			<h1>This is portal splash page accessible to everyone</h1>
		</div>
		<div id="content">
			<h4>
				Please note, we are in Beta (test) mode at the moment - if you
				have any issues or questions, please mail 
				<a
					href="mailto:hello@streetpin.com">hello@streetpin.com</a> and
				we'll get you set up.
			</h4>
			<h4>Registration coming soon</h4>
	
		</div>
		
		<div id="footer">
			<div id="footer">&copy; Copyright 2014 streetpin</div>
		</div>
	</div>
</body>
</html>