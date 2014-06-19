<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="../head/headlite.jsp"></jsp:include>
<style>
a {
    color: #000;
}
</style>
<body>


<div data-role="page" class="greenCall" id="home">
  <div data-role="header">
    <div class="ui-grid-a">
      <div class="ui-block-a"><a href="#" class="logo" title="Logo"><img src="images/streetpin-logo-notext100.png" alt="logo" /></a></div>
      <div class="ui-block-b"><strong>Welcome to StreetPin</strong><small>Connecting the world,</small> <small> one community at a time</small> </div>
    </div>
  </div>

  <div data-role="content">
  
	<sec:authorize access="isAuthenticated()">
 	  	<h1>Welcome <sec:authentication property="principal.username" />, you're logged in and ready to go</h1>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
    	<h1>you're NOT logged in</h1>
	</sec:authorize>
	
    <h2>This is where all the map gubbins and stuff will go</h2>

<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/mw/auth/logout">Log out with curl</a>
 
</sec:authorize>
  
  
  </div>
  
   <a href="${pageContext.request.contextPath}/nowhere">a broken link</a>
  
  
</div>

</body>
</html>