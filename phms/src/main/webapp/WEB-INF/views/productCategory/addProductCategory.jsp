<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-product-category-container">

	Adding new ProductCategory

	<sf:form id="add-product-category-form" method="POST"
		commandName="productCategory" onsubmit="addProductCategory(event)">


		<table>
			<tbody>

				<tr>
					<td class="text-left">Name</td>
					<td><sf:input class="form-control" path="name" /></td>
					<td><sf:errors path="name" /></td>
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
	function addProductCategory(event) {
		event.preventDefault();
		console.log("addProductCategory->fired");

		var data = $("#add-product-category-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/productCategories/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#add-product-category-container").html(response);
			},
			error : function(response) {
				console.log(response);
				$("#add-product-category-container").html(response.responseText);
			}
		});
	}
</script>