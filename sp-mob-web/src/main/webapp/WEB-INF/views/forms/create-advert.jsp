<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

			<form:form action="create" method="post" modelAttribute="advertForm" enctype="multipart/form-data" >
				<table>
					<tr>
						<td>
							<label for="displayName">Advert name</label>
						</td>
						<td><form:input path="displayName" class="inputbox"/></td>
					</tr>
					<tr>
						<td><label for="brand">Brand</label></td>
						<td>
							<c:choose>
								<c:when test="${empty advertForm.userBrands }">
										No brands.
								</c:when>
								<c:otherwise>
									<form:select path="brand">
										<c:forEach items="${advertForm.userBrands}" var="brand">
											<option
												data-bgImage="${brand.backgroundimg}"
												data-logo="${brand.logo}"
												data-textCol="${brand.textColour}"
												data-bgCol="${brand.bgColour}"
												value="${brand.id}">
											  	${brand.name}</option>
										</c:forEach>
									</form:select>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>				
					<tr>
						<td><label for="catid">Category name</label></td>
						<td>
							<form:select path="category">
								<option value=""></option>
								<c:forEach items="${advertForm.adCategories}" var="adCategory">
									<option value="${adCategory}">
															 ${adCategory.category}
									</option>
								</c:forEach>
							</form:select>
						</td>					
					</tr>
					<tr>
						<td><label for="title">Title</label></td>
						<td><form:input path="title" class="inputbox"/></td>
					</tr>
					<tr>
						<td><label for="text">Your text</label></td>
						<td><form:textarea path="text" class="inputbox"></form:textarea></td>
					</tr>
					<tr>
						<td><label>Add an image</label></td>
						<td>
							<form:input path="imageUpload" type="file"/>
						</td>
					</tr>
					<tr>
						<td><label for="phoneNo">Contact phone</label></td>
						<td><form:input path="phoneNo" class="inputbox"/></td>
					</tr>	
					<tr>
						<td><label for="email">Contact E-mail</label></td>
						<td><input id="email" name="email" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label for="url">Offer URL</label></td>
						<td><form:input path="url" class="inputbox"/></td>
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td>
							<span id="location_status">Location not selected</span>
							<a href="#" class="location_select">
								<img src="${pageContext.request.contextPath}/img/location_icon.png" alt="Select Location" title="Select Location" style="vertical-align:middle">
							</a>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Save" class="btn">
						</td>
					</tr>
				</table>
			</form:form>