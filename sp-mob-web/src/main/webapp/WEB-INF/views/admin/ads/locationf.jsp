<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/${location.type}/${location.id}"><c:out value="${location.name}"/></a>
						<ul>
							<c:choose>
								<c:when test="${empty location.defaultAdParams }"><li>no adverts set as default</li></c:when>
								<c:otherwise>
									<c:forEach items="${location.defaultAdParams}" var="defaultAdParams">
										<li>
											<c:choose>
												<c:when test="${defaultAdParams.advert.id == advert.id}">
												<c:set var="current">true</c:set>
													<strong>
														*<c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
													</strong>
												</c:when>
												<c:otherwise>
													<c:out value="${defaultAdParams.advert.id}"/>:<c:out value="${defaultAdParams.advert.title}"/>
												</c:otherwise>
											</c:choose>
										</li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<li>
								<c:choose>
									<c:when test="${current}">
										<c:set var="current">false</c:set>
										<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/${location.type}/${location.id}/remove">
											<img src="${pageContext.request.contextPath}/img/fugue/icons/cross.png" alt="Target">Remove as default
										</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/mw/admin/adverts/${advert.id}/${location.type}/${location.id}/set">
											<img src="${pageContext.request.contextPath}/img/fugue/icons/target.png" alt="Target">Set as default
										</a>
									</c:otherwise>
								</c:choose>
							</li>
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
