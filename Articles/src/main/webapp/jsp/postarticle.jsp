<%@page import="com.santhosh.model.Category"%>
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
<title>Upload Article</title>
<link
	rel="stylesheet"
	href="styles/styles.css"
>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main-content">
		<div class="upload-article-container">
			<h2>Upload Article</h2>
			<form
				id="uploadForm"
				action="postarticle"
				method="post"
				enctype="multipart/form-data"
			>
				<label for="title">Title:</label> <input
					name="title"
					type="text"
					placeholder="Enter the Title"
					required
				> <label for="category">Category:</label> <select id="category" name="category" required>
                    <option disabled="disabled" selected="selected" value="">Select a Category</option>
                    <s:iterator value="categories">
                        <option value="<s:property value='id'/>"><s:property value='name' /></option>
                    </s:iterator>
                </select> <label for="image">Image:</label> <input
					name="image"
					id="article"
					type="file"
					accept="image/*"
					required
				>
				<div id="articleContainer">
					<div class="image-container">
						<img
							src=""
							id="articleImage"
						>
					</div>
				</div>
				<label for="content">Content:</label>
				<textarea
					name="content"
					rows="7"
					cols="10"
					placeholder="Enter the Content"
					required
				></textarea>
				<div class="error-message">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<div class="error">
								<s:property />
							</div>
						</s:iterator>
					</s:if>
				</div>
				<input
					type="submit"
					value="Upload"
				>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
    <script>
        let imageTag = document.getElementById("articleImage");
        let imageContainer = document.getElementById("articleContainer");
        let inputTag = document.getElementById("article");
        let uploadForm = document.getElementById("uploadForm");
        let categorySelect = document.getElementById("category");

        imageContainer.style.display = "none";

        const handleImageSelect = (event) => {
            let file = event.target.files[0];
            if (file) {
                let reader = new FileReader();
                reader.onload = function () {
                    imageTag.src = reader.result;
                    imageContainer.style.display = "block";
                };
                reader.readAsDataURL(file);
            }
        }

        inputTag.addEventListener("change", handleImageSelect);

        const validateForm = (event) => {
            if (categorySelect.value === "") {
                event.preventDefault();
                alert("Please select a category.");
            }
        }

        uploadForm.addEventListener("submit", validateForm);
    </script>
</body>
</html>
