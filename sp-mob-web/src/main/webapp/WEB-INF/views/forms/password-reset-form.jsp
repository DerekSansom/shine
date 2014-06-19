<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
 
 	<form:form method="post" action="${pageContext.request.contextPath}/mw/passwordreset" modelAttribute="passwordResetForm">
 
     <span>${error}</span>
	    <ul class="loginStack">
	      <li>
	      <!--[if IE]>
		  <label for="username">New Password</label>
	      <![endif]-->
	      <form:input path="newPassword" type="password" placeholder="new password"/>
	      </li>
	      <li>
	      <!--[if IE]>
		  <label for="pwd">Confirm Password</label>
	      <![endif]-->
	      <form:input path="confirmPassword" type="password" placeholder="confirm password"/>
	      <form:hidden path="token"/>
	      </li>
	    </ul>
	    <div class="greenOnBtn">
	      <input type="submit" value="reset password"/> 
	    </div>
	</form:form>