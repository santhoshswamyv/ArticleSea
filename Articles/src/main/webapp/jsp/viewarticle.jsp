<%@page import="com.santhosh.model.Article"%>
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
<html>
<head>
<meta charset="UTF-8">
<title>Article Details</title>
<link
	rel="stylesheet"
	href="styles/styles.css"
>
<style>
body {
	font-family: Poppins, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f5f5f5;
}

.singleContainer {
	max-width: 1000px;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.imageContainer {
	display: flex;
	justify-content: center;
	align-content: center;
}

img {
	max-width: 100%;
	height: auto;
	margin-bottom: 20px;
	max-width: 100%;
}

.singleContainer .title {
	color: #333;
	font-size: 24px;
	margin-bottom: 10px;
}

.singleContainer .author {
	color: #666;
	font-size: 14px;
	margin-bottom: 10px;
}

.singleContainercontent {
	color: #555;
	font-size: 16px;
	line-height: 1.6;
	white-space: pre-wrap;
	overflow-wrap: anywhere;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="singleContainer">
		<h2 class="title">
			<s:property value="article.title" />
		</h2>
		<p class="author">
			By
			<s:property value="article.username" />
		</p>
		<div class="imageContainer">
			<img
				alt="<s:property value='article.title' />"
				src="<s:property value='article.imagePath' />"
			>
		</div>
		<p class="singleContainercontent">
			<s:property value="article.content" />
		</p>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
