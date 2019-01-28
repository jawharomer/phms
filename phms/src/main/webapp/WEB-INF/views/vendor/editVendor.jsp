<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="edit-vendor-container">

	Edit Vendor

	<sf:form id="edit-vendor-form" method="POST" commandName="vendor"
		onsubmit="addVendor(event)">

		<sf:input path="id" type="hidden" />

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
					<td class="text-left">Address</td>
					<td><sf:input class="form-control" path="address" /></td>
					<td><sf:errors path="address" /></td>
				</tr>


				<tr>
					<td class="text-left">Note</td>
					<td><sf:textarea class="form-control" path="note" /></td>
					<td><sf:errors path="note" /></td>
				</tr>


				<tr>
					<td><input class="btn btn-outline-warning" type="submit"
						value="Edit"></td>
				</tr>

			</tbody>

		</table>

	</sf:form>

</div>


<script>
	function addVendor(event) {
		event.preventDefault();
		console.log("editVendor->fired");

		var data = $("#edit-vendor-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/vendors/update"/>",
			headers : {
				'X-CSRF-TOKEN' : csrf
			},
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#edit-vendor-container").html(response);
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>