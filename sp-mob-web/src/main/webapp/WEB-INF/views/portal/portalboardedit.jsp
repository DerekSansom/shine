<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sp.portal.boards.BoardEditResult"%>
<%@ page import="com.shine.boards.CorpBrand"%>
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
				active: 2 
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
			<h1>Edit board</h1>
		</div>

		<div class="sp-page-content">
					<form action="" method="POST" id="board_form">
				<input type="hidden" id="lat" name="lat" class="inputbox">
				<input type="hidden" id="lng" name="lng" class="inputbox">
				<input type="hidden" id="country" name="country" value="">
				<input type="hidden" id="location" name="location" value="">
				<input type="hidden" id="area" name="area" value="">

				<table>
					<c:set var="board" value="${result.board}"/>
					<tr>
						<td><label for="name">Name</label></td>
						<td><input id="name" name="name" value="${board.name }" class="inputbox"></td>
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
					<!--
					<tr>
						<td><label for="bannerimg">Banner image</label></td>
						<td>
							<input id="bannerimg_upload" name="bannerimg_upload" type="file">
							<input type="hidden" id="bannerimg" name="bannerimg" value="">							
						</td>
					</tr>
					<tr>
						<td><label for="bannerurl">Banner URL</label></td>
						<td>
							<input id="bannerurl" name="bannerurl" value="" class="inputbox">
							<div id="bannerimg_img"></div>
						</td>
					</tr>
					 -->
					<tr>
						<td><label>Location</label></td>
						<td>
							<span id="location_status">Location not selected</span>
							<a href="#" class="location_select">
								<img src="/publish/img/location_icon.png" alt="Select Location" title="Select Location" style="vertical-align:middle">
							</a>
						</td>
					</tr>
					<tr>
						<td><label for="catid">Exclude these adverts</label></td>
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
						<td></td>
						<td>
							<p>
								<input type="submit" value="Publish" class="btn">
								<input type="submit" value="Save Draft" class="btnalt" id="save_draft">
							</p>
							
							<p>Clicking <em>Publish</em> will decrement one noticeboard credit from your account.<br>Current credit balance: <b>XX</b></p>
							
							<p class="share_notice"><strong>NB:</strong> Clicking Publish will publish your board and also provide a social media link, if you don't want to make this public,
							please click the 'x' - your board/advert will be saved either way</p>
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