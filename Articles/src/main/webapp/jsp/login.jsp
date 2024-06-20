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
	<div class="main-content">
		<div class="login-form">
			<h2>Login</h2>
			<form
				action="login"
				method="post"
			>
				<label for="emailid">Email ID : </label> <input
					type="email"
					name="emailid"
					placeholder="Enter your Email"
					required
				><br> <label for="password">Password : </label><input
					type="password"
					name="password"
					placeholder="Enter your Password"
					required
				>
				<div
					class="error-message"
					id="error-message"
				>
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<s:property />
						</s:iterator>
					</s:if>
				</div>
				<input
					type="submit"
					value="Login"
				>
			</form>
			<p>
				Forgot Password ? <a href="doresetpassword">Reset Password</a>
			</p>
			<p>
				Don't have an account? <a href="dosignup">Sign up</a>
			</p>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
