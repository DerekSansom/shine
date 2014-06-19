





<!DOCTYPE html>
<html manifest="cache.appcache">
<head>
	
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="apple-touch-icon" href="/shine/static/images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="72x72" href="/shine/static/images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="114x114" href="/shine/static/images/streetpin-logo-glow.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>StreetPin</title>

<!--JQs-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="http://code.jquery.com/mobile/1.2.0-alpha.1/jquery.mobile-1.2.0-alpha.1.min.js"></script>
<script src="/shine/static/js/jquery.mobile.tabs.js"></script>
<script type="text/javascript">
	var addToHomeConfig = {
		autostart: false,
		touchIcon: true,
		startDelay: 0
	};
</script>

<!--CSS-->
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0-alpha.1/jquery.mobile-1.2.0-alpha.1.min.css" />
<link rel="stylesheet" href="/shine/static/css/android-tabs.min.css" />

<!--[if IE]>
<link rel="stylesheet" href="/shine/static/css/customIE.css" />
<![endif]-->


<!--LESS-->
<link rel="stylesheet/less" type="text/css" href="/shine/static/less/custom.less">
<script src="http://cloud.github.com/downloads/cloudhead/less.js/less-1.3.1.min.js" type="text/javascript"></script>

<!--Js Custom Lib-->
<script src="/shine/static/js/js-calls.js" type="text/javascript"></script>

<!--Royal Slider-->
<script src="/shine/static/royalslider/jquery.easing.1.3.min.js"></script>
<script src="/shine/static/royalslider/royal-slider-8.1.min.js"></script>
<script src="/shine/static/js/slider-calls.js" type="text/javascript"></script>
<link rel="stylesheet" href="/shine/static/royalslider/royalslider.min.css" />

<!--Date Box-->
<script type="text/javascript" src="http://dev.jtsage.com/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.js"></script>
<link type="text/css" href="/shine/static/datebox/jquery.mobile.datebox.css" rel="stylesheet" />

<!--Gmap Scripts-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCvzv5wLrPHEdRMIfbQLc9pIX9zNaaii6Y&amp;sensor=false"></script>
<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script><!--Custom Info Window-->
<script src="/shine/static/js/geo.min.js" type="text/javascript" charset="utf-8"></script>

<script src="/shine/static/js/pageshow.js"></script>

<!--Add To Home-->
<link rel="stylesheet" href="/shine/static/css/add2home.css">
<script type="text/javascript" src="/shine/static/js/add2home.min.js" charset="utf-8"></script>
</head>

<body>

<div data-role="page" id="" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
     <!--[if IE]>
     <div class="headerBack"><h1 id="uprofilename">User Profile</h1></div>
     <![endif]-->
		<h1 id="uprofilename">User Profile</h1>
		<a href="#" data-rel="back" data-theme="a">back</a>
	</div><!-- /header -->
 <span></span>

  <div data-role="content">
	<div class="loading-on upload-on" id="loading" style="display:none">
		<h3><small>Uploading Image</small> <span class="ui-icon-loading"></span></h3>
	</div>
  <form id="profileUpdate" name="form" action="/shine/mw/profile/update" method="POST" enctype="multipart/form-data">
	<input type="hidden" id="uemail" name="uemail" value="" />
      <div class="loginStack">
        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:40%;"> <img src="/shine/images/user/7.png" id="userimg" class="uimgprofile" alt="no image" />
            <div class="greenOnBtn">
              <input id="imageUpload" name="imageUpload" type="file" value=""/>
            </div>
          </div>

          <div class="ui-block-b" style="width:60%;">
            <div class="areaField">
			  <!--[if IE]>
			  <label for="txtuserstatus">Update Status</label>
			  <![endif]-->
              <textarea id="txtuserstatus" name="status" placeholder="Update Status">sdfsfsdf</textarea>
			  <span id="statuserr"></span>
            </div>
            <div class="greenOnBtn"><a id="btnstatusupdate" href="javascript:void(0);" data-role="none" class="dashBtn" data-transition="slide">Update Status</a></div>
          </div>
        </div>

        <!-- /grid-a -->
        <h3 class="para-line">Status Updates:</h3>

        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="profile-add-fb"><strong>Add to Facebook</strong></label>
            <select name="profile-add-fb" id="profile-add-fb" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <!--<div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="profile-add-twt"><strong>Add to Twitter</strong></label>
            <select name="profile-add-twt" id="profile-add-twt" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>-->
        <div class="areaField">
		  <!--[if IE]>
		  <label for="ubiog">Tell us about you...</label>
		  <![endif]-->
          <textarea id="ubiog" name="biog" placeholder="Tell us about you...">ertrewtert</textarea>
        </div>
      </div>
      <ul data-role="listview" data-inset="true" class="termInset">
        <li>
          <div data-role="fieldcontain">
            <label for="gender">Gender</label>
            <select id="gender" name="gender">
              <option value="">Choose</option>
              <option value="M">Male</option>
              <option value="F" selected="selected">Female</option>
            </select>
          </div>
        </li>
      </ul>
      <ul data-role="listview" data-inset="true" class="termInset">
        <li>
          <div data-role="fieldcontain" class="datebox-inner">
            <label for="defslide"><strong>Date of Birth</strong></label>
            <input name="defslide" type="date" data-role="datebox" id="defslide" data-options='{"mode": "flipbox", "dateFormat": "dd/mm/YYYY", "useClearButton": true, "collapseButtons": true, "focusMode": true, "beforeToday":true, "centerWindow":true, "disableManualInput":true}' />
          </div>
        </li>
      </ul>
      <h3 class="para-line">I'm interested in seeing offers for:</h3>
      <div class="loginStack">
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="foodrink"><strong>Food & drink</strong></label>
            <select id="foodrink" name="foodrink" data-role="slider">
              <option value="off" selected="selected">OFF</option>
              <option value="on">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="events"><strong>Events</strong></label>
            <select id="events" name="events" data-role="slider">
              <option value="off" selected="selected">OFF</option>
              <option value="on">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="shopping"><strong>Shopping</strong></label>
            <select id="shopping" name="shopping" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="hgarden"><strong>Home & garden</strong></label>
            <select id="hgarden" name="hgarden" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="services"><strong>Services</strong></label>
            <select id="services" name="services" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="leisure"><strong>Leisure</strong></label>
            <select id="leisure" name="leisure" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="jobs"><strong>Jobs</strong></label>
            <select id="jobs" name="jobs" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="travel"><strong>Travel</strong></label>
            <select id="travel" name="travel" data-role="slider">
              <option value="off">OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>
      </div>
	  <h3 class="para-line" id="profileerr"></h3>

      <div class="greenOnBtn">
        <input type="submit" name="saveprofile" id="saveprofile" value="Save Settings" />
      </div>
	  </form>
  </div>
</div>


</body>
</html>

