<%@ include file="/WEB-INF/includes/header.html"%>
<h1>Join our Email List</h1>
<p>To join our email list, enter your name and email address below.</p>
<p>
	<i>${message}</i>
</p>
<form action="email-list" method="post">
	<input type="hidden" name="action" value="add"> <label>Email:</label>
	<input type="email" name="email" value="${user.email}"><br>

	<label>First Name:</label> <input type="text" name="firstName"
		value="${user.firstName}"><br> <label>Last Name:</label>
	<input type="text" name="lastName" value="${user.lastName}"><br>

	<label>&nbsp;</label> <input type="submit" value="Join Now" id="submit">
</form>
<%@include file="/WEB-INF/includes/footer.jsp"%>