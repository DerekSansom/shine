<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>SteetPin</title>
<jsp:include page="../head/head.jsp"></jsp:include>

<body>


<div data-role="page" class="greenCall" id="home">
  <div data-role="header">
    <div class="ui-grid-a">
      <div class="ui-block-a"><a href="#" class="logo" title="Logo"><img src="images/streetpin-logo-notext100.png" alt="logo" /></a></div>
      <div class="ui-block-b"><strong>Welcome to StreetPin</strong><small>Connecting the world,</small> <small> one community at a time</small> </div>
    </div>
  </div>

  <div data-role="content">
    	<h1>404 </h1>

 	<sec:authorize access="isAuthenticated()">
 	  	<h2>Sorry <sec:authentication property="principal.username" /></h2>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
    	<h2>Sorry! </h2>
	</sec:authorize>
	
    <h2>Something or other couldn't be found to complete your request</h2>

<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/mw/">Back to home page</a>

</sec:authorize>
<jsp:include page="../head/analytics.html"></jsp:include>
</body>
</html>