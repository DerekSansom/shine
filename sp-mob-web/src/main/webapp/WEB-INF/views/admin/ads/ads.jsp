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
							<th>Expiry</th>
							<th>Actions</th>
						</tr>
					
					
						<c:forEach items="${adverts}" var="advert">
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
									<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/default">
										<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Set As Default">
										Use As Default
									</a>
								</td>
							</tr>
					</c:forEach>
		</table>

	</body>
</html>