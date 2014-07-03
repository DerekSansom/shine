<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<h1>Adverts</h1>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>

			<table class="sp-table-even-rows">
						<tr>
							<th>Advert name</th>
							<th>Title</th>
							<th>Profile</th>
							<th>Expiry</th>
							<th>Text</th>
							<th>IconUrl</th>
							<th>ImageUrl</th>
							<th>Category</th>
							<th>Brand</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${adverts}" var="advert">
							<tr>
								<td>
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/default">
										<c:out value="${advert.displayname}"/>
									</a>
								</td>
								<td>
									<c:out value="${advert.title}"/>
								</td>
								<td>
									<c:out value="${advert.profile}"/>
								</td>
								<td>
									<fmt:formatDate pattern="dd MMM yyyy k:mm" value="${advert.expires}"/>
								</td>
								<td>
									<c:out value="${advert.text}"/>
								</td>
								<td>
									<c:out value="${advert.iconUrl}"/>
								</td>
								<td>
									<c:out value="${advert.imageUrl}"/>
								</td>
								<td>
									<c:out value="${advert.categoryId}"/>
								</td>
								<td>
									<c:if test="${not empty advert.brand}"></c:if>
										<ul>
											<li><c:out value="${advert.brand.corporateId}"/></li>
											<li><c:out value="${advert.brand.name}"/></li>
											<li><c:out value="${advert.brand.url}"/></li>
										</ul>
								</td>
								<td style="width:20%">
									<ul>
										<li>
											<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/default">
												<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Set As Default">
												Use As Default
											</a>
										</li>
									</ul>
								</td>
							</tr>
					</c:forEach>
		</table>

	</body>
</html>