<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
    <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post" data-ajax="false"  >
	    <span>${error}</span>
	    <ul data-role="listview" data-inset="true" class="loginStack">
	      <li>
	      <!--[if IE]>
		  <label for="username">Username</label>
	      <![endif]-->
	      <input id="j_username" name="j_username" type="text" placeholder="Username"/>
	      </li>
	      <li>
	      <!--[if IE]>
		  <label for="pwd">Password</label>
	      <![endif]-->
	      <input id="j_password" name="j_password" type="password" placeholder="Password"/>
	      </li>
	    </ul>
	    <p class="para-line"><span id="login_msg" style="float:left"></span>
	      <label class="extraOptions" for="keepin">Keep me signed in</label>
	      <input type="checkbox" name="_spring_security_remember_me" checked="checked"/>
	    </p>
	    <div class="greenOnBtn">
	      <input type="submit" value="Login"/> 
	    </div>
	</form>