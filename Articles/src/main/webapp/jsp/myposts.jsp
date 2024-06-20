<%@page import="java.util.List"%>
<%@page import="com.santhosh.model.Article"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
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
	<%@ include file="header.jsp"%>
	<div class="articlecontainer">
		<h1>
			<s:property value="#session.userName" />
			's Articles
		</h1>
		<div class="articles">
			<!-- Repeat this block for each article -->
			<%
			List<Article> articles = (List<Article>) request.getAttribute("articles");
			if (articles != null && !articles.isEmpty()) {
			%>
			<s:iterator
				value="articles"
				var="article"
			>
				<a
					href="viewarticle?id=<s:property value='#article.id'/>"
					target="_self"
					class="article-link"
				>
					<div class="article">
						<img
							src="<s:property value="#article.imagePath"/>"
							alt="<s:property value="#article.title" />"
						>
						<div class="article-content">
							<h2 class="title">
								<s:property value="#article.title" />
							</h2>
							<p class="author">
								By
								<s:property value="#article.username" />
							</p>
							<p class="content">
								<s:property value="#article.content" />
							</p>
						</div>
					</div>
				</a>
			</s:iterator>
			<div class="end-of-articles">
				<h2>You've Reached the End...!</h2>
			</div>
			<%
			} else {
			%>
			<div
				class="error-message"
				id="error-message"
			>
				<h2>
					<s:property value="errorMessage" />
				</h2>
			</div>
			<%
			}
			%>
			<!-- End of repeat block -->
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
