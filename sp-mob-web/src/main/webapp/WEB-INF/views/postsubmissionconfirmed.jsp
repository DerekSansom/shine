<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head/head.jsp"></jsp:include>
</head>
<body>

<div class="greenCall ui-pag ui-body-c ui-page-header-fixed ui-page-active" data-role="page" style="padding-top: 43px; min-height: 238px;">
  <div class="greenohead ui-header ui-bar-a ui-header-fixed slidedown" data-position="fixed" data-role="header" role="banner">
    <h1 class="ui-title" role="heading" aria-level="1">Create Post</h1>
    <a data-theme="a" data-rel="back" href="#" class="ui-btn-left ui-btn ui-btn-up-a ui-shadow ui-btn-corner-all" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">Cancel</span></span></a> </div>
  <!-- /header -->

  <div data-role="content" class="ui-content" role="main">
  <span id="newposterr">${error}</span>
<p>your post has been created and will soon be able to be viewed <a href="#">here</a></p>

<!-- a href="${pageContext.request.contextPath}/mw/post?id=${newnoticeid}">here</a></p-->
<p>the board it was posted too can be viewed<a href="${pageContext.request.contextPath}/mw/board?id=${bid}">here</a></p>


  </div>
</div><jsp:include page="head/analytics.html"></jsp:include>

</body>
</html>
