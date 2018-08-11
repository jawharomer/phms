<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-app="productStepUps" ng-controller="productStepUps">

	This is Admin product stepups

	<div>
		<form action="<c:url value="/productStepUps" />">
			<table>
				<tr>
					<td>From</td>
					<td><input id="from" name="from" /></td>
				</tr>

				<tr>
					<td>To</td>
					<td><input id="to" name="to" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="View" /></td>
				</tr>
			</table>
		</form>

	</div>


	<table>
		<thead>
			<tr>
				<td>ProductCode</td>
				<td>Invoice Time</td>
				<td>expirationDate</td>
				<td>productionDate</td>
				<td>vendor</td>
				<td>paymentAmount</td>
				<td>Quantity</td>
				<td>soldQuantity</td>
				<td>bonusQuantity</td>
				<td>note</td>
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
					<td>${item.paymentAmount}</td>
					<td>${item.quantity}</td>
					<td>${item.soldQuantity}</td>
					<td>${item.note}</td>
					<td>
						<button ng-click="deleteProductStepUp(${item.id})">D</button>
					</td>
				</tr>

				<c:set var="sumTotalPayment"
					value="${sumTotalPayment+paymentAmount}" />

			</c:forEach>

		</tbody>

	</table>

	<div>
		<span>sumTotalPayment=</span> <span>${sumTotalPayment}</span>
	</div>

</div>