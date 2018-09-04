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
					<td class="text-left">Country</td>
					<td><sf:select class="form-control" path="country">
							<c:forEach items="${countries}" var="item">
								<option value="${item.code}">${item.name}</option>
							</c:forEach>

						</sf:select></td>
					<td><sf:errors class="text-wrap" path="country" /></td>
				</tr>

				<tr>
					<td class="text-left">Scientific Name</td>
					<td><sf:input class="form-control" path="scientificName" /></td>
					<td><sf:errors class="text-wrap" path="scientificName" /></td>
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
					<td class="text-left">Category</td>
					<td><select class="form-control" name="productCategory[id]"
						value="${product.productCategory.id}">
							<option value="">Choose</option>
							<c:forEach items="${productCategories}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
					</select></td>
					<td><sf:errors path="productCategory" /> <sf:errors
							path="productCategory.id" /></td>
				</tr>

				<tr>
					<td><input class="btn btn-outline-primary" type="submit"
						value="Adding"></td>
				</tr>

			</tbody>

		</table>

	</sf:form>

</div>


<script>
	var csrf = '${_csrf.token}';
	function addProduct(event) {
		event.preventDefault();
		console.log("addProduct->fired");
		var data = $("#add-product-form").serializeJSON();
		
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/products/update"/>",
			headers : {
				'X-CSRF-TOKEN' : csrf
			},
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