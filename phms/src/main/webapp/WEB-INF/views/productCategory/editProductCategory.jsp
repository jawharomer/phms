<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="edit-product-category-container">

	Edit  ProductCategory

	<sf:form id="edit-product-category-form" method="POST"
		commandName="productCategory" onsubmit="editProductCategory(event)">


		<sf:input path="id" type="hidden" />

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
	function editProductCategory(event) {
		event.preventDefault();
		console.log("editProductCategory->fired");

		var data = $("#edit-product-category-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/productCategories/update"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#edit-product-category-container").html(response);
			},
			error : function(response) {
				$("#edit-product-category-container").html(response.responseText);
			}
		});
	}
</script>