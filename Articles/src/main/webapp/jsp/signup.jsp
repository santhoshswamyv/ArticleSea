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
		<div class="signup-form">
			<h2>Signup</h2>
			<form
				action="signup"
				method="post"
			>
				<label for="username">Username:</label> <input
					type="text"
					name="username"
					id="username"
					placeholder="Enter your Username"
					oninput="checkUsername()"
					required
				> <label for="email">Email:</label> <input
					type="email"
					name="emailid"
					id="email"
					placeholder="Enter your Email"
					required
				> <label for="contact">Contact:</label> <input
					type="text"
					name="contact"
					id="contact"
					pattern="[0-9]{10}"
					placeholder="Enter your Contact"
					required
				> <label for="password">Password:</label> <input
					type="password"
					id="pass"
					name="password"
					placeholder="Enter your Password"
					required
				> <label for="confirm_password">Confirm Password:</label> <input
					type="password"
					id="cnfpass"
					placeholder="Re-enter your Password"
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
					value="Signup"
				>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function checkUsername(){
			let btn = document.getElementById("btn");
			let errorMessage = document.getElementById("error-message");
			let username = document.getElementById("username").value;
			
			if (/\d/.test(username)) {
				btn.setAttribute("disabled", "disabled");
				errorMessage.textContent = "Username should not contain numbers!";
			} else {
				btn.removeAttribute("disabled");
				errorMessage.textContent = "";
			}
		}
	
	
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
