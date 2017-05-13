<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<table class="gridtable">
	<thead style="border: thick; border-color: black;">
		<tr>
			<th>ID</th>
			<th>name</th>
			<th>email</th>
			<th>Quote</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${offers}">
			<tr style="border-bottom: dashed;">
				<td>${row.id}</td>
				<td>${row.user.name}</td>
				<td><a href="<c:url value="/message?uid=${row.username}"/>">contact</a></td>
				<td>${row.text}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<p/>
<c:choose>
	<c:when test="${hasOffer}">
		<p>
			<a href="${pageContext.request.contextPath}/createOffers">Edit or
				delete Current Offer</a>
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createOffers">Create
				an New Offer</a>
		</p>
	</c:otherwise>
</c:choose>
<sec:authorize access="isAuthenticated()&&hasAuthority('ROLE_ADMIN')">
	<p>
		<a href="<c:url value='/admin'/>">Admin</a>
	</p>
</sec:authorize>
