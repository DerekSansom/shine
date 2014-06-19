<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>StreetPin</title>
	<script type="text/javascript">
		var defaultLat = 51.530867;
		var defaultLng = -0.122309;
		
		var lat;
		var lng;

	</script>


<!--JQs-->
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.mobile-1.2.0-alpha.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.mobile.tabs.js"></script>
<script type="text/javascript">
	var addToHomeConfig = {
		autostart: false,
		touchIcon: true,
		startDelay: 0
	};
</script>


<!--CSS-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.mobile-1.2.0-alpha.1.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/android-tabs.min.css" />

<!--[if IE]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/customIE.css" />
<![endif]-->

<!--LESS-->
<link rel="stylesheet/less" type="text/css" href="${pageContext.request.contextPath}/static/less/custom.less">
<script src="${pageContext.request.contextPath}/static/less/less-1.3.1.min.js" type="text/javascript"></script>

<!--Js Custom Lib-->
<script src="${pageContext.request.contextPath}/static/js/map-calls.js" type="text/javascript"></script>

<!--Royal Slider-->
<script src="${pageContext.request.contextPath}/static/royalslider/jquery.easing.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/royalslider/royal-slider-8.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/slider-calls.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/royalslider/royalslider.min.css" />

<!--Date Box-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.mobile.datebox.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/static/datebox/jquery.mobile.datebox.css" rel="stylesheet" />

<!--Gmap Scripts-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCvzv5wLrPHEdRMIfbQLc9pIX9zNaaii6Y&amp;sensor=false"></script>
<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script><!--Custom Info Window-->
<script src="${pageContext.request.contextPath}/static/js/geo.min.js" type="text/javascript" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/static/js/mapshow.js" type="text/javascript"></script>

<!--Add To Home-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/add2home.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/add2home.min.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom.css">

<style>
<!--
.msg{
color:black;
}

.ui-page{
display: block;
}
-->
</style>

</head>
<body>

<div data-role="page" id="controls" class="greenCall">
  <div data-role="header">
    <div class="ui-grid-b greenohead">
      <div class="ui-block-a"><a href="${pageContext.request.contextPath}/mw/menu" class="logo" title="Logo" data-transition="slide"><img src="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" alt="logo" /></a></div>
      <!--images/streetpin-logo-noglow.png-->
      <div class="ui-block-b">
        <div data-role="tabs" class="tabBtns">
        	<a href="#tab-1" data-role="button" data-icon="map" data-iconpos="notext" data-inline="true" data-theme="b"></a>
        	<a href="#tab-2" data-role="button" data-icon="list" data-iconpos="notext" data-inline="true" data-theme="b"></a> </div>
      </div>
      <div class="ui-block-c">
        <div class="locBtns"> <a id="myLoc" data-role="button" data-theme="b" data-inline="true" data-icon="dicon" data-iconpos="top" data-rel="popup" data-position-to="window" data-transition="pop"></a>
          <!--<a data-role="button" data-theme="b" data-inline="true" data-icon="pluss" data-iconpos="top" href="#popupLogin" data-rel="popup" data-position-to="window" data-transition="pop"></a>-->
        </div>
      </div>
    </div>
    <!-- /grid-a -->
    <div class="searchArea">
      <div class="ui-grid-a">
          <div class="ui-block-a" id="divsearch">
		  	<input type="search" placeholder="Find a board" id="txtsearch" />
			<div data-role="popup" id="searchPopup" data-theme="a" data-tolerance="5,90" class="ui-corner-all">
				<ul id="searchpoplist" data-role="listview" data-inset="true" style="max-width:310px;" data-theme="a" class="loc-list" ></ul>
			</div>
		  </div>
          <div class="ui-block-b">
            <div class="greenOnBtn"> <input type="button" id="boardsearch" name="boardsearch" value="Search" data-mini="true" data-theme="c" /> </div>
          </div>
        </div>
    </div>
<!--    <fieldset class="grey-btns" data-role="controlgroup" data-type="horizontal" >
      <input type="radio" data-theme="a"  name="radio-choice" id="optboard" value="Board"  />
      <label for="optboard">Board</label>
      <input type="radio" data-theme="a" name="radio-choice" id="optuser" value="User"  />
      <label for="optuser">User</label>
      <input type="radio" data-theme="a" name="radio-choice" id="optbrand" value="Brand"  />
      <label for="optbrand">Brand</label>
      <input type="radio" data-theme="a" name="radio-choice" id="optloc" value="Location"  />
      <label for="optloc">Location</label>
      <input type="radio" data-theme="a" name="radio-choice" id="optall" value="All" checked="checked"  />
      <label for="optall">All</label>
    </fieldset>-->

  </div>
  <div data-role="content">
    <div id="tab-1">
      <div class="loading-on">
        <h3><small>Acquiring location data</small> <span class="ui-icon-loading"></span></h3>
      </div>
      <div id="map_canvas" class="map_class"></div>
	  <div class="infobox-wrapper">
</div>
      <div data-role="popup" id="popupLogin" data-theme="a" class="ui-corner-all">
        <div class="popup-inner">
          <h3>What's next?</h3>
          <div class="greenOnBtn"> <a href="#" data-role="none" class="dashBtn" data-rel="back">Find a board</a></div><!-- onClick="closePopup();"-->
          <div class="greenOnBtn"><a href="#landgrab" data-role="none" class="dashBtn" data-transition="slide">Create & claim your board</a></div>
          <div class="greenOnBtn"><a href="#howto" data-role="none" class="dashBtn" data-transition="slide">How to...</a></div>
          <div class="greenOnBtn"><a href="#" data-role="none" class="redBtn" data-rel="back">Close menu</a></div><!-- onClick="closePopup();"-->
          <p>
            <input type="checkbox" name="popupshow" id="popupshow" data-role="none" onClick="setPopup();"/>
            <label for="popupshow">Don't show this box again?</label>
          </p>
        </div>
      </div>

      <div class="map-footer" data-position="fixed" data-role="footer" data-fullscreen="true" style="display:none">
      	<a href="#" data-role="none" class="ui-btn-right" onClick="$('.map-footer').hide();">close</a>
        <div class="map-infos"></div>
      </div>
    </div>
    <div id="tab-2">
      <ul data-role="listview" class="listView" id="listboard">

      </ul>
    </div>
  </div>
</div>





<div data-role="page" id="landgrab" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
		<a href="#controls" data-theme="b" data-transition="slide" data-direction="reverse">Cancel</a>
        <h1>Land Grab</h1>
		<a href="#createboard" data-theme="b" data-transition="slide" data-ajax="false">Claim</a>
	</div><!-- /header -->

    <div data-role="content">
    	<div id="map_canvas_land" style="height:500px;">

		</div>
    </div>
</div>



<div data-role="page" id="createboard" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
		<a href="#landgrab"  class="takeback" data-theme="a">Back</a>
        <h1>Create Board</h1>
		<a href="${pageContext.request.contextPath}/mw/map#controls" data-theme="b" data-transition="slide">Cancel</a>
	</div><!-- /header -->

    <div data-role="content">
	<span id="createboarderr"></span>
		<jsp:include page="forms/create-board-embed.jsp"></jsp:include>
	</div>
</div>
<jsp:include page="head/analytics.html"></jsp:include>

</body>
</html>