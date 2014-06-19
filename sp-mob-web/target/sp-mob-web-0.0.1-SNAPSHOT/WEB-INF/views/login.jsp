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

<div data-role="page" data-ajax="false" class="greenCall" id="home">
  <div class="header" data-role="header">
    <div class="ui-grid-a">
      <div class="ui-block-a"><a href="#" class="logo" title="Logo"><img src="${pageContext.request.contextPath}/static/images/streetpin-logo-notext100.png" alt="logo" /></a></div>
      <div class="ui-block-b"><strong>Welcome to StreetPin</strong><small>Connecting the world,</small> <small> one community at a time</small> </div>
    </div><!--The global network of local communities-->
    <!-- /grid-a -->
  </div>
  <div data-role="content">
	<jsp:include page="forms/login-form.jsp"></jsp:include>

    <p class="para-line"><a href="${pageContext.request.contextPath}/mw/forgottenpassword" class="grnButton" data-role="none" data-transition="slide">Forgotten username/password?</a></p>
    <p class="para-line"><a href="${pageContext.request.contextPath}/mw/registration" class="grnButton" data-role="none" class="dashBtn" data-transition="slide">Create Account</a></p>
    <div class="blkAds">
      <div class="adTxt">
      	For best experience, please download the app:<br><br>
      	<a href="https://itunes.apple.com/gb/app/streetpin/id485064718?mt=8" data-transition="slide" target="_blank"><img src="${pageContext.request.contextPath}/static/images/iphone-download-small.png" alt="iphone-download" /></a>
      </div>
    </div>
	<div id="loginPopup" data-role="popup" data-overlay-theme="a" style="max-width:250px;" class="ui-corner-all greenCall greenbg" data-tolerance="-100,0,0,0">
		<div data-role="header" class="ui-corner-top greenohead" style="padding:1px 0">
			<h1>Warning</h1>
		</div>
		<div data-role="content" class="ui-corner-bottom">
			<p><center>Sorry your handset is not supported for this version.</center></p>
			<div class="greenOnBtn"><a data-role="none" class="dashBtn"  data-rel="back">OK</a></div>

		</div>
	</div>
  </div>
  <div data-role="footer" class="bg-clear footer">
    <!--[if IE]>
	     <div class="footerBack"><h4><a href="http://www.streetpin.com/" data-role="none">View Web Site</a></h4></div>
    <![endif]-->
    <h4><a href="http://www.streetpin.com/" data-role="none">View Web Site</a></h4>
  </div>
</div>
	<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>

