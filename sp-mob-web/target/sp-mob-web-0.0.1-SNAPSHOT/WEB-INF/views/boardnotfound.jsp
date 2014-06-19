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

<div data-role="page" id="boardscreen" class="mapicon">
  <!--
Change classname to change category image :-
cat-barclub < cat-books < cat-default < cat-events < cat-outdoors < cat-poi < cat-restaurants < cat-retails < cat-social < cat-sports < cat-stations < cat-tickets
-->
  <div data-role="header" class="greenohead">
    <div class="ui-grid-b">
      <div class="ui-block-a"> <a href="#controls"  class="mapback takeback" data-role="button" data-inline="true" data-transition="slide" data-direction="reverse">&nbsp;</a> </div>
      <div class="ui-block-b">
        <h3 class="head3" id="boardhead">Board Screen</h3>
      </div>
      <div class="ui-block-c">
        <div class="locBtns">
        	<a data-role="button" data-theme="b" data-inline="true" data-transition="slide" href="#popupadd" data-rel="popup" data-position-to="origin" data-overlay-theme="a"><strong>+</strong></a> </div>
      </div>
    </div>
    <!-- /grid-a -->
  </div>
  <div data-role="content">
    <div class="myboard">
    	<h5 class="blkright" id="boardscraddr">Sorry, that board could not be found</h5>
     
       
    </div>
  </div>
</div>

<jsp:include page="head/analytics.html"></jsp:include>


</body>
</body>
</html>
