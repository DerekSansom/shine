<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="apple-touch-icon" href="./images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="72x72" href="./images/streetpin-logo-glow.png">
<link rel="apple-touch-icon" sizes="114x114" href="./images/streetpin-logo-glow.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>StreetPin</title>

<!--JQs-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="http://code.jquery.com/mobile/1.2.0-alpha.1/jquery.mobile-1.2.0-alpha.1.min.js"></script>
<script src="js/jquery.mobile.tabs.js"></script>
<!--<script type="text/javascript" src="js/jquery.cookies.js"></script>-->
<script type="text/javascript">
	var addToHomeConfig = {
		autostart: false,
		touchIcon: true,
		startDelay: 0
	};
</script>

<!--CSS-->
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0-alpha.1/jquery.mobile-1.2.0-alpha.1.min.css" />
<!--<link rel="stylesheet" href="css/jquery.mobile.tabs.css" />
<link rel="stylesheet" href="css/android-theme.css" />-->
<link rel="stylesheet" href="css/android-tabs.min.css" />
<link rel="stylesheet" href="css/custom.css" />

<!--facebook -->
<script src='http://connect.facebook.net/en_US/all.js'></script>
<script type="text/javascript" src="js/fb.js"></script>
<!--Js Custom Lib-->
<script src="js/js-calls.js" type="text/javascript"></script>

<!--Royal Slider-->
<link rel="stylesheet" href="royalslider/royalslider.css" />
<link rel="stylesheet" href="royalslider/royalslider-skins/default/default.css" />
<link rel="stylesheet" href="royalslider/royalslider-preview.css" />
<script src="royalslider/jquery.easing.1.3.min.js"></script>
<script src="royalslider/royal-slider-8.1.min.js"></script>
<script src="js/slider-calls.js" type="text/javascript"></script>

<!--Date Box-->
<script type="text/javascript" src="http://dev.jtsage.com/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.js"></script>
<link type="text/css" href="datebox/jquery.mobile.datebox.css" rel="stylesheet" />

<!--Gmap Scripts-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCvzv5wLrPHEdRMIfbQLc9pIX9zNaaii6Y&sensor=false"></script>
<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script><!--Custom Info Window-->
<script src="js/geo.min.js" type="text/javascript" charset="utf-8"></script>

<script src="js/pageshow.js"></script>

<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<!--Add To Home-->
<link rel="stylesheet" href="css/add2home.css">
<script type="text/javascript" src="js/add2home.min.js" charset="utf-8"></script>



</head>
<body>


<div data-role="page" id="landgrab" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
		<a href="#menu" data-theme="b" data-transition="slide" data-direction="reverse">Cancel</a>
        <h1>Land Grab</h1>
		<a href="#createboard" data-theme="b" data-transition="slide" data-ajax="false">Claim</a>
	</div><!-- /header -->

    <div data-role="content">
    	<div id="map_canvas_land" style="height:500px;">

		</div>
    </div>
</div>


<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36581471-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</body>
</html>
