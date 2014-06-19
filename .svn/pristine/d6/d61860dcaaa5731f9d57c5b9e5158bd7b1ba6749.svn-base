<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

   	<form:form method="POST" action="${pageContext.request.contextPath}/mw/post/reply" modelAttribute="replyForm" data-ajax="false">
	    <form:errors element="span" class="error"/>
	    <form:hidden path="noticeId"/>
	    <form:hidden path="noticeTitle"/>
	      <p class="note1" id="postttl">${replyForm.noticeTitle}</p>
	      <div class="loginStack">
	        <div class="areaField">
			  <!--[if IE]>
			  <label for="txtpostreply">Your Reply...</label>
			  <![endif]-->
				<form:textarea path="reply" placeholder="Your Reply..."/><form:errors path="reply" element="span" class="error"/>
	        </div>
	      </div>
	      <div class="greenOnBtn">
	        <input type="submit" value="Reply"/>
	      </div>
    </form:form>

