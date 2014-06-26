<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<jsp:include page="adminHead.jsp"></jsp:include>
	<body>
		<h1>Properties</h1>
		<jsp:include page="adminNav.jsp"></jsp:include>
		<p>
			<span id="err" style="color:red">${error}</span>
		</p>

			<table class="sp-table-even-rows">
						<tr>
							<th>Key</th>
							<th>Value</th>
							<th>Last updated</th>
							<th/>
						</tr>
					
					
						<c:forEach items="${properties}" var="property">
							<tr>
								<td>
									<c:out value="${property.key}"/>
								</td>
								<td>
									<c:out value="${property.value}"/>
								</td>
								<td>
									<fmt:formatDate pattern="dd MMM yyyy k:mm" value="${property.updated}"/>
								</td>
								<td>
									<form:form action="" method="post" modelAttribute="propertyForm">
										<form:hidden path="key" value="${property.key}"/>
										<form:input  path="value" value="${property.value}"/>
										<input type="submit"/>
									</form:form>
								</td>

							</tr>
					</c:forEach>
		</table>

	</body>
</html>