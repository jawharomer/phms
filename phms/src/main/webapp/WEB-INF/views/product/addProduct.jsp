<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-product-container">

	Addint new Product Page

	<sf:form id="add-product-form" method="POST" commandName="product"
		onsubmit="addProduct(event)">
		<table>
			<tbody>
				<tr>
					<td>Code</td>
					<td><sf:input path="code" /></td>
					<td><sf:errors path="code" /></td>
				</tr>

				<tr>
					<td>Name</td>
					<td><sf:input path="name" /></td>
					<td><sf:errors path="name" /></td>
				</tr>
				
				<tr>
					<td>Profit</td>
					<td><sf:input path="profit" /></td>
					<td><sf:errors path="profit" /></td>
				</tr>

				<tr>
					<td>unitType</td>
					<td><sf:input path="unitType" /></td>
					<td><sf:errors path="unitType" /></td>
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
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>