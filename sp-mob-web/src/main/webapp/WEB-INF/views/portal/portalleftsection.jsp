<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="container-left">
	<div id="user-summary">
		<a href="http://87.106.67.218">
			<img src="${pageContext.request.contextPath}/img/streetpin-sign-logo_200x55.png" alt="Streetpin" width="200" height="55">
		</a>
		<p>Logged in as <sec:authentication property="principal.username" /></p>
		<p>XX Noticeboard credits</p>
		<p>XX Advert credits</p>
	</div>
	<div id="accordion" class="navigation">
		<h3>You</h3>
		<div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/mw/portal/profile">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/mw/portal/auth/logout">Log out</a></li>
			</ul>
		</div>
		<h3>Your brands</h3>
		<div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/mw/portal/brands">Existing Brands</a></li>
				<li><a href="${pageContext.request.contextPath}/mw/portal/brands/showNew">Create new brand</a></li>
			</ul>
		</div>
		<h3>Your boards</h3>	
		<div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/mw/portal/boards">Existing boards</a></li>
				<li><a href="${pageContext.request.contextPath}/mw/portal/boards/new">Create new board</a></li>
			</ul>
		</div>
		<h3>Your adverts</h3>
		<div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/mw/portal/adverts">Existing adverts</a></li>
				<li><a href="${pageContext.request.contextPath}/mw/portal/adverts/create">Create new advert</a></li>
			</ul>
		</div>
		<h3>Credits</h3>
		<div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/mw/portal/credits/add">Add credits</a></li>
				<li><a href="${pageContext.request.contextPath}/mw/portal/activity">Activity history</a></li>
			</ul>
		</div>
	</div>
</div> 