<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


    	<form:form method="POST" action="${pageContext.request.contextPath}/mw/createboard" modelAttribute="createboard" id="frmcreateboard">
   			<form:hidden path="boardlat"/>
   			<form:hidden path="boardlng"/>
            <div class="loginStack">
            	<div class="areaField"><input type="text" name="create-name" id="create-name" value="" placeholder="Name"  /></div>
                <div class="areaField"><textarea id="landadrs"></textarea></div>
                <!--<ul data-role="listview" data-inset="true" class="termInset">
                    <li><a href="#previews"><span class="col1">Category</span><span class="col2">Category</span></a></li>
                </ul>
                <ul data-role="listview" data-inset="true" class="termInset">
                    <li><a href="#"><span class="col1">Text</span><span class="col2">Text</span></a></li>
                </ul>-->
            </div>
                <ul data-role="listview" data-inset="true" class="termInset">
                	<li><div data-role="fieldcontain">
                       <label for="category1">Category</label>
                       <form:select path="category" id="category1">
                          <form:option value="ENT" selected="selected">Entertainment</form:option>
                          <form:option value="SPORT">Sport</form:option>
                          <form:option value="FOOD_DRK">Food & Drink</form:option>
                          <form:option value="BARCLUB">Bar, club</form:option>
						  <form:option value="TRANS">Station, stop</form:option>
                          <form:option value="EDU">Education</form:option>
                          <form:option value="EVENT">Event</form:option>
						  <form:option value="POI">Place of Interest</form:option>
                          <form:option value="RETAIL">Retail</form:option>
                          <form:option value="SOCIAL">Social</form:option>
                       </form:select>
                    </div></li>
                </ul>
                <!--<ul data-role="listview" data-inset="true" class="termInset">
                	<li><div data-role="fieldcontain">
                       <label for="boardcolor">Text</label>
                       <select name="boardcolor" id="boardcolor">
                          <option value="Black" selected="selected">Black</option>
                          <option value="White">White</option>
                          <option value="Red">Red</option>
                          <option value="Blue">Blue</option>
						  <option value="Yellow">Yellow</option>
                       </select>
                    </div></li>
                </ul>-->
            <div class="loginStack">
                <div class="areaToggle">
                    <div data-role="fieldcontain">
                        <label for="board-add-fb">Add to Facebook</label>
                        <select name="board-add-fb" id="board-add-fb" data-role="slider">
                            <option value="off" selected="selected">OFF</option>
                            <option value="on">ON</option>
                        </select>
                    </div>
                </div>
                <!--<div class="areaToggle">
                    <div data-role="fieldcontain">
                        <label for="board-add-twt">Add to Twitter</label>
                        <select name="board-add-twt" id="board-add-twt" data-role="slider">
                            <option value="off" selected="selected">OFF</option>
                            <option value="on">ON</option>
                        </select>
                    </div>
                </div>-->

            </div>

            <div class="greenOnBtn"><input type="button" name="preview" id="preview" value="Preview Board" onClick="getPreview();"/></div>
            <p class="note1">Please note that your board will be up for grabs if you don't post on it for 3 months.</p>
        </form:form>

