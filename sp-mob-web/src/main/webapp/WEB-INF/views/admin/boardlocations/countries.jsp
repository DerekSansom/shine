<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<h1>Locations and default ads</h1>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>
		

<p>use this page to remove default ads from a location</p>	
<p>To add default adverts first select the <a href="${pageContext.request.contextPath}/mw/admin/adverts">advert</a></p>
	
	
	<div id="global">	
		<h3>Global default ads:</h3>
		<p>These are the adverts shown by default on boards that do not yet have a location</p>
		
	<c:choose>
		<c:when test="${empty global}"><p>There are no global default ads</p></c:when>
		<c:otherwise>
		
				<table class="sp-table-even-rows">
						<tr>
							<th>Id</th>
							<th>Title</th>
							<th>Actions</th>
						</tr>
		
				<c:forEach items="${global}" var="defaultAdParams">
						<tr>
				
				
					<td><c:out value="${defaultAdParams.advert.id}"/></td>
					<td><c:out value="${defaultAdParams.advert.title}"/></td>
					<td><a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/adverts/${defaultAdParams.advert.id}/global/0/remove">
						<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="AutoLocate">
						Remove
					</a></td>
					
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
		</div>
	
	<div id="countries">	

<h3>Default ads per country:</h3>

		<table class="sp-table-even-rows">
						<tr>
							<th>Country</th>
							<th>id</th>
							<th>Code</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${countries}" var="country">
							<tr>
								<td>
									
									<a href="${pageContext.request.contextPath}/mw/admin/locations/country/${country.id}/defaultads"><c:out value="${country.name}"/></a>
								</td>
								<td>
									<c:out value="${country.id}"/>
								</td>
								<td>
									<c:out value="${country.code}"/>
								</td>
								<td>
									<ul>
										<c:forEach items="${country.defaultAdParams}" var="defaultAdParams">
											<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
											<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/locations/country/${country.id}/defaultad/${defaultAdParams.adId}/remove">
											<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="AutoLocate">
												Remove
											</a>
											</li>
										</c:forEach>
									</ul>
								</td>
							</tr>
					</c:forEach>
		</table>
</div>
	</body>
</html>