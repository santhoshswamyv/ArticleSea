<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile Update</title>
<link rel="stylesheet" href="styles/styles.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="main-content">
        <div class="profile-container">
            <h2>Profile</h2>
            <div id="profile-details" class="profile-view">
                <p><strong>Name:</strong> <span id="username-display"><s:property value="user.username" /></span></p>
                <p><strong>Email:</strong> <span id="email-display"><s:property value="user.email" /></span></p>
                <p><strong>Contact:</strong> <span id="contact-display"><s:property value="user.contact" /></span></p>
                <p><strong>Created At:</strong> <span id="date-display"><s:property value="user.createdAt" /></span></p>
                <button id="edit-button" class="btn">Edit</button>
            </div>
            <div id="profile-form-container" class="profile-edit" style="display: none;">
                <form id="profile-form" action="updateprofile" method="post">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" value="<s:property value='user.username'/>" required>
                    </div>
                    <div class="form-group">
                        <label for="emailid">Email:</label>
                        <input type="email" name="emailid" id="emailid" value="<s:property value='user.email'/>" required>
                    </div>
                    <div class="form-group">
                        <label for="contact">Contact:</label>
                        <input type="text" name="contact" id="contact" pattern="[0-9]{10}" value="<s:property value='user.contact'/>" required>
                    </div>
                    <div class="error-message">
                        <s:if test="hasActionErrors()">
                            <s:iterator value="actionErrors">
                                <div><s:property /></div>
                            </s:iterator>
                        </s:if>
                    </div>
                    <button type="submit" class="btn">Update Profile</button>
                    <button type="button" id="cancel-button" class="btn btn-secondary">Cancel</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const editButton = document.getElementById('edit-button');
            const cancelButton = document.getElementById('cancel-button');
            const profileDetails = document.getElementById('profile-details');
            const profileFormContainer = document.getElementById('profile-form-container');

            editButton.addEventListener('click', function() {
                profileDetails.style.display = 'none';
                profileFormContainer.style.display = 'block';
            });

            cancelButton.addEventListener('click', function() {
                profileDetails.style.display = 'block';
                profileFormContainer.style.display = 'none';
            });
        });
    </script>
    <%@ include file="footer.jsp"%>
</body>
</html>
