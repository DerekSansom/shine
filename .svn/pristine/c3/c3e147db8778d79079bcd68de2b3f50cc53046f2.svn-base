<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html manifest="cache.appcache">
<head>
	<jsp:include page="head/head.jsp"></jsp:include>
	
</head>
<body>
<div data-role="page" id="controls" class="greenCall">
<!--[if IE]>
    <div data-role="header" data-position="fixed" class="greenohead">
       <div class="headerBack"><h1>Boards</h1></div>
       <h1>Boards</h1>
	   <a href="#" data-rel="back" data-theme="a">back</a>
	</div>
	<p class="para-line support">Internet Explorer does not support features of StreetPin please use another browser.<br/>Recommended browsers include Firefox or Chrome<p>
<![endif]-->

  <div data-role="header" data-position="fixed" class="greenohead ieHide">
    <div class="ui-grid-b greenohead">
      <div class="ui-block-a"><a href="#menu" class="logo" title="Maps Logo" data-transition="slide"><img src="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" alt=" maps logo" /></a></div>
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
	<!--<fieldset class="grey-btns" data-role="controlgroup" data-type="horizontal" >
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
  <div data-role="content" class="ieHide">
    <div id="tab-1">
      <div class="loading-on">
        <h3><small>Acquiring location data</small> <span class="ui-icon-loading"></span></h3>
      </div>
      <div id="map_canvas" class="map_class"></div>
	  <div class="infobox-wrapper">
	</div>
	 <!--popup menu-->
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
	<!--end popup menu-->

      <div class="map-footer ieHide" data-position="fixed" data-role="footer" data-fullscreen="true" style="display:none">
      	<a href="#" data-role="none" class="ui-btn-right" onClick="$('.map-footer').hide();">close</a>
        <div class="map-infos"></div>
      </div>
    </div>
    <div id="tab-2">
      <ul data-role="listview" class="listView" id="listboard"></ul>
    </div>
  </div>
</div>

<div data-role="page" id="landgrab" class="greenCall">
<!--[if IE]>
    <div data-role="header" data-position="fixed" class="greenohead">
       <div class="headerBack"><h1>Create Board</h1></div>
       <h1>Create Board</h1>
	   <a href="#" data-rel="back" data-theme="a">back</a>
	</div>
	<p class="para-line support">Internet Explorer does not support features of StreetPin please use another browser.<br/>Recommended browsers include Firefox or Chrome<p>
<![endif]-->

   <div data-role="header" data-position="fixed" class="greenohead ieHide">
	 <a href="#menu" data-theme="b" data-transition="slide" data-direction="reverse">Cancel</a>
	<!--[if IE]>
	 <div class="headerBack"><h1>Land Grab</h1></div>
	 <![endif]-->
		<h1>Land Grab</h1>
		<a href="${pageContext.request.contextPath}/mw/createboard" data-theme="b" data-transition="slide" data-ajax="false">Claim</a>
	</div><!-- /header -->

    <div data-role="content" class="ieHide">
    	<div id="map_canvas_land" style="height:500px;">

		</div>
    </div>
</div>
	<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>
