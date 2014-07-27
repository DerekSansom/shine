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
	<c:choose>
		<c:when test="${empty global }">No global default adds set</c:when>
		<c:otherwise>
			<p>Current default global ads</p>
			<ul>
				<c:forEach items="${global}" var="defaultAdParams">
					<li>
						<c:choose>
							<c:when test="${defaultAdParams.advert.id == advert.id}">
								<c:set var="current">true</c:set>
								<strong>* <c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
								
									<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/global/0/remove">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="remove">Remove
									</a>
								</strong>
							</c:when>
							<c:otherwise>
								<c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${current}">
				<c:set var="current">false</c:set>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/global/0/set">
			<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="set global">Use this advert as a global default</a>
		</c:otherwise>
	</c:choose>

	<p>To edit global ads (i.e. remove any) go <a href="${pageContext.request.contextPath}/mw/admin/locations/countries">here</a></p>
	
</div>



<div id="countries">				
<h3>Country default ads:</h3>
		<table class="sp-table-even-rows">
				<tr>
					<th/>
					<th>Current Default Ads</th>
					<th/>
				</tr>
			
				<c:forEach items="${countries}" var="country">
					<c:set var="location" scope="request" value="${country}"/>
					<jsp:include page="locationf.jsp"/>
											
			</c:forEach>
		</table>
		
		<p>To edit country ads (i.e. remove any) go <a href="${pageContext.request.contextPath}/mw/admin/locations/countries">here</a></p>
		
</div>
	</body>
</html>