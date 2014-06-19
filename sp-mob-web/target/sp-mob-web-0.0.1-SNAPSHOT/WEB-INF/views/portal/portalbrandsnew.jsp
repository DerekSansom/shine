<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shine.boards.Report"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<!--  ALL SP JSP -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/portal_style.css" rel="stylesheet"/>
	<script type='text/javascript'>
		$(document).ready(function() {
			$("#accordion").accordion({ 
				active: 1 
				}
			);
		});
	</script>	

	<!--  THIS SPECIFIC JSP -->
	<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"> </script>
	<script src="${pageContext.request.contextPath}/js/spectrum.js"></script>
	<link href="${pageContext.request.contextPath}/js/spectrum.css" rel="stylesheet"/>
	<title>StreetPin Portal</title>
</head> 
<body>
	<jsp:include page="portalleftsection.jsp"></jsp:include>

	<div id="container-right">

		<div class="sp-page-header">
			<h1>New brand</h1>
		</div>
		
		<div class="sp-page-content">
			<div id="sp-preview-case">
				<div id="sp-preview-screen">
					<div id="sp-preview-board">
						<h2 id="sp-preview-brand-name">Brand Name Example</h2>
						<div id="sp-preview-brand-logo"></div>
						<div id="sp-preview-text-link">
							<span>Link text example</span>
						</div>
						<div id="sp-preview-text-created">Created 13 Apr 14</div>
						<div id="creator_container">
							<div id="creator">streetpin</div>
							<div id="creator_label" class="p_textColour about_text" style="">Creator</div>
						</div>
					</div>
				</div>	
			</div>

			<c:if test="${not empty result.errorMessages}">
				<c:forEach items="${result.errorMessages}" var="errorMessage">
					<p>
						<span style="color:red">${errorMessage}</span>
					</p>
				</c:forEach>
			</c:if>
			<form  	id="newbrand" 
					method="POST" 
					action="${pageContext.request.contextPath}/mw/portal/brands/saveNew"
					enctype="multipart/form-data">
				<table>
					<tr> 
						<td><label for="name">Name</label></td>
						<!--
							<td><input id="name" name="name" value="" class="inputbox required" title="Please supply a name for this brand"></td> 
						 -->
						 <td><input name="name" value="" class="inputbox"></td>
					</tr>
					<tr>
						<td><label for="bgFile">Background image</label></td>
						<td>
							<input name="bgFile" type="file">
						</td>
					</tr>
					<tr>
						<td><label for="logoFile">Logo image</label></td>
						<td>
							<input name="logoFile" type="file">
						</td>
					</tr>
					<tr>
						<td><label for="bgColour">Background colour</label></td>
						<td>
							<input name="bgColour" type='text' class="colorpicker" value="#FF0000" />
						</td>
					</tr>
					<tr>
						<td><label for="linkColour">Address colour</label></td>
						<td>
							<input name="linkColour" type='text' class="colorpicker" value="#00FF00" />
						</td>
					</tr>
					<tr>
						<td><label for="textColour">Text colour</label></td>
						<td>
							<input name="textColour" type='text' class="colorpicker" value="#0000FF" />
						</td>
					</tr>	
					<!--
					<tr>
						<td><label for="url">Link URL</label></td>
						<td><input id="url" name="url" value="" class="inputbox"></td>
					</tr>
					 -->
					<tr>
						<td></td>
						<td><input type="submit" value="Save"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="sp-page-footer">
			<div class="sp-copyright">&copy; Copyright 2014 streetpin</div>
		</div>

	</div>
</body>
<script>
$(document).ready(function() {
	$(".colorpicker").spectrum({
	    //color: this.value,
	    preferredFormat:"hex",
	   	showInput: true,
	    showInitial: true,
	    chooseText: "OK",
	    change: function(color) {
	    	this.setAttribute("value", color.toHexString());
	    }
	});
	$('#newbrand').validate({
		rules: {
			name: {
				required:true,
				rangelength:[6,10]
			}
		},
		messages: {
			name: {
				required:"Please supply a name for this brand",
				rangelength:"Your brand name should be between 6 and 10 characters long"
			}
		}, // end messages
		errorPlacement : function (error, element) {
			// TODO: Move the error message to below
			error.appendTo(element.parent());
		}
	}); // end validate
});
</script>
</html>