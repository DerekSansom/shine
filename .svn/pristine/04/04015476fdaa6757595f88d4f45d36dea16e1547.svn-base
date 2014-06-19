<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

   	<form:form method="POST" action="${pageContext.request.contextPath}/mw/post/report" modelAttribute="reportForm" data-ajax="false">
	    <form:errors element="span" class="error"/>
	    <form:hidden path="noticeId"/>
	    <form:hidden path="replyId"/>
	    <form:hidden path="noticeTitle"/>
	      <p class="note1" id="postttl">${reportForm.noticeTitle}</p>
	      <div class="loginStack">
	        <div class="areaField">
			  <!--[if IE]>
			  <label for="txtpostreply">Reason...</label>
			  <![endif]-->
				<form:textarea path="reason" placeholder="Reason..."/><form:errors path="reason" element="span" class="error"/>
	        </div>
	      </div>
	      <div class="greenOnBtn">
	        <input type="submit" value="Report"/>
	      </div>
    </form:form>

