<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="../adminHead.jsp"></jsp:include>
	<body>
		<jsp:include page="../adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>


<h2>Advert "${advert.displayname}"</h2>


<div id="ad">				
		<p><strong>Details for ad ${advert.id}</strong></p>
		<p>Name: <span><c:out value="${advert.displayname}"/></span></p>
		<p>Title: <span><c:out value="${advert.title}"/></span></p>
		<p>Text: <span><c:out value="${advert.text}"/></span></p>
		<p>categoryId: <span><c:out value="${advert.categoryId}"/></span></p>
		<p>Expires: <span><fmt:formatDate pattern="dd MMM yyyy k:mm" value="${advert.expires}"/></span></p>
</div>


<div id="global">				
<h3>Global default ads:</h3>
	<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/global/0/set">Use this advert as a global default</a>
	<c:choose>
		<c:when test="${empty global }">None</c:when>
		<c:otherwise>
			<ul>
				<c:forEach items="${global}" var="defaultAdParams">
					<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>

	<p>To edit global ads (i.e. remove any) go <a href="${pageContext.request.contextPath}/mw/admin/locations/countries">here</a></p>
	
</div>



<div id="countries">				
<h4>Use this advert as default for:</h4>
		<table class="sp-table-even-rows">
						<tr>
							<th/>
							<th/>
							<th>Current Default Ads</th>
							<th/>
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
										<c:choose>
											<c:when test="${empty country.defaultAdParams }"><p>no adverts set as default</p></c:when>
											<c:otherwise>
												<c:forEach items="${country.defaultAdParams}" var="defaultAdParams">
													<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
													</li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
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
		
		<p>To edit country ads (i.e. remove any) go <a href="${pageContext.request.contextPath}/mw/admin/locations/countries">here</a></p>
		
</div>
	</body>
</html>