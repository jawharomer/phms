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
					<td><input class="btn btn-outline-warning" type="submit"
						value="Edit"></td>
				</tr>

			</tbody>

		</table>


	</sf:form>

</div>


<script>
	var csrf = '${_csrf.token}';
	function modalEditProduct(event) {
		console.log("modalEditProduct->fired");
		event.preventDefault();
		var data = JSON.stringify($("#editProductForm").serializeJSON());
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/products/update"/>",
			data : data,
			headers : {
				"X-CSRF-TOKEN" : csrf
			},
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