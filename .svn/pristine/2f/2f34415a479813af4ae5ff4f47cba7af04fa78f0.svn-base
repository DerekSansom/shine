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
				active: 3
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
			<h1 class="sp-page-header-h1">New advert</h1>
		</div>

		<div class="sp-page-content">
			<form method="POST" action="${pageContext.request.contextPath}/mw/portal/addAdvert">
				<input type="hidden" id="lat" name="lat">
				<input type="hidden" id="lng" name="lng">	
				<table>
					<tr>
						<td>
							<label for="displayname">Advert name</label>
						</td>
						<td><input id="displayname" name="displayname" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label for="brand">Brand</label></td>
						<td>
							<c:choose>
								<c:when test="${empty result.userBrands }">
										No brands.
								</c:when>
								<c:otherwise>
									<select id="brand" name="brand">
										<c:forEach items="${result.userBrands}" var="brand">
											<option
												data-bgImage="${brand.backgroundimg}"
												data-logo="${brand.logo}"
												data-textCol="${brand.textColour}"
												data-bgCol="${brand.bgColour}"
												value="${brand.id}">
											  	${brand.name}</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>				
					<tr>
						<td><label for="catid">Category name</label></td>
						<td>
							<select id="catid" name="catid">
								<option value=""></option>
								<c:forEach items="${result.adCategories}" var="adCategory">
									<option	adcategory-code="${adCategory.code}"
											  		  value="${adCategory.id}">
															 ${adCategory.category}
									</option>
								</c:forEach>
							</select>
						</td>					
					</tr>
					<tr>
						<td><label for="title">Title</label></td>
						<td><input id="title" name="title" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label for="text">Your text</label></td>
						<td><textarea id="text" name="text" class="inputbox"></textarea></td>
					</tr>
					<tr>
						<td><label>Add an image</label></td>
						<td>
							<input id="image_upload" name="image_upload" type="file">
							<input type="hidden" id="image" name="image" value="">
						</td>
					</tr>
					<tr>
						<td><label for="phoneno">Contact phone</label></td>
						<td><input id="phoneno" name="phoneno" value="" class="inputbox"></td>
					</tr>	
					<tr>
						<td><label for="email">Contact E-mail</label></td>
						<td><input id="email" name="email" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label for="url">Offer URL</label></td>
						<td><input id="url" name="url" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td>
							<span id="location_status">Location not selected</span>
							<a href="#" class="location_select">
								<img src="${pageContext.request.contextPath}/img/location_icon.png" alt="Select Location" title="Select Location" style="vertical-align:middle">
							</a>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Save" class="btn">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>
	</div>
</body>
</html>