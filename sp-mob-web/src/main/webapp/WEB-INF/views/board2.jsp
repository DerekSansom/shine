<!DOCTYPE html>
<html>
<head>
<jsp:include page="head/headlite.jsp"></jsp:include>

<body>
<div class="mapicon ui-page ui-body-c ui-page-active greenCall cat-default" id="boardscreen" data-role="page" data-url="boardscreen?id=355" tabindex="0" style="min-height: 257px;">
  <!--
Change classname to change category image :-
cat-barclub < cat-books < cat-default < cat-events < cat-outdoors < cat-poi < cat-restaurants < cat-retails < cat-social < cat-sports < cat-stations < cat-tickets
-->
  <div class="greenohead ui-header ui-bar-a" data-role="header" role="banner">
    <div class="ui-grid-b">
      <div class="ui-block-a"> <a data-direction="reverse" data-transition="slide" data-inline="true" data-role="button" class="mapback takeback ui-btn ui-btn-up-a ui-shadow ui-btn-corner-all ui-btn-inline" href="#controls" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="a"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">&nbsp;</span></span></a> </div>
      <div class="ui-block-b">
        <h3 id="boardhead" class="head3">The Lavender Social Board</h3>
      </div>
      <div class="ui-block-c">
        <div class="locBtns">
        	<a data-overlay-theme="a" data-position-to="origin" data-rel="popup" href="#popupadd" data-transition="slide" data-inline="true" data-theme="b" data-role="button" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" aria-haspopup="true" aria-owns="#popupadd" class="ui-btn ui-btn-up-b ui-shadow ui-btn-corner-all ui-btn-inline"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text"><strong>+</strong></span></span></a> </div>
      </div>
    </div>
    <!-- /grid-a -->
  </div>
  <div data-role="content" class="ui-content" role="main">
		<div style="display: none;"><!-- placeholder for popupadd --></div>
    <div class="myboard">
    	<h5 id="boardscraddr" class="blkright" style="color: rgb(0, 0, 0);">Sw11,<br> London</h5>
		  <h3 id="boardscrcat" class="blkright" style="color: rgb(0, 0, 0);"></h3>
          <h5 class="blkright" style="color: rgb(0, 0, 0);">creator:</h5>
      <div class="ui-grid-a">
        <div style="width:55%;" class="ui-block-a"> &nbsp;
		  <div style="margin-top:-16px;" class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_preferred_2 ui-link addthis_button_facebook at300b" title="Facebook" href="#"><img alt="facebook" src="${pageContext.request.contextPath}/static/images/facebook_32.png"></a>
                <a class="addthis_button_preferred_1 ui-link addthis_button_linkedin at300b" href="http://www.addthis.com/bookmark.php?v=300&amp;winname=addthis&amp;pub=ra-50af72d56f668872&amp;source=tbx-300&amp;lng=en&amp;s=linkedin&amp;url=http%3A%2F%2Fstreetpin.com%2Fmw%2F%23boardscreen%3Fid%3D355&amp;title=StreetPin&amp;ate=AT-ra-50af72d56f668872/-/-/50fc0abfa4ae9d92/2/50a4209719a443c7&amp;frommenu=1&amp;uid=50a4209719a443c7&amp;ct=1&amp;uct=1&amp;tt=0&amp;captcha_provider=nucaptcha" target="_blank" title="LinkedIn"><img alt="twitter" src="${pageContext.request.contextPath}/static/images/twitter_32.png"></a>
				<a class="addthis_button_preferred_3 ui-link addthis_button_twitter at300b" title="Tweet" href="#"><img alt="email" src="${pageContext.request.contextPath}/static/images/email_32.png"></a>
				<a class="addthis_button_compact ui-link at300m" href="#"><img alt="addthis" src="${pageContext.request.contextPath}/static/images/1354286846_addthis.png"></a>
				<a class="addthis_counter addthis_bubble_style ui-link" style="display: block;" href="#" tabindex="1000"><a class="addthis_button_expanded" target="_blank" title="View more services" href="#">0</a><a class="atc_s addthis_button_compact"><span></span></a></a>
				<div class="atclear"></div></div>
				<script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
				<script src="http://s7.addthis.com/js/300/addthis_widget.js#pubid=ra-50af72d56f668872" type="text/javascript"></script><!-- /grid-a -->
        </div>
        <div style="width:45%;" class="ui-block-b">
          <div class="greenOnBtn right boardcreator"><a data-transition="slide" id="boardscrcreator" class="dashBtn" data-role="none" href="#userprofile?uid=55">tombwatts</a></div>
        </div>
      </div>
      <!-- /grid-a -->

      <div id="boardposts" class="ui-grid-b postBoard">
        <div class="ui-block-a">
          <div class="cutpaper-one">
            <a data-transition="slide" id="bpost0" class="posty-desc ui-link" href="#posts?id=355&amp;pid=94"><span class="postdata">What's occuring right here, right now?</span><span class="postauthor">StreetPin</span></a>
			<span style="display:none" id="postrep1" class="postnum"></span>
            <!--<p class="posty-name"></p>-->
          </div>
        </div>
        <div class="ui-block-b">
          <div class="cutpaper-one">
            <a data-transition="slide" id="bpost1" class="posty-desc ui-link" href="#posts?id=355&amp;pid=95"><span class="postdata">Get it off your chest &ndash; what’s frustrated you here</span><span class="postauthor">StreetPin</span></a>
			<span style="display:none" id="postrep2" class="postnum"></span>
            <!--<p class="posty-name"></p>-->
          </div>
        </div>
        <div class="ui-block-c">
          <div class="cutpaper-one">
            <a data-transition="slide" id="bpost2" class="posty-desc ui-link" href="#posts?id=355&amp;pid=96"><span class="postdata">Anyone bored?</span><span class="postauthor">StreetPin</span></a>
			<span style="display:none" id="postrep3" class="postnum"></span>
            <!--<p class="posty-name"></p>-->
          </div>
        </div>
      </div>
      <div class="ui-grid-b imageBoard">
        <div class="ui-block-a">
          <div class="cutpaper">
            <div class="post-image"><img alt="image1" src="http://streetpin.com:8080/shine/images/ad/23-4e1ff6d57f29d7-51502582.jpg" id="boardadimg0"></div>
            <a data-transition="slide" id="bad0" class="posty-title ui-link" onclick="$.mobile.changePage('#advert?bid=355&amp;adid=2')">How to... add an ad</a>
          </div>
        </div>
        <div class="ui-block-b">
          <div class="cutpaper">
            <div class="post-image"><img alt="image2" src="http://streetpin.com:8080/shine/images/ad/23-4e201d19f1cf60-64608513.jpg" id="boardadimg1"></div>
            <a data-transition="slide" id="bad1" class="posty-title ui-link" onclick="$.mobile.changePage('#advert?bid=355&amp;adid=3')">How to... add a board</a>
          </div>
        </div>
        <div class="ui-block-c">
          <div class="cutpaper">
            <div class="post-image"><img alt="image3" src="http://streetpin.com:8080/shine/images/ad/23-4e23ebac36f151-64042879.jpg" id="boardadimg2"></div>
            <a data-transition="slide" id="bad2" class="posty-title ui-link" onclick="$.mobile.changePage('#advert?bid=355&amp;adid=4')">How to... add a tailored board</a>
          </div>
        </div>
      </div>
      <div class="ui-grid-b postBtns">
        <div class="ui-block-a">
          <div class="greenOnBtn"><a data-transition="slide" id="postcount" style="width:auto;" class="dashBtn" data-role="none" href="#posts?id=355">3 Posts</a></div>
        </div>
        <div class="ui-block-b">
          <div class="greenOnBtn"><a data-transition="slide" id="advertcount" style="width:auto;" class="dashBtn" data-role="none" href="#advert?bid=355">3 Offers</a></div>
        </div>
        <div class="ui-block-c">
          <div class="greenOnBtn"><a data-transition="slide" id="usercount" style="width:auto;" class="dashBtn" data-role="none" href="#">0 Users</a></div>
        </div>
      </div>
    </div>
  </div>
<div class="ui-screen-hidden ui-popup-screen fade" id="popupadd-screen"></div><div class="ui-popup-container ui-selectmenu-hidden" id="popupadd-popup"><div data-tolerance="5,50" class="ui-corner-all ui-popup ui-body-a ui-overlay-shadow" data-theme="a" id="popupadd" data-role="popup" aria-disabled="false" data-disabled="false" data-shadow="true" data-corners="true" data-transition="none" data-position-to="origin">
                <ul class="loc-list ui-listview ui-listview-inset ui-corner-all ui-shadow" data-theme="a" data-inset="true" data-role="listview">
                    <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li ui-corner-top"><div class="ui-btn-inner ui-li ui-corner-top"><div class="ui-btn-text"><a data-transition="slide" id="createnewpost" href="#createpost?id=355" class="ui-link-inherit">Add Post</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
					<li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a data-transition="slide" id="popupdetail" href="#profiles" class="ui-link-inherit">Update status</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
					<li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a data-transition="slide" href="#" id="showMyLoc" class="ui-link-inherit">Show my location</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
					<li data-icon="delete" data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-iconpos="right" data-theme="a" class="ui-btn ui-btn-up-a ui-btn-icon-right ui-li-has-arrow ui-li ui-corner-bottom ui-li-last"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a data-rel="back" class="redBtn ui-link-inherit" data-role="none" href="#">Cancel</a></div><span class="ui-icon ui-icon-delete ui-icon-shadow">&nbsp;</span></div></li>
                </ul>
            </div></div>
            </div>
            
</body>
</html>