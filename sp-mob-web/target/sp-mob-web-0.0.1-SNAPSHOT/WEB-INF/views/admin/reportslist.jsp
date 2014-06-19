<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="adminHead.jsp"></jsp:include>
<body>
<h1>Reports admin here</h1>
<jsp:include page="adminNav.jsp"></jsp:include>
<p>
<span id="err" style="color:red">${error}</span>
</p>

<table>
<tr class="shade">
	<th>
		Id:
	</th>
	<th>
		Reported By:
	</th>
	<th>
		Date:
	</th>
	<th>
		Notice Id:
	</th>
	<th>
		Reply Id:
	</th>
	<th>
		Offending entry:
	</th>
	<th>
		Reason:
	</th>
	<th>
	</th>
</tr>

<c:forEach items="${pendingReports}" var="pendingReport" varStatus="index">
	<tr <c:if test="${index.index%2==1}">class="shade"</c:if>>
		<td>
			<c:out value="${pendingReport.id}"/>
		</td>
		<td>
			<c:out value="${pendingReport.reporterId}"/>
		</td>
		<td>
			<c:out value="${pendingReport.created}"/>
		</td>
		<td>
			<c:out value="${pendingReport.noticeId}"/>
		</td>
		<td>
			<c:out value="${pendingReport.replyId}"/>
		</td>
		<td>
			<c:out value="${pendingReport.offendingEntry}"/>
		</td>
		<td>
			<c:out value="${pendingReport.reason}"/>
		</td>
		<td>
			<form method="post" action="${pageContext.request.contextPath}/mw/admin/handleReport">
				<input type="hidden" name="reportId" value="${pendingReport.id}"/>

				<input type="text" name="rationale" placeholder="rationale for accepting/rejecting"/>
<br/>				
				<label for="reject">Reject</label> 
				<input type="radio" name="decision" id="reject" value="reject" />
<br/>	
				<label for="accept">Accept</label> 
				<input type="radio" name="decision" id="accept" value="accept" />
<br/>	
				<input type="submit"/>
			</form>

		</td>
	</tr>


</c:forEach>
</table>

</body>
</html>