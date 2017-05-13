<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
