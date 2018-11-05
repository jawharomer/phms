<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet"></link>


<link href="<c:url value="/resources/css/font-awesome.css" />"
	rel="stylesheet"></link>

<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"></link>

<style type="text/css">
td {
	text-align: left;
}

.center-td {
	text-align: center;
}
</style>

</head>
<body>


	<div id="cus-login-contianer" class="card">
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="POST">
			<table>
				<tr>

					<td>&nbsp;</td>

					<td class="center-td"><i class="fa fa-user cus-user-icon"></i></td>

				</tr>
				<tr>
					<td>User name</td>
					<td><input class="form-control form-control-sm"
						name="username" /></td>
				</tr>

				<tr>
					<td>Password</td>
					<td><input class="form-control form-control-sm"
						name="password" type="password"></td>
				</tr>
				<tr>
					<td>Remember Me:</td>
					<td><input type="checkbox" name="remember-me" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="center-td" id="cus-submit-td"><input id="cus-login-btn"
						class="btn btn-outline-success" type="submit" value="login"></td>
				</tr>



				<c:if test="${param.error!=null}">
					<tr>
						<td>&nbsp;</td>
						<td class="text-danger">username or password is incorrect</td>
					</tr>
				</c:if>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
		</form>

	</div>




</body>
</html>