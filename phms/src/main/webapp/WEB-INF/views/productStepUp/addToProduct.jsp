<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div id="add-product-step-up-container">
	Adding new product StepUp
	<sf:form id="add-product-step-up-form" method="POST"
		commandName="productStepUpD" onsubmit="addProductStepUp(event)">
		<sf:input path="productId" type="hidden" />
		<table>
			<tbody>
				<tr>
					<td>vendorId</td>
					<td><sf:select path="vendorId">
							<c:if test="${productStepUpD.vendorId==null}">
								<option value="null">Vendor</option>
							</c:if>
							<c:forEach items="${vendors}" var="item">
								<c:choose>
									<c:when test="${item.id==productStepUpD.vendorId}">
										<option value="${item.id}" selected="true">${item.fullName}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.id}">${item.fullName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</sf:select>
						<button onclick="getAddVendor(event)">Add</button></td>
					<td><sf:errors path="vendorId" /></td>
				</tr>
				<tr>
					<td>expirationDate</td>
					<td><sf:input path="expirationDate" /></td>
					<td><sf:errors path="expirationDate" /></td>
				</tr>
				<tr>
					<td>productionDate</td>
					<td><sf:input path="productionDate" /></td>
					<td><sf:errors path="productionDate" /></td>
				</tr>
				<tr>
					<td>quantity</td>
					<td><sf:input path="quantity" /></td>
					<td><sf:errors path="quantity" /></td>
				</tr>
				<tr>
					<td>bonusQuantity</td>
					<td><sf:input path="bonusQuantity" /></td>
					<td><sf:errors path="bonusQuantity" /></td>
				</tr>
				<tr>
					<td>paymentAmount</td>
					<td><sf:input path="paymentAmount" /></td>
					<td><sf:errors path="paymentAmount" /></td>
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
	$(document).ready()
	{
		$("#expirationDate,#productionDate").datepicker({
			dateFormat : "yy-mm-dd"
		}).datepicker("setDate", new Date());
	}
	function addProductStepUp(event) {
		event.preventDefault();
		console.log("addProductStepUp->fired");
		var data = $("#add-product-step-up-form").serializeObject();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/productStepUps/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#add-product-step-up-container").html(response);
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
	function getAddVendor(event) {
		event.preventDefault();
		console.log("getAddVendor->fired");
		$.get($$ContextURL + "/vendors/add", function(response) {
			console.log("response=", response);
			$("#modal-body").html(response);
			$("#modal-body").modal("show");
		});
	}
</script>
