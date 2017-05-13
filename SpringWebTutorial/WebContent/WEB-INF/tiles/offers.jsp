<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <sql:query var="rs" dataSource="jdbc/spring">
select id, name, text from offers
</sql:query> --%>
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
				    <td>${row.user.name}</td>
				    <td>${row.user.email}</td>
				    <td>${row.text}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>