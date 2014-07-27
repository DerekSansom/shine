<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

				<table>
					<tr>
						<td>
							<label>Advert name</label>
						</td>
						<td><c:out value="${advert.displayname}"/></td>
					</tr>
					<tr>
						<td><label>Brand</label></td>
						<td>
							<c:choose>
								<c:when test="${empty advert.brand }">
										No brands.
								</c:when>
								<c:otherwise>
										<ul>
											<li><c:out value="${advert.brand.name}"/></li>
											<li><c:out value="${advert.brand.backgroundimg}"/></li>
											<li><c:out value="${advert.brand.logo}"/></li>
											<li><c:out value="${advert.brand.textColour}"/></li>
											<li><c:out value="${advert.brand.bgColour}"/></li>
										</ul>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>				
					<tr>
						<td><label>Category:</label></td>
						<td><c:out value="${advert.category}"/></td>
					</tr>
					<tr>
						<td><label>Title</label></td>
						<td><c:out value="${advert.title}"/></td>
					</tr>
					<tr>
						<td><label>Your text</label></td>
						<td><c:out value="${advert.text}"/></td>
					</tr>

					<c:choose>
						<c:when test="${empty advert.imageUrl }">
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="2"><img src="${pageContext.request.contextPath}/images/ad/{advert.imageUrl}" title="advert image" alt="advert image"/>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					<tr>
						<td><label for="phoneNo">Contact phone</label></td>
						<td><c:out value="${advert.phoneno}"/></td>
					</tr>	
					<tr>
						<td><label for="email">Contact E-mail</label></td>
						<td><c:out value="${advert.email}"/></td>
					</tr>
					<tr>
						<td><label for="url">Offer URL</label></td>
						<td><c:out value="${advert.url}"/></td>
					</tr>
				</table>
