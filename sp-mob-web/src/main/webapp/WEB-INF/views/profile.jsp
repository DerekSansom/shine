<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html manifest="cache.appcache">
<head>
	<jsp:include page="head/head.jsp"></jsp:include>
</head>

<body>

<div data-role="page" data-ajax="false" id="" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
     <!--[if IE]>
     <div class="headerBack"><h1 id="uprofilename">User Profile</h1></div>
     <![endif]-->
		<h1 id="uprofilename">User Profile</h1>
		<a href="#" data-rel="back" data-theme="a">back</a>
	</div><!-- /header -->
 <span>${msg}</span>

  <div data-role="content">
	<div class="loading-on upload-on" id="loading" style="display:none">
		<h3><small>Uploading Image</small> <span class="ui-icon-loading"></span></h3>
	</div>

<form:form data-ajax="false" method="POST" action="${pageContext.request.contextPath}/mw/profile/update"  modelAttribute="profileUpdate" enctype="multipart/form-data">

	<jsp:include page="forms/update-profile.jsp"></jsp:include>
</form:form>


  </div>
</div>

<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>

