<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="edit-product-container">

	<sf:form id="editProductForm" method="POST" commandName="product"
		onsubmit="modalEditProduct(event)">
		<sf:input path="id" type="hidden" />
		<table>
			<tbody>
				<tr>
					<td class="text-left">Code</td>
					<td><sf:input class="form-control" path="code" /></td>
					<td><sf:errors path="code" /></td>
				</tr>

				<tr>
					<td class="text-left">Name</td>
					<td><sf:input class="form-control" path="name" /></td>
					<td><sf:errors path="name" /></td>
				</tr>

				<tr>
					<td class="text-left">Profit</td>
					<td><sf:input class="form-control" path="profit" /></td>
					<td><sf:errors path="profit" /></td>
				</tr>

				<tr>
					<td class="text-left">unitType</td>
					<td><sf:input class="form-control" path="unitType" /></td>
					<td><sf:errors path="unitType" /></td>
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
	function modalEditProduct(event) {
		console.log("modalEditProduct->fired");
		event.preventDefault();
		var data = JSON.stringify($("#editProductForm").serializeObject());
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/products/update"/>",
			data : data,
			contentType : "application/json",
			success : function(data) {
				console.log("data=", data);
				$("#edit-product-container").html(data);
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>