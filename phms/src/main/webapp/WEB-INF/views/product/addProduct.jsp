<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-product-container" style="padding: 5px; overflow: auto">

	<h4>Add new Product</h4>

	<sf:form id="add-product-form" method="POST" commandName="product"
		onsubmit="addProduct(event)">
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
					<td><sf:errors class="text-wrap" path="name" /></td>
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
					<td><input class="btn btn-outline-primary" type="submit" value="Adding"></td>
				</tr>

			</tbody>

		</table>

	</sf:form>

</div>


<script>
	function addProduct(event) {
		event.preventDefault();
		console.log("addProduct->fired");
		var data = $("#add-product-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/products/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#add-product-container").html(response);
			},
			error : function(response) {
				console.log("response=", response);
				$("#add-product-container").html(response.responseText);
			}
		});
	}
</script>