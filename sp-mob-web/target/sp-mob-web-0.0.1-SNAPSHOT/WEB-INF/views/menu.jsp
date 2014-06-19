<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html manifest="cache.appcache">
<head>
	<jsp:include page="head/head.jsp"></jsp:include>
</head>

<body>
<div data-role="page" id="menu" class="greenCall">
  <div data-role="header" data-position="fixed" class="greenohead">

     <!--[if IE]>
     <div class="headerBack"><h1>Menu</h1></div>
     <![endif]-->
    <h1>Menu</h1>
    <a href="#" data-rel="back" data-theme="a">back</a>


  </div>

  <!-- /header -->

  <div class="content" data-role="content">
    <div class="ui-grid-b menu-grid">
      <div class="ui-block-a block ieHide"> <a href="${pageContext.request.contextPath}/mw/map#landgrab" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-boards.png" alt="image1" /> <span class="right-text">create board</span> </a> </div>
      <div class="ui-block-b block"> <a href="${pageContext.request.contextPath}/mw/map#controls" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-search-button.png" alt="image1" /> <span class="right-text">find a board</span> </a> </div>
      <div class="ui-block-c block"> <a href="${pageContext.request.contextPath}/inbox" data-ajax="false" data-role="none" data-transition="slide"> <span class="promptNum" style="display:none"></span> <img src="${pageContext.request.contextPath}/static/images/image-mail.png" alt="image1" /> <span class="right-text">mail</span> </a> </div>
      <div class="ui-block-a block"> <a href="${pageContext.request.contextPath}/favourites" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-favourites.png" alt="image1" /> <span class="right-text">favourites</span> </a> </div>
      <div class="ui-block-b block"> <a href="${pageContext.request.contextPath}/mw/myprofile" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-profile.png" alt="image1" /> <span class="right-text">profile</span> </a> </div>
      <div class="ui-block-c block"> <a href="${pageContext.request.contextPath}/mw/howTo.html" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-howto.png" alt="image1" /> <span class="right-text">how to</span> </a> </div>
      <div class="ui-block-a block"> <a href="${pageContext.request.contextPath}/mw/trends" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-trending.png" alt="image1" /> <span class="right-text">trending</span> </a> </div>
      <div class="ui-block-b block"> <a href="${pageContext.request.contextPath}/mw/settings" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-settings.png" alt="image1" /> <span class="right-text">settings</span> </a> </div>
      <div class="ui-block-c block"> <a href="${pageContext.request.contextPath}/mw/terms" data-ajax="false" data-role="none" data-transition="slide"> <img src="${pageContext.request.contextPath}/static/images/image-terms.png" alt="image1" /> <span class="right-text">terms</span> </a> </div>
    </div>
    <!-- /grid-a -->
  </div>
</div>

<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>

