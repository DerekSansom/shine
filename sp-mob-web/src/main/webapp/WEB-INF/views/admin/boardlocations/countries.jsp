<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<h1>Boards locations</h1>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>

		<table class="sp-table-even-rows">
						<tr>
							<th/>
							<th>Id</th>
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
											<li><c:out value="${defaultAdParams.adId}"/>
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

	</body>
</html>