<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head/head.jsp"></jsp:include>
</head>
<body>
<div data-role="page" id="create" class="greenCall">
    <div data-role="header" data-position="fixed" class="greenohead">
		<h1>Create Your Account</h1>
		<a href="#" data-rel="back" data-theme="a">back</a>
	</div><!-- /header -->

	<div data-role="content">
	<span id="reg_msg"></span><!-- name="reg_msg"-->

	<form:form method="POST" action="register" modelAttribute="playerRegistration" data-ajax="false">
		<form:errors path="global" element="span" class="error"/>
    	<div class="loginStack">
			<div class="areaField"><form:input path="fullname" placeholder="Real name (not displayed)"/><strong>*</strong><form:errors path="fullname" class="error"/></div>
            <div class="areaField"><form:input path="username" placeholder="Username"/><strong>*</strong><form:errors path="username" class="error"/></div>
            <div class="areaField"><form:input path="email" placeholder="Email"/><strong>*</strong><form:errors path="email" class="error"/></div>
			<div class="areaField"><form:input path="phone" placeholder="Phone"/></div>
            <div class="areaField"><form:password path="password" placeholder="Password"/><strong>*</strong><form:errors path="password" class="error"/></div>
            <div class="areaField"><form:password path="passwordconf" placeholder="Repeat password"/><strong>*</strong><form:errors path="passwordconf" class="error"/></div>
        </div>

        <p>*NB assuming the identity of celebrities or businesses may result in your account being renamed</p>
        <div class="greenOnBtn">
            <button type="submit" name="create-acc" id="create-acc" value="create-account">Create Account</button>
        </div>
        <p class="para-line"> <label for="shortcut">Add shortcut to phone desktop</label> <input type="checkbox" name="shortcut" id="shortcut" checked="checked" data-role="none" /></p>
   </form:form>
	</div>
</div>

<jsp:include page="head/analytics.html"></jsp:include>


</body>
</html>