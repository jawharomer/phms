<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


this is CustomerOrder page
<table>


	<tr>
		<td>Customer Name</td>
		<td>${customerOrder.customerName}</td>
	</tr>

	<tr>
		<td>Order Time</td>
		<td>${customerOrder.orderTime}</td>
	</tr>

	<tr>
		<td>Total Price</td>
		<td><c:set var="totalPrice" value="${0.0}" /> <c:forEach
				var="item" items="${customerOrder.customerOrderDetails}">
				<c:set var="totalPrice" value="${totalPrice + (item.price*item.quantity)}" />
			</c:forEach> ${totalPrice}</td>
	</tr>

</table>

<table>

	<thead>
		<tr>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Price</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${customerOrder.customerOrderDetails}" var="item">
			<tr>
				<td>${item.productCode}</td>
				<td>${item.quantity}</td>
				<td>${item.price}</td>
			</tr>
		</c:forEach>
	</tbody>

</table>




