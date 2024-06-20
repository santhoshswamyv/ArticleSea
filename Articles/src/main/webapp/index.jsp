<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib
	uri="/struts-tags"
	prefix="s"
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0"
>
<title>Article Gallery</title>
<link
	rel="stylesheet"
	href="styles/styles.css"
>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<div class="main-content">
		<%-- <s:if test="#session.user == null">
			<jsp:include page="jsp/login.jsp" />
		</s:if>
		<s:else>
			<jsp:include page="jsp/home.jsp" />
		</s:else> --%>
	</div>
	<%@ include file="jsp/footer.jsp"%>
</body>
</html>
