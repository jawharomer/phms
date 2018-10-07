<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-light bg-light">


	<a class="navbar-brand" href="<c:url value="/adminRoot" />"> <img
		src="<c:url value="/resources/img/logo.png" />" height="50" alt="">
		<b style="color: #F1008C">BHMS</b>

	</a>

	<form action="<c:url value="/logout" />" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<div>
			<span><sec:authentication property="principal.username" /></span>
			<button class="btn btn-outline-primary">logout</button>
		</div>
	</form>
</nav>