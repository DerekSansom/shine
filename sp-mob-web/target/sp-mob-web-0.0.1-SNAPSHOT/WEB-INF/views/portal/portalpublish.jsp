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
	<script type='text/javascript'>
		$(document).ready(function() {
			$("#accordion").accordion({
				active: 0 
				}
			);
		});
	</script>	

	<title>StreetPin Portal</title>

</head>
<body>
	<jsp:include page="portalleftsection.jsp"></jsp:include>

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
			<h4>1. Design your brand:</h4>
			<ul>
				<li>select a background colour or image</li>
				<li>add your logo</li>
				<li>select your text colour</li>
				<li>select your link colour</li>
				<li>add your default url</li>
			</ul>

			<p>Once saved, you can use this template for all your boards</p>

			<h4>2. Create, edit or delete your boards:</h4>

			<ul>
				<li>select a brand position it on a map (these tailored
					boards can overlap existing boards)</li>
				<li>select any competing industry that you'd rather not
					advertise on your board (if required)</li>
				<li>publish right away, or save as draft and return later</li>
			</ul>
			<h4>3. Create, edit or delete your adverts</h4>

			<ul>
				<li>select a category</li>
				<li>add your title &amp; text</li>
				<li>add your contact details - phone, web, email, location</li>
				<li>once saved, visit 'existing adverts' to post this advert
					on your chosen board</li>
			</ul>

			<h4>4. Credits</h4>

			<p>Credits will be charged for adding boards and posts, or use
				an appropriate voucher code.</p>
			<p>As a launch special, we are pleased to offer the following
				free vouchers:</p>

			<ul>
				<li>for a free advert please use <b>FREEADS1</b></li>
				<li><b>To trial a branded Pin Board, FREE for 6 months,
						please use FREEBOARDS1</b></li>
			</ul>

			<p>NB PLEASE ADD AN ADVERT OR BOARD TO YOUR CART ON THE ‘ADD
				CREDIT’ SCREEN, THEN USE THIS VOUCHER TO OBTAIN YOUR FREE PRODUCT</p>

			<p>PLEASE CONTACT HELLO@STREETPIN.COM FOR LARGER REQUIREMENTS</p>

			<p>We operate a 'local newspaper' style of advertising payment.
				It's a cheap and cheerful &pound;1 per week, per board.
				Advertising can be bought as a one off or cheaper credits if buy
				in bulk.</p>
	
		</div>
		
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>

	</div>
</body>
</html>