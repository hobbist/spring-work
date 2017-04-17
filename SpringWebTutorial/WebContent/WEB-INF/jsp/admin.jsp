<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/static/offers.css" rel="stylesheet" type="text/css"/>
<title>Insert title here</title>
</head>
<body>
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

 
</body>
</html>