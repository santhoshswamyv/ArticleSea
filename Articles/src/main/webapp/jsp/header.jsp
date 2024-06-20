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
</head>
<body>
	<nav>
		<s:if test="%{#session.userEmail == null}">
			<div class="logo">Write Wave</div>
		</s:if>
		<s:else>
			<div class="logo"><a href="home" style="text-decoration: none; color:white;">Write Wave</a></div>
		</s:else>
		<div>
			<ul>
				<s:if test="%{#session.userEmail != null}">
					<li><a href="home">Home</a></li>
					<li><a href="dopostarticle">Post Article</a></li>
					<li><a href="myposts">My Posts</a></li>
					<li><a href="showprofile">Profile</a></li>
					<li><a href="logout">Logout</a></li>
				</s:if>
				<s:else>
					<li><a href="dologin">Log In</a></li>
					<li><a href="dosignup">Sign Up</a></li>
				</s:else>
			</ul>
		</div>
	</nav>
</body>
</html>