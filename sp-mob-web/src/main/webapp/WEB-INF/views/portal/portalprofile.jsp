<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shine.boards.Report"%>
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
			<h1 class="sp-page-header-h1">Your profile</h1>
		</div>

		<div class="sp-page-content">
			<form method="POST" action="${pageContext.request.contextPath}/mw/portal/profile" >
				<input type="hidden" id="lat" name="lat" value="">
				<input type="hidden" id="lng" name="lng" value="">
				<fieldset>
					<legend>Your details</legend>
					<table>
						<tr>
							<td><label for="category">Business category</label></td>
							<td>
								<select id="category" name="category">
									<option  value="2">Food &amp; drink</option>
									<option  value="3">Services</option>
									<option  value="4">Events</option>
									<option  value="8">Jobs</option>
									<option  value="5">Leisure</option>
									<option  value="6">Travel</option>
									<option  value="7">Shopping</option>
									<option  value="9">Home &amp; Garden</option>								
								</select>
							</td>
						</tr>
						
						<!-- 
						<tr id="competitor" style="display:none">
							<td class="label"><label>Do you sell flights or accommodation?</label></td>
							<td>
								<label for="comp_yes">Yes</label><input id="comp_yes" type="radio" name="competitor" value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label for="comp_no">No</label><input id="comp_no" type="radio" name="competitor" value="0" selected>
								
								<p id="competitor_sorry" style="display:none">We're sorry, but due to an exclusive contact with STA Travel we are currently
								unable to offer you the ability to set up your own board. Please feel free to
								advertise on our own boards which are set up in appropriate locations.</p>
							</td>
						</tr>
						 -->
						 
						<tr>
							<td><label for="cname">Company name</label></td>
							<td><input id="cname" name="cname"></td>
						</tr>
						<tr>
							<td><label for="description">Company description</label></td>
							<td><textarea id="description" name="description"></textarea></td>
						</tr>
						<tr>
							<td><label for="phone">Telephone</label></td>
							<td><input id="phone" name="phone"></td>
						</tr>
						<tr>
							<td><label for="email">E-mail</label></td>
							<td><input id="email" name="email"></td>
						</tr>
						<tr>
							<td><label>Your location</label></td>
							<td>
								<span id="location_status">Location not selected</span>
								<a href="#" class="location_select">
									<img src="${pageContext.request.contextPath}/img/location_icon.png" alt="Select Location" title="Select Location" style="vertical-align:middle">
								</a>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Save"></td>
						</tr>							
					</table>
				</fieldset>
				<fieldset>
					<legend>Password</legend>
					<table>
						<tr>
							<td><label for="newpass">New password</label></td>
							<td><input id="newpass" name="newpass" type="password"></td>
						</tr>
						<tr>
							<td><label for="newpass2">New password again</label></td>
							<td><input id="newpass2" name="newpass2" type="password"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Save"></td>
						</tr>
					</table>
				</fieldset>
			</form>		
		</div>
		
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>

	</div>
</body>
</html>