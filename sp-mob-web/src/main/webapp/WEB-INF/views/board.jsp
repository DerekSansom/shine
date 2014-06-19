<%@page import="com.shine.boards.NoticeBoard"%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head/head.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom.css" />

</head>
<body>

<div data-role="page" data-ajax="false" id="" class="mapicon ui-page ui-body-c ui-page-active greenCall ${boardCssClass}">


  <div data-role="header" class="greenohead ui-header ui-bar-a">
    <div class="ui-grid-b">
      <div class="ui-block-a"> <a href="${pageContext.request.contextPath}/mw/map#controls" data-ajax="false" class="mapback takeback" data-role="button" data-inline="true" data-transition="slide" data-direction="reverse">&nbsp;</a> </div>
      <div class="ui-block-b">
        <h3 class="head3" id="boardhead">${board.name}</h3>
      </div>
      <div class="ui-block-c">
        <div class="locBtns">
        	<a data-role="button" data-theme="b" data-inline="true" data-transition="slide" href="#popupadd" data-rel="popup" data-position-to="origin" data-overlay-theme="a"><strong>+</strong></a> </div>
      </div>
    </div>
    <!-- /grid-a -->
  </div>
  <div data-role="content">
		<div data-role="popup" id="popupadd" data-theme="a" class="ui-corner-all" data-tolerance="5,50">
                <ul data-role="listview" data-inset="true" data-theme="a" class="loc-list">
                    <li><a href="addpost?id=${board.id}" id="createnewpost" data-transition="slide">Add Post</a></li>
					<li><a href="#profiles" id="popupdetail" data-transition="slide">Update status</a></li>
					<li><a id="showMyLoc" href="#" data-transition="slide">Show my location</a></li>
					<li data-icon="delete"><a href="#" data-role="none" class="redBtn" data-rel="back" >Cancel</a></li>
                </ul>
            </div>
    <div class="myboard">
    	<h5 class="blkright" id="boardscraddr">${board.locationName}</h5>
		  <h3 class="blkright" id="boardscrcat"></h3>
          <h5 class="blkright">creator:</h5>
      <div class="ui-grid-a">
        <div class="ui-block-a" style="width:55%;"> &nbsp;
		  <div class="addthis_toolbox addthis_default_style" style="margin-top:-16px;">
				<a class="addthis_button_preferred_2"><img src="${pageContext.request.contextPath}/static/images/facebook_32.png" alt="facebook" /></a>
                <a class="addthis_button_preferred_1"><img src="${pageContext.request.contextPath}/static/images/twitter_32.png" alt="twitter" /></a>
				<a class="addthis_button_preferred_3"><img src="${pageContext.request.contextPath}/static/images/email_32.png" alt="email" /></a>
				<a class="addthis_button_compact"><img src="${pageContext.request.contextPath}/static/images/1354286846_addthis.png" alt="addthis" /></a>
				<a class="addthis_counter addthis_bubble_style"></a>
				</div>
				<script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
				<script type="text/javascript" src="http://s7.addthis.com/js/300/addthis_widget.js#pubid=ra-50af72d56f668872"></script><!-- /grid-a -->
        </div>
        <div class="ui-block-b" style="width:45%;">
          <div class="greenOnBtn right boardcreator"><a href="${pageContext.request.contextPath}/mw/userprofile?id=1" data-role="none" class="dashBtn" id="boardscrcreator" data-transition="slide">${board.creator}</a></div>
        </div>
      </div>
      <!-- /grid-a -->


      <div class="ui-grid-b postBoard" id="boardposts">
		<c:forEach items="${board.notices}" var="notice" varStatus="rowCounter">    
	        	<c:set var="notice" value="${notice}" scope="request"/>  
	        	<c:set var="position" value="${rowCounter.index}" scope="request"/>  
	  	        <jsp:include page="jspx/post.jsp">
					<jsp:param value="${rowCounter}" name="position"/>	        
		        </jsp:include>
		</c:forEach>
		
	
	
	
      </div>
       <div class="ui-grid-b imageBoard">
		<c:forEach items="${board.ads}" var="ad" varStatus="rowCounter">    
	        	<c:set var="ad" value="${ad}" scope="request"/>  
	        	<c:set var="position" value="${rowCounter.index}" scope="request"/>  
	  	        <jsp:include page="jspx/ad.jsp">
					<jsp:param value="${rowCounter}" name="position"/>	        
		        </jsp:include>
		</c:forEach>
       </div>
      <div class="ui-grid-b postBtns">
        <div class="ui-block-a">
          <div class="greenOnBtn"><a  data-ajax="false" href="boardposts?id=${board.id}" data-role="none" class="dashBtn" style="width:auto;" id="postcount" data-transition="slide">${board.noticeCount} Posts</a></div>
        </div>
        <div class="ui-block-b">
          <div class="greenOnBtn"><a  data-ajax="false" href="boardads?id=${board.id}" data-role="none" class="dashBtn" style="width:auto;" id="advertcount" data-transition="slide">${board.advertCount} Offers</a></div>
        </div>
        <div class="ui-block-c">
          <div class="greenOnBtn"><a  data-ajax="false" href="boardusers?id=${board.id}" data-role="none" class="dashBtn" style="width:auto;" id="usercount" data-transition="slide">${board.userCount} Users</a></div>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>
