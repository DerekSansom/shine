<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.shine.boards.Advert"%>
 
  	    <c:set var="linepos" value="${position % 3}"/>  
 
        <div class="ui-block-${linepos == 0?'a':(linepos == 1?'b':'c')}">
 
          <div class="cutpaper">
            <div class="post-image"><img id="boardadimg${position}" src="${pageContext.request.contextPath}/images/ad/${ad.imageUrl}" alt="image<%=request.getParameter("position")%>" /></div>
            <a class="posty-title" id="bad<%=request.getParameter("position")%>" data-transition="slide">${ad.title}</a>
          </div>

        </div>