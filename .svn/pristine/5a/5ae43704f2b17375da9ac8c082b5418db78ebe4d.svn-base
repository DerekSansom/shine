<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 	    <c:set var="linepos" value="${position % 3}"/>  
 
        <div class="ui-block-${linepos == 0?'a':(linepos == 1?'b':'c')}">
 
          <div class="cutpaper-one">
            <a data-ajax="false" data-transition="slide" id="bpost${linepos}" class="posty-desc ui-link" href="post?id=${notice.id}"><span class="postdata">${notice.title}</span><span class="postauthor">${notice.author}</span></a>
			<c:if test="${notice.repliesCount > 0}">
				<span id="postrep${linepos}" class="postnum">${notice.repliesCount}</span>
			</c:if>
          </div>
  
  </div>
 
