<%@page session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/tutorial.css" type="text/css" />
      <title>Success - Logged In</title>
  </head>
<body>
<div id="content">
<h2>Welcome - <sec:authentication property="principal.username"/></h2>
<p>
You have been logged out. <a href="${pageContext.request.contextPath}">Start again</a>.
</p>
</div>
</body>
</html>
