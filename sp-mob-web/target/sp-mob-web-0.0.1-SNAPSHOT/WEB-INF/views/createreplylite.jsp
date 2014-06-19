<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html manifest="cache.appcache">
<head>
</head>

<body>
<div data-role="page" id="reply" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
	     <!--[if IE]>
	     <div class="headerBack"><h1>New Reply</h1></div>
	     <![endif]-->
			<h1>New Reply</h1>
			<a href="${pageContext.request.contextPath}/mw/notice?id=${post.id}" data-rel="back" data-theme="a">back</a>
			<a href="${pageContext.request.contextPath}/mw/menu" data-theme="b" data-transition="slide">Cancel</a>
	</div><!-- /header -->




  <div data-role="content">
  	<span id="postrepErr"></span>
	<jsp:include page="forms/create-reply.jsp"></jsp:include>

  </div>
</div>
<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>

