<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div ng-app="productStepUps" ng-controller="productStepUps">



	<c:if test="${searchBy=='orderDate'}">
		<h2>Added To Stock</h2>
	</c:if>

	<c:if test="${searchBy=='expirationDate'}">
		<h2>Expiration From Stock</h2>
	</c:if>
	<hr>

	<div>
		<form action="<c:url value="/productStepUps/search/" />${searchBy}">
			<table>



				<c:if test="${searchBy=='orderDate'}">

					<tr>
						<td class="text-left">From</td>
						<td><input class="form-control" id="from" name="from"
							value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
					</tr>


				</c:if>
				<tr>
					<td class="text-left">To</td>
					<td><input class="form-control" id="to" name="to"
						value="<fmt:formatDate pattern = "yyyy-MM-dd" 
         value = "${to}" />" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-outline-info" type="submit"
						value="View" /></td>
				</tr>
			</table>
			<hr>
		</form>

	</div>


	<table id="productStepUpsTable" class="display">
		<thead>
			<tr>
				<th>Code</th>
				<th>Time</th>
				<th>Expiration</th>
				<th>Production</th>
				<th>Vendor</th>
				<th>Payment</th>
				<th>Quantity</th>
				<th>Sold</th>
				<th>Remain</th>
				<th>Bonus</th>
				<th>Note</th>
				<th>Function</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="sumTotalPayment" value="${0}" />

			<c:forEach items="${productStepUps}" var="item">


				<tr>
					<td>${item.product.code}</td>
					<td>${item.time}</td>
					<td>${item.expirationDate}</td>
					<td>${item.productionDate}</td>
					<td>${item.vendor.fullName}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${item.paymentAmount}" /></td>
					<td>${item.quantity}</td>
					<td>${item.soldQuantity}</td>
					<td>${item.quantity-item.soldQuantity}</td>
					<td>${item.bonusQuantity}</td>
					<td>${item.note}</td>
					<td>
						<button class="btn btn-sm btn-outline-danger"
							ng-click="deleteProductStepUp(${item.id})">D</button>
					</td>
				</tr>

				<c:set var="sumTotalPayment"
					value="${sumTotalPayment+item.paymentAmount}" />

			</c:forEach>

		</tbody>

		<tfoot>
			<tr>
				<th>Code</th>
				<th>Time</th>
				<th>Expiration</th>
				<th>Production</th>
				<th>Vendor</th>
				<th>Payment</th>
				<th>Quantity</th>
				<th>Sold</th>
				<th>Bonus</th>
				<th>Note</th>
				<th>Function</th>
			</tr>
		</tfoot>


	</table>

	<hr>

	<div>
		<h6 class="text-info">
			<span>Summation Of TotalPayment=</span> <span> <fmt:formatNumber
					type="number" maxFractionDigits="3" value="${sumTotalPayment}" />
			</span>
		</h6>
	</div>

</div>