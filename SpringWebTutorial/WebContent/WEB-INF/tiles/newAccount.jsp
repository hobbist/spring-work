<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<td class="label">Name:</td>
				<td><sf:input class="control" path="name" name="name"
						type="text" /><br>
					<div class='error'>
						<sf:errors path="name"></sf:errors>
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
						name="password" type="password" /><br>
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