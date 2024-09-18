<%@ include file="/includes/header.html"%>

<h1>Join our email list</h1>
<p>To join our email list, enter your name and email address below.</p>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="email-list" method="post">
	<input type="hidden" name="action" value="add"> <label>Email:</label>
	<input type="email" name="email" value="${user.email}"><br>
	<label>First Name:</label> <input type="text" name="firstName"
		value="${user.firstName}"><br> <label>Last Name:</label>
	<input type="text" name="lastName" value="${user.lastName}"><br>

	<label>&nbsp;</label> <input type="submit" value="Join Now" id="submit">

	<c:if test="${msg != null}">
		<p style="color: red; font-size: 20px;">${msg}</p>
	</c:if>
</form>

<%@ include file="/includes/footer.jsp"%>