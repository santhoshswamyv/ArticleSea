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
			<h2>Reset Password</h2>
			<form
				action="resetpassword"
				method="post"
			>
				<label for="email">Email:</label> <input
					type="email"
					name="emailid"
					id="email"
					placeholder="Enter your Email"
					required
				><br> <label for="password">New Password : </label><input
					type="password"
					id="pass"
					name="password"
					placeholder="Enter your New Password"
					required
				> <br> <label for="cnfnewpassword">Confirm Password : </label><input
					type="password"
					id="cnfpass"
					placeholder="Re-enter your New Password"
					required
					oninput="checkPassword()"
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
					id="btn"
					value="Reset"
				>
			</form>
			<p>
				Already have an account ? <a href="dologin">Log in</a>
			</p>
		</div>
	</div>
	<script type="text/javascript">
		function checkPassword() {
			let btn = document.getElementById("btn");
			let errorMessage = document.getElementById("error-message");
			if (document.getElementById("cnfpass").value == document
					.getElementById("pass").value) {
				btn.removeAttribute("disabled");
				errorMessage.textContent = "";
			} else {
				btn.setAttribute("disabled", "disabled");
				errorMessage.textContent = "Confirm Password should be same as Password!";
			}
		}
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>
