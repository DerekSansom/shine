<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>SteetPin</title>
<jsp:include page="head/headlite.jsp"></jsp:include>

</head>
<body>

<div class="greenCall ui-page ui-body-c ui-page-active" id="" data-role="page" style="min-height: 281px;">
  <div data-role="header" class="ui-header ui-bar-a" role="banner">
    <div class="ui-grid-b greenohead">
      <div class="ui-block-a">
      <a class="takeback ui-btn ui-btn-up-a ui-shadow ui-btn-corner-all ui-btn-inline" data-direction="reverse" data-transition="slide" data-inline="true" data-role="button" href="${pageContext.request.contextPath}/mw/board?id=${post.boardId}" id="postback" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="a"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">board</span></span></a> </div>
      <div class="ui-block-b">
        <div class="tabBtns ui-navbar" data-role="tabs" role="navigation">
   <!--      	<a data-theme="b" data-inline="true" data-iconpos="top" data-icon="hscroll" data-role="button" href="${pageContext.request.contextPath}/mw/post?id=1" id="lnkpost1" data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="span" class="ui-btn ui-btn-up-b ui-btn-inline ui-btn-icon-top ui-btn-active"><span class="ui-btn-inner"><span class="ui-btn-text"></span><span class="ui-icon ui-icon-hscroll ui-icon-shadow">&nbsp;</span></span></a>
            <a data-theme="b" data-inline="true" data-iconpos="top" data-icon="list" data-role="button" href="${pageContext.request.contextPath}/mw/post?id=2" id="lnkpost2" data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="span" class="ui-btn ui-btn-up-b ui-btn-inline ui-btn-icon-top"><span class="ui-btn-inner"><span class="ui-btn-text"></span><span class="ui-icon ui-icon-list ui-icon-shadow">&nbsp;</span></span></a>
     -->
         </div>
      </div>
      <div class="ui-block-c">
        <div class="locBtns">
        	<a data-overlay-theme="a" data-position-to="origin" data-rel="popup" href="#popupposts" data-transition="slide" data-inline="true" data-theme="b" data-role="button" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" aria-haspopup="true" aria-owns="#popupposts" class="ui-btn ui-btn-up-b ui-shadow ui-btn-corner-all ui-btn-inline"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text"><strong>+</strong></span></span></a>
        </div>
      </div>
    </div>
    <!-- /grid-a -->
    <div class="posts-cat">
      <div class="ui-grid-a">
        <div id="postcathead" class="ui-block-a left"><strong>Posts:</strong>${post.category}</div>
        <div id="postindex" class="ui-block-b right">${position}/${total}</div>
      </div>
      <!-- /grid-a -->
    </div>
  </div>
  <div data-role="content" class="ui-content" role="main">
<p class="msg">${msg}</p>
    <div id="posts-1" class="ui-tabs-content ui-tabs-content-active">
	 <div style="display: none;"><!-- placeholder for popupposts --></div>
      <div class="royalSlider default" id="posts-slider" style="overflow: visible;">
        <div class="royalWrapper">
        <div class="royalWrapper">
        
        <ul id="viewpostslider" class="royalSlidesContainer grab-cursor" style="left: 0px; width: 1860px;">
        	<li class="royalSlide" style="margin-right: 20px ! important; height: 431px; width: 600px;">
        		<div class="centeredSlide">
        	        <div class="paperlarge">
        	        	<span class="replies">1</span>
        				<div class="innerInfo"><h3>${post.title}</h3>
        					<div class="ui-grid-a contentInfo">
						        <div class="ui-block-a"><img alt="no image"
						        	 onerror="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png"
							         src="${pageContext.request.contextPath}/images/user/${post.creatorId}.png"  
 							        id="sliderpostimg0"></div>
						        <div class=""><!-- ui-block-b -->
						        	<div class="greenOnBtn"><a class="dashBt" data-role="none" href="${pageContext.request.contextPath}/mw/userprofile?id=${post.creatorId}">${post.author}</a></div>
						        	<p>${post.expires}</p>
						        </div>
						    </div>
						    <div class="postimagehh"><p></p></div>
						</div>
						<div class="ui-grid-a btmBtn">
							<div class="ui-block-a"><div class="greenOnBtn"> <a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/report?id=${post.id}">Report</a></div></div>
							<div class="ui-block-b"><div class="greenOnBtn">
         						<a class="dahBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/reply?id=${post.id}">Reply</a></div>
         					</div>
         				</div>
  		        </div>
         
        	 </div>
         
         <!-- Dummy reply 
         <div class="paperlarge">
	         	<div class="innerInfo">
	         		<div class="ui-grid-a contentInfo">
	         			<div class="ui-block-a">
	         				<img onerror="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" alt="no image" src="${pageContext.request.contextPath}/images/user/${reply.creatorId}.png" id="reppostimg2"></div>
	         				<div class="ui-block-b">
	         					<div class="greenOnBtn"><a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/userprofile?id=1">Dum dum</a></div>
	         			</div>
	         		</div><p>A dummy reply</p></div>
	         		<div class="ui-grid-a btmBtn">
	         			<div class="ui-block-a">
	         				<div class="greenOnBtn"><a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/report?id=${post.id}&replyid=${reply.id}">Report</a></div>
	         			</div>
	         		<div class="">
	         			<div class="greenOnBtn"> <a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/reply?id=${post.id}">Reply</a>
	         				
	         			</div>
	         		</div>
	         		</div>
	         </div>
         -->
         
         
         
         <c:forEach items="${post.replies}" var="reply" varStatus="rowCounter">    
         
	         <div class="paperlarge">
	         	<div class="innerInfo">
	         		<div class="ui-grid-a contentInfo">
	         			<div class="ui-block-a">
	         				<img onerror="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" alt="no image" src="${pageContext.request.contextPath}/images/user/${reply.creatorId}.png" id="reppostimg2"></div>
	         				<div class="ui-block-"><!-- ui-block-b -->
	         					<div class="greenOnBtn"><a class="dashBt" data-role="none" href="${pageContext.request.contextPath}/mw/userprofile?id=${reply.creatorId}">${reply.author}</a></div>
	         			</div>
	         		</div><p>${reply.reply}</p></div>
	         		<div class="ui-grid-a btmBtn">
	         			<div class="ui-block-a">
	         				<div class="greenOnBtn"><a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/report?id=${post.id}&replyid=${reply.id}">Report</a></div>
	         			</div>
	         		<div class="">
	         			<div class="greenOnBtn"> <a class="dashBtn" data-role="none" href="${pageContext.request.contextPath}/mw/post/reply?id=${post.id}">Reply</a>
	         				
	         			</div>
	         		</div>
	         		</div>
	         </div>
         </c:forEach>
     <!--    <div class="paperlarge"><div class="innerInfo"><div class="ui-grid-a contentInfo">
         <div class="ui-block-a"><img alt="no image" src="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" id="reppostimg0"></div>
         <div class="ui-block-b"><div class="greenOnBtn"><a class="dashBtn" data-role="none" href="#profiles">dezy</a></div></div></div><p>nothing</p></div><div class="ui-grid-a btmBtn"><div class="ui-block-a"><div class="greenOnBtn"> <a onclick="postReport(90,354);" class="dashBtn" data-role="none" href="#">Report</a></div></div><div class="ui-block-b"><div class="greenOnBtn"> <a onclick="postReply(90,354,'What\'s occuring right here, right now?');" class="dashBtn" data-role="none" href="#">Reply</a></div></div></div></div></li><li class="royalSlide" style="margin-right: 20px ! important; height: 431px; width: 600px;"><div class="centeredSlide"><div class="paperlarge"><div class="innerInfo"><h3>Get it off your chest &ndash; what’s frustrated you here?</h3><div class="ui-grid-a contentInfo"><div class="ui-block-a"><img onerror="defimg('#sliderpostimg1');" alt="no image" src="images/streetpin-logo-glow.png" id="sliderpostimg1"></div><div class="ui-block-b"><div class="greenOnBtn"><a class="dashBtn" data-role="none" href="#userprofile?uid=1">StreetPin</a></div><p>Expiry 31/01/2021</p></div></div><div class="postimagehh"><p></p></div></div><div class="ui-grid-a btmBtn"><div class="ui-block-a"><div class="greenOnBtn"> <a class="dashBtn" onclick="postReport(91,354);" data-role="none" href="#">Report</a></div></div><div class="ui-block-b"><div class="greenOnBtn"> <a class="dashBtn" data-role="none" onclick="postReply(91,354,'Get it off your chest &ndash; what’s frustrated you here?');" href="#">Reply</a></div>
         </div></div></div></div>
         -->
         </li>
       <!--   <li class="royalSlide" style="height: 431px; width: 600px;"><div class="centeredSlide">
         
         <div class="paperlarge"><div class="innerInfo"><h3>Anyone bored?</h3>
         
         <div class="ui-grid-a contentInfo"><div class="ui-block-a">
         <img onerror="defimg('#sliderpostimg2');" alt="no image" src="${pageContext.request.contextPath}/static/images/streetpin-logo-glow.png" id="sliderpostimg2"></div>
        <div class="ui-block-b"><div class="greenOnBtn"><a class="dashBtn" data-role="none" href="reply?id=${post.creatorId}">${post.author}</a></div><p>Expiry ${post.expires}</p></div></div>
        <div class="postimagehh"><p></p></div></div><div class="ui-grid-a btmBtn"><div class="ui-block-a"><div class="greenOnBtn">
         <a class="dashBtn" data-role="none" href="reportpost?id=${post.id}">Report</a></div></div><div class="ui-block-b"><div class="greenOnBtn"> 
        <a class="dashBtn" data-role="none" href="reply?id=${post.id}">Reply</a></div></div></div></div></div></li>
         -->
        
        </ul>
       </div>
       <%if(request.getAttribute("next") != null){ %>
			<a class="arrow right" href="${pageContext.request.contextPath}/mw/post?id=${next}"></a>
		<%}else{ %>
			<a class="arrow right disabled"></a>
		<%} %>    
       <%if(request.getAttribute("previous") != null){ %>
			<a class="arrow left" href="${pageContext.request.contextPath}/mw/post?id=${previous}"></a>
		<%}else{ %>
	      <a class="arrow left disabled"></a>
		<%} %>    
		   </div>
       </div>
    </div>

    <div id="posts-2" class="ui-tabs-content">
      <ul id="postlistview" class="advertList ui-listview" data-role="listview"><li class="ui-li ui-li-static ui-btn-up-c ui-li-has-thumb"><p class="ui-li-aside ui-li-desc">Valid until 31/01/2021</p><a data-role="none" href="#userprofile?uid=1"><img onerror="defimg('#lstpostimg0');" class="ui-li-thumb" alt="no image" src="images/streetpin-logo-glow.png" id="lstpostimg0"> <span class="img-overlay"></span></a><h3 class="ui-li-heading"><a onclick="showPost(0)" data-role="none" href="#">StreetPin</a></h3><p class="ui-li-desc">What's occuring right here, right now?</p><span class="replies">1</span></li><li class="ui-li ui-li-static ui-btn-up-c ui-li-has-thumb"><p class="ui-li-aside ui-li-desc">Valid until 31/01/2021</p><a data-role="none" href="#userprofile?uid=1"><img onerror="defimg('#lstpostimg1');" class="ui-li-thumb" alt="no image" src="images/streetpin-logo-glow.png" id="lstpostimg1"> <span class="img-overlay"></span></a><h3 class="ui-li-heading"><a onclick="showPost(1)" data-role="none" href="#">StreetPin</a></h3><p class="ui-li-desc">Get it off your chest &ndash; what’s frustrated you here?</p></li><li class="ui-li ui-li-static ui-btn-up-c ui-li-has-thumb ui-li-last"><p class="ui-li-aside ui-li-desc">Valid until 31/01/2021</p><a data-role="none" href="#userprofile?uid=1"><img onerror="defimg('#lstpostimg2');" class="ui-li-thumb" alt="no image" src="images/streetpin-logo-glow.png" id="lstpostimg2"> <span class="img-overlay"></span></a><h3 class="ui-li-heading"><a onclick="showPost(2)" data-role="none" href="#">StreetPin</a></h3><p class="ui-li-desc">Anyone bored?</p></li></ul>
    </div>
  </div>
<div class="ui-screen-hidden ui-popup-screen fade" id="popupposts-screen"></div>
<div class="ui-popup-container ui-selectmenu-hidden" id="popupposts-popup">
	<div data-tolerance="5,50" class="ui-corner-all ui-popup ui-body-a ui-overlay-shadow" data-theme="a" id="popupposts" data-role="popup" aria-disabled="false" data-disabled="false" data-shadow="true" data-corners="true" data-transition="none" data-position-to="origin">
      <ul class="loc-list ui-listview ui-listview-inset ui-corner-all ui-shadow" data-theme="a" data-inset="true" data-role="listview">
        <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li ui-corner-top"><div class="ui-btn-inner ui-li ui-corner-top"><div class="ui-btn-text"><a data-transition="slide" id="btncreatepost" href="#createpost?id=354" class="ui-link-inherit">New Post</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
	 	<li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li ui-corner-bottom ui-li-last"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a data-transition="slide" id="btnupdatestatus" href="#profiles" class="ui-link-inherit">Update Status</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
      </ul>
</div>
</div>
</div>


<jsp:include page="head/analytics.html"></jsp:include>
</body>
</html>