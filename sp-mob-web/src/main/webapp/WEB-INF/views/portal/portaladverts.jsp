<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<h1>Your adverts</h1>
		</div>

		<div class="sp-page-content">
					<c:choose>
				<c:when test="${empty result.adverts }">
					No Adverts.
				</c:when>
				<c:otherwise>
					<table class="sp-table-even-rows">
						<tr>
							<th>Advert name</th>
							<th>Title</th>
							<th>Expiry</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${result.adverts}" var="advert">
							<tr>
								<td>
									<c:out value="${advert.displayname}"/>
								</td>
								<td>
									<c:out value="${advert.title}"/>
								</td>
								<td>
									<fmt:formatDate pattern="dd MMM yyyy k:mm" value="${advert.expires}"/>
								</td>
								<td>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/portal/adverts/${advert.id}">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/pencil.png" alt="Edit">
										Edit
									</a>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/portal/adverts/${advert.id}/delete">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="Delete">
										Delete
									</a>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/portal/adverts/${advert.id}/target">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Target">
										Target
									</a>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/portal/adverts/${advert.id}/duplicate">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/document-copy.png" alt="Duplicate">
										Duplicate
									</a>
								</td>
							</tr>
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${result.start != null}">
				<div id="pagination">
					<c:if test="${result.start > 1 }">
						<a href="?start=${result.start - result.boardCount}">prev</a>
					</c:if>
				
					showing ${result.start} to ${result.start + result.boardCount -1} of ${result.totalBoards} notice boards</div>
					<c:if test="${result.start + result.boardCount - 1 < result.totalBoards }">
						<a href="?start=${result.start + result.boardCount}">next</a>
					</c:if>
			</c:if>
		</div>
		
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>

	</div>
</body>
</html>