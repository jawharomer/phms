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
					<td class="text-left">Country</td>
					<td><sf:select class="form-control" path="country">
							<c:forEach items="${countries}" var="item">
								<c:choose>
									<c:when test="${item.code==product.country}">
										<option selected="selected" value="${item.code}">${item.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.code}">${item.name}</option>
									</c:otherwise>
								</c:choose>

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
					<td><select id="productUnit"  onchange="changeProductUnit()" class="form-control" name="productUnitType[id]">
							<option value="">Choose</option>
							<c:forEach items="${productUnitTypes}" var="item">
								<c:if test="${product.productUnitType.id==item.id}">
									<option selected="selected" value="${item.id}">${item.name}</option>
								</c:if>
								<c:if test="${product.productUnitType.id!=item.id}">
									<option value="${item.id}">${item.name}</option>
								</c:if>
							</c:forEach>


					</select></td>



					<td><sf:errors path="productUnitType" /></td>
				</tr>


				<tr>
					<td class="text-left">Packet Size</td>
					<td><sf:input type="number" class="form-control"
							path="packetSize" /></td>
					<td><sf:errors path="packetSize" /></td>
				</tr>

				<tr>
					<td class="text-left">Category</td>
					<td><select class="form-control" name="productCategory[id]">
							<option value="">Choose</option>
							<c:forEach items="${productCategories}" var="item">
								<c:if test="${product.productCategory.id==item.id}">
									<option selected="selected" value="${item.id}">${item.name}</option>
								</c:if>
								<c:if test="${product.productCategory.id!=item.id}">
									<option value="${item.id}">${item.name}</option>
								</c:if>
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
	
	function changeProductUnit() {
		console.log("changeProductUnit->fired");
		var unitType = $('#productUnit option:selected').text();
		console.log("unitType=" + unitType);
		if (unitType != "pack") {
			$("#packetSize").val("");
			$("#packetSize").prop('disabled', true);
		} else {
			$("#packetSize").prop('disabled', false);
		}

	}
	
	
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