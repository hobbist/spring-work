<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Admin functionality</h2>
<table class="gridtable">
		<thead style="border: thick;border-color: black;">
		<tr>
			<th>username</th>
			<th>emailAddress</th>
			<th>Role</th>
			<th>enabled</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${users}">
				<tr style="border-bottom : dashed;">
				 	<td>${row.username}</td>
				    <td>${row.email}</td>
				    <td>${row.authority}</td>
				    <td>${row.enabled}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>