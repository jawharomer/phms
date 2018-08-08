<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-vendor-container">

	Adding new Vendor

	<sf:form id="add-vendor-form" method="POST" commandName="vendor"
		onsubmit="addVendor(event)">

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
					<td>address</td>
					<td><sf:input path="address" /></td>
					<td><sf:errors path="address" /></td>
				</tr>


				<tr>
					<td>note</td>
					<td><sf:input path="note" /></td>
					<td><sf:errors path="note" /></td>
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
	function addVendor(event) {
		event.preventDefault();
		console.log("addVendor->fired");

		var data = $("#add-vendor-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/vendors/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#add-vendor-container").html(response);
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>