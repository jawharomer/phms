<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-doctor-container">

	Add new Doctor


	<c:url value="/doctors/add" var="url" />
	<sf:form id="add-doctor-form" modelAttribute="doctor"
		onsubmit="addDoctor(event)">

		<table>
			<tbody>

				<tr>
					<td class="text-left">FullName</td>
					<td><sf:input class="form-control" path="fullName" /></td>
					<td><sf:errors path="fullName" /></td>
				</tr>

				<tr>
					<td class="text-left">Phone</td>
					<td><sf:input class="form-control" path="phone" /></td>
					<td><sf:errors path="phone" /></td>
				</tr>

				<tr>
					<td class="text-left">profit</td>
					<td><sf:input class="form-control" path="profit"
							placeholder="0-1" /></td>
					<td><sf:errors path="profit" /></td>
				</tr>

				<tr>
					<td><input class="btn btn-outline-primary" type="submit"
						value="Add"></td>
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
			url : "<c:url value="/doctors/add"/>",
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