<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-app="customerOrders" ng-controller="customerOrders">

	this is admin customer orders


	<table>
		<thead>
			<tr>
				<td>CustomerOrder</td>
				<td>OrderTime</td>
				<td>TotalPrice</td>
				<td>TotalPayment</td>
				<td>Discount</td>
				<td>Doctor</td>
				<td>DiscountType</td>
				<td>Function</td>
			</tr>
		</thead>
		<tbody>


			<c:set var="sumTotalPayment" value="${0}" />
			<c:forEach items="${customerOrders}" var="item">

				<c:set var="totalPayment" value="${0}" />
				<c:forEach var="i" items="${item.customerOrderDetails}">
					<c:set var="totalPayment"
						value="${totalPayment +(i.quantity*i.price)}" />
				</c:forEach>

				<tr>
					<td>${item.customerName}</td>
					<td>${item.orderTime}</td>
					<td>${item.totalPrice}</td>
					<td>${totalPayment}</td>
					<td>${item.totalPrice-totalPayment}</td>
					<td>${item.doctor.fullName}</td>
					<td>${item.discountType.discountType}</td>
					<td><a
						href="<c:url value="/customerOrders/edit/" />${item.id}">Edit</a></td>
					<td>
					<button ng-click="deleteCustomerOrder(${item.id})">D</button>
					</td>
				</tr>

				<c:set var="sumTotalPayment" value="${sumTotalPayment+totalPayment}" />

			</c:forEach>

		</tbody>

	</table>

	<div>
		<span>SumTotalPayment=</span> <span>${sumTotalPayment}</span>
	</div>