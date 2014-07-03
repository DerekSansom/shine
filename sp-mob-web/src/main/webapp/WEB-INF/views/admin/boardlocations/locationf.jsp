<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


				<tr>
					<td>
						<c:out value="${location.name}"/>
						<ul>
							<c:choose>
								<c:when test="${empty location.defaultAdParams }"><li>no adverts set as default</li></c:when>
								<c:otherwise>
									<c:forEach items="${location.defaultAdParams}" var="defaultAdParams">
										<li><c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
											<a class="action_btn" href="${pageContext.request.contextPath}/mw/admin/locations/${location.type}/${location.id}/defaultad/${defaultAdParams.adId}/remove">
											<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="Remove">
												Remove
											</a>
										</li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</ul>
					</td>
					<td>
						<c:if test="${not empty location.children }">
							<table>
								<c:forEach items="${location.children}" var="child">
									<c:set var="location" scope="request" value="${child}"/>
									<jsp:include page="locationf.jsp"/>
								</c:forEach>
							</table>
						</c:if>
					
					</td>
				</tr>
