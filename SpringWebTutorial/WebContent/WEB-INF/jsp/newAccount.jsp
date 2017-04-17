<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/static/main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(onLoad);
	function onLoad() {
		$("#password").keyup(checkPassMatch);
		$("#confirmpass").keyup(checkPassMatch);
		$("#details").submit(canSubmit);
	}
	function canSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if (password.length > 3 && confirmpass.length > 3) {
			if (password != confirmpass) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	function checkPassMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if (password.length < 3 || confirmpass.length < 3) {
			return;
		}
		if (password == confirmpass) {
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>");
			$("#matchpass").addClass('valid');
			$("#matchpass").removeClass('error');
		} else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
			$("#matchpass").addClass('error');
			$("#matchpass").removeClass('valid');
		}
	}
</script>
<title>Create New Account</title>
</head>
<body>
	<h2>Create New Account</h2>

	<sf:form method="post" id='details'
		action="${pageContext.request.contextPath}/createAccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">UserName:</td>
				<td><sf:input class="control" path="username" name="name"
						type="text" /><br>
					<div class='error'>
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br>
					<div class='error'>
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" id="password" path="password"
						name="password" type="text" /><br>
					<div class='error'>
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Retype-Password:</td>
				<td><input class="control" id="confirmpass" name="confirmpass"
					type="text" />
				<div id='matchpass'></div></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Create account" type="submit" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>