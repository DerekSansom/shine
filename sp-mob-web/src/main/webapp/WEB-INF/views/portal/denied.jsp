<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<!--  ALL SP JSP -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/portal_style.css" rel="stylesheet"/>
	<title>StreetPin Portal Login</title>

</head>
<body>

	<div id="container-right">

		<div class="sp-page-header">
			<h1>Getting started</h1>
		</div>
		<div class="sp-page-content">
			<h4>
				Please note, we are in Beta (test) mode at the moment - if you
				have any issues or questions, please mail <a
					href="mailto:hello@streetpin.com">hello@streetpin.com</a> and
				we'll get you set up.
			</h4>
		
			<h1>Access Denied!</h1>
			<p>you do not have enough rights to access this page!</p>
			<p><a href="${pageContext.request.contextPath}/mw/portal/auth/logout">Log out</a> to log in as someone else</p>
			<p>or go to portal <a href="${pageContext.request.contextPath}/mw/portal">home</a></p>
		
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>
		</div>
	</div>
</body>
</html>