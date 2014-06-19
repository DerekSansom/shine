<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
 
       <div class="loginStack">
        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:40%;"> <img src="${pageContext.request.contextPath}/${profileUpdate.img}" id="userimg" class="uimgprofile" alt="no image" />
            <div class="greenOnBtn">
              <form:input path="file" type="file"/>
            </div>
          </div>

          <div class="ui-block-b" style="width:60%;">
            <div class="areaField">
			  <!--[if IE]>
			  <label for="txtuserstatus">Update Status</label>
			  <![endif]-->
              <form:textarea path="status" placeholder="Update Status" id="txtuserstatus"/>
			  <span id="statuserr"></span>
            </div>
            <div class="greenOnBtn">
            
            <!-- this should be hidden and use javascript to unhide and submit just update status where appropriate. 
            <a id="btnstatusupdate" href="javascript:void(0);" data-role="none" class="dashBtn" data-transition="slide">Update Status</a>
             -->
            
            </div>
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
          <form:textarea path="biog" placeholder="Tell us about you..." id="ubiog"/>
        </div>
      </div>
      <ul data-role="listview" data-inset="true" class="termInset">
        <li>
          <div data-role="fieldcontain">
            <label for="gender">Gender</label>
            <form:select path="gender" id="gender">
              <form:option value="">Choose</form:option>
              <form:option value="M">Male</form:option>
              <form:option value="F">Female</form:option>
            </form:select>
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
            <form:select path="foodrink" id="foodrink" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="events"><strong>Events</strong></label>
            <form:select path="events" id="events" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="shopping"><strong>Shopping</strong></label>
            <form:select path="shopping" id="shopping" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="hgarden"><strong>Home & garden</strong></label>
            <form:select path="hgarden" id="hgarden" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="services"><strong>Services</strong></label>
            <form:select path="services" id="services" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="leisure"><strong>Leisure</strong></label>
            <form:select path="leisure" id="leisure" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="jobs"><strong>Jobs</strong></label>
            <form:select path="jobs" id="jobs" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
        <div class="areaToggle">
          <div data-role="fieldcontain">
            <label for="travel"><strong>Travel</strong></label>
            <form:select path="travel" id="travel" data-role="slider">
              <form:option value="off">OFF</form:option>
              <form:option value="on">ON</form:option>
            </form:select>
          </div>
        </div>
      </div>
	  <h3 class="para-line" id="profileerr"></h3>

      <div class="greenOnBtn">
        <input type="submit" name="saveprofile" id="saveprofile" value="Save Settings" />
      </div>
