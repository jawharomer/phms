<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-doctor-container">

	Editing Doctor


	<c:url value="/doctors/update" var="url" />
	<sf:form id="add-doctor-form" modelAttribute="doctor"
		onsubmit="addDoctor(event)">

		<sf:input path="id" type="hidden" />

		<table>
			<tbody>

				<tr>
					<td>fullName</td>
					<td><sf:input path="fullName" /></td>
					<td><sf:errors path="fullName" /></td>
				</tr>

				<tr>
					<td>phone</td>
					<td><sf:input path="phone" /></td>
					<td><sf:errors path="phone" /></td>
				</tr>

				<tr>
					<td>profit</td>
					<td><sf:input path="profit" /></td>
					<td><sf:errors path="profit" /></td>
				</tr>

				<tr>
					<td>Add</td>
					<td><input type="submit" value="Adding"></td>
				</tr>

			</tbody>

		</table>

	</sf:form>

</div>



<script>
	function addDoctor(event) {
		event.preventDefault();
		console.log("addDoctor->fired");
		var data = $("#add-doctor-form").serialize();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/doctors/update"/>",
			data : data,
			success : function(response) {
				$("#add-doctor-container").html(response);
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>