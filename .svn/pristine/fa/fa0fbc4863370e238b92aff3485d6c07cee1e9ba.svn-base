<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				active: 1 
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
			<h1>Your brands</h1>
		</div>

		<div class="sp-page-content">
			<c:choose>
				<c:when test="${empty result.brands }">
					No brands.
				</c:when>
				<c:otherwise>
					<table class="sp-table-even-rows">
						<tr>
							<th>Brand name</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${result.brands}" var="brand">
							<tr>
								<td>
									<c:out value="${brand.name}"/>
								</td>
								<td>
									<!--  
									<a class="action_btn" href="/publish/brands/168"><img src="${pageContext.request.contextPath}/img/fugue/icons/pencil.png" alt="Edit"> Edit</a>
									-->								
									<a class="action_btn" href="/publish/brands/${brand.id}">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/pencil.png" alt="Edit">
										Edit
									</a>
									<a class="action_btn" href="/publish/brands/${brand.id}/delete">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="Delete">
										Delete
									</a>
								</td>
							</tr>
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>

	</div>
</body>
</html>