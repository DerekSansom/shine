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

		<h2>Advert options:</h2>

<div id="ad">				
		<h3><c:out value="${advert.displayname}"/></h3>
		<p>Title: <span><c:out value="${advert.title}"/></span></p>
		<p>Expires: <span><fmt:formatDate pattern="dd MMM yyyy k:mm" value="${advert.expires}"/></span></p>
		
</div>
<h4>Use this advert as default for:</h4>
		<table class="sp-table-even-rows">
						<tr>
							<th/>
							<th/>
							<th>Current Default Ads</th>
							<th/>
						</tr>


							<tr>
								<td>
									<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/globalDefault">Use as global default</a>
								</td>
								<td>
									<c:out value="${country.code}"/>
								</td>
								<td>
									<ul>
										<c:when test="${empty result.adverts }">None</c:when>
										<c:otherwise>
											<c:forEach items="${country.defaultAdParams}" var="defaultAdParams">
												<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
												</li>
											</c:forEach>
										</c:otherwise>
									</ul>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/country/${country.id}/set">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Target">Set as default
									</a>
								</td>
							</tr>					
					
						<c:forEach items="${countries}" var="country">
							<tr>
								<td>
									
									<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/country/${country.id}"><c:out value="${country.name}"/></a>
								</td>
								<td>
									<c:out value="${country.code}"/>
								</td>
								<td>
									<ul>
										<c:when test="${empty result.adverts }">None</c:when>
										<c:otherwise>
											<c:forEach items="${country.defaultAdParams}" var="defaultAdParams">
												<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
												</li>
											</c:forEach>
										</c:otherwise>
									</ul>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/country/${country.id}/set">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Target">Set as default
									</a>
								</td>
							</tr>
					</c:forEach>
		</table>

	</body>
</html>