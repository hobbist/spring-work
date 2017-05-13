<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
    <%@taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(onReady);

function onReady(){
	$("#delete").click(onDeleteClick);
}
function onDeleteClick(event){
	var doDelete=confirm("Are You sure you want to delete this offer?");
	if(!doDelete){
		event.preventDefault();
	}
}
</script>
<sf:form method="post"
	action="${pageContext.request.contextPath}/doCreate"
	commandName="offers">
<sf:input name='id' path='id' type='hidden'/>
	<table class="formtable">
		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea class="control" path="text" name="text"
					rows="10" cols="10" /><br>
			<sf:errors path="text" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Save advert" type="submit" /></td>
		</tr>
		<c:if test="${offers.id!=0}">
		<tr>
			<td class="label"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="delete control" id='delete' name='delete' value="Delete advert" type="submit" /></td>
		</tr>
		</c:if>
	</table>

</sf:form>

