<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

  	<form:form method="POST" action="${pageContext.request.contextPath}/mw/post/submit" modelAttribute="newPost" id="frmcreatepost" enctype="multipart/form-data" data-ajax="false">
     <form:errors element="span" class="error"/>
     <form:hidden path="bid" id="bid"/>
      <!--<ul data-role="listview" data-inset="true" class="termInset">
                    <li><a href="#previews"><span class="col1">Category</span><span class="col2">Category</span></a></li>
                </ul>-->
      <ul class="termInset ui-listview ui-listview-inset ui-corner-all ui-shadow" data-inset="true" data-role="listview">
        <li class="ui-li ui-li-static ui-btn-up-c ui-corner-top ui-corner-bottom ui-li-last">
         <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
          	<label for="postcat" class="ui-select">Category</label><div class="ui-select">
          	<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-right ui-btn-up-c"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text"><span>Here &amp; now</span></span><span class="ui-icon ui-icon-arrow-d ui-icon-shadow">&nbsp;</span></span><form:select path="catid" id="postcat">
				<form:option selected="selected" value="1">Here &amp; now</form:option>
				<form:option value="2">Announcements</form:option>
				<form:option value="3">Meeting up</form:option>
				<form:option value="4">Interests</form:option>
				<form:option value="5">Wants</form:option>
				<form:option value="6">Rant</form:option>
            </form:select></div></div></div>
        </li>
      </ul>
      <div class="loginStack">
        <div class="areaField">
             <form:input path="title" placeholder="Title" id="txtposttitle" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset"/><form:errors path="title" element="span" />
        </div>
        <div class="areaField">
             <form:textarea path="notice" placeholder="Text"  id="txtpostdesc" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset"/>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br"><label for="post-add-fb" id="post-add-fb-label" class="ui-slider">Add to Facebook</label>
          <form:select data-role="slider" id="post-add-fb" path="addFacebook" class="ui-slider-switch">
              <form:option value="off">OFF</form:option>
              <form:option selected="selected" value="on">ON</form:option>
            </form:select><div role="application" class="ui-slider ui-slider-switch ui-btn-down-c ui-btn-corner-all" style=""><span class="ui-slider-label ui-slider-label-a ui-btn-active ui-btn-corner-all" role="img" style="width: 100%;">ON</span><span class="ui-slider-label ui-slider-label-b ui-btn-down-c ui-btn-corner-all" role="img" style="width: 0%;">OFF</span><div class="ui-slider-inneroffset"><a href="#" class="ui-slider-handle ui-btn ui-btn-up-c ui-shadow ui-btn-corner-all ui-slider-handle-snapping" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="c" role="slider" aria-valuemin="0" aria-valuemax="1" aria-valuenow="on" aria-valuetext="ON" title="ON" aria-labelledby="post-add-fb-label" style="left: 100%;"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text"></span></span></a></div></div></div>
        </div>
        <!--<div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="post-add-twt">Add to Twitter</label>
            <select name="post-add-twt" id="post-add-twt" data-role="slider">
              <option value="off" s>OFF</option>
              <option value="on" selected="selected">ON</option>
            </select>
          </div>
        </div>-->
      </div>
      <!--<ul data-role="listview" data-inset="true" class="takeImage">
                    <li><a href="#">
                    <span class="col1"></span>
                    <span class="col2">Add Image</span></a></li>
                </ul>-->

      <!--<ul data-role="listview" data-inset="true" class="termInset">
                    <li><a href="#"><span class="col1">Expires</span><span class="col2">One Month</span></a></li>
                </ul>-->
      <ul class="termInset ui-listview ui-listview-inset ui-corner-all ui-shadow" data-inset="true" data-role="listview">
        <li class="ui-li ui-li-static ui-btn-up-c ui-corner-top ui-corner-bottom ui-li-last">
         <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br"><label for="expires" class="ui-select">Expires</label><div class="ui-select"><div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-up-c ui-shadow ui-btn-corner-all ui-btn-icon-right"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text"><span>Tonight</span></span><span class="ui-icon ui-icon-arrow-d ui-icon-shadow">&nbsp;</span></span>
 			<form:select path="expires" id="expires">
              <form:option selected="selected" value="Tonight">Tonight</form:option>
              <form:option value="24 hours">24 hours</form:option>
              <form:option value="1 week">1 week</form:option>
              <form:option value="1 month">1 month</form:option>
			  <form:option value="3 months">3 months</form:option>
           </form:select></div></div></div>
        </li>
      </ul>
	  <ul class="inputFile ui-listview ui-listview-inset ui-corner-all ui-shadow" data-inset="true" data-role="listview">
        <li class="ui-li ui-li-static ui-btn-up-c ui-corner-top ui-corner-bottom ui-li-last">
          <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br"><label for="file"><img height="76px" alt="image1" src="${pageContext.request.contextPath}/static/images/streetpin-logo-notext100.png" id="pimg"></label>
        	  <form:input path="imageUpload" type="file"/>
          </div>
        </li>
      </ul>
      <div class="greenOnBtn">
        <div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="" data-iconpos="" data-theme="c" class="ui-btn ui-btn-up-c ui-shadow ui-btn-corner-all" aria-disabled="false"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">Create my Post</span></span>
        <input type="submit" value="Create my Post" ></div>
      </div>
   	</form:form>