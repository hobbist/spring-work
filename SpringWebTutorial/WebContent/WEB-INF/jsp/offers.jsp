<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sql:query var="rs" dataSource="jdbc/spring">
select id, name, text from offers
</sql:query>
<html>
<head>
<link href="${pageContext.request.contextPath}/static/offers.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="gridtable">
		<thead style="border: thick;border-color: black;">
		<tr>
			<th>ID</th>
			<th>name</th>
			<th>email</th>
			<th>Quote</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${offers}">
				<tr style="border-bottom : dashed;">
				 	<td>${row.id}</td>
				    <td>${row.name}</td>
				    <td>${row.email}</td>
				    <td>${row.text}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>
</body>
</html>