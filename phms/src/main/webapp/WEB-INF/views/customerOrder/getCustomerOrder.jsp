<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>

<style type="text/css">
.cus-center th, .cus-center td {
	text-align: center;
}

.print-btn-con {
	width: 500px;
	margin: 0 auto;
}

.print-btn-con button {
	font-size: 2em;
}

.cus-center {
	text-align: center;
}

@media print {
	@page {
		size: auto;
		margin: 0;
	}
	.cus-no-print, .cus-no-print * {
		display: none !important;
	}
}
</style>


</head>

<body>

	<div class="cus-no-print cus-center  print-btn-con">
		<button onclick="window.print()">Print</button>
		<hr>
	</div>

	<div style="width: 500px; margin: 0 auto">


		<h3 class="cus-center">
			<b>Bahceci Pharmacy</b>
		</h3>

		<p>
			# [${customerOrder.id} ]
			<fmt:formatDate pattern="yyyy-MM-dd  hh:mm:ss"
				value="${customerOrder.orderTime}" />
		</p>

		<table style="width: 100%">
			<tr>
				<td>Customer Name</td>
				<td>${customerOrder.customerName}</td>
			</tr>
			<tr>
				<td>Total Price</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${customerOrder.totalPrice}" /></td>
			</tr>

			<c:if test="${customerOrder.discountAmount!=null}">
				<tr>
					<td>Discount Amount</td>
					<td>(${customerOrder.discountAmount})-${customerOrder.discountAmount*100}%</td>
				</tr>
				<tr>
					<td>Total Price (Discount)</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${customerOrder.totalPrice*customerOrder.discountAmount}" />
					</td>
				</tr>

			</c:if>

		</table>
		<hr>

		<table class="cus-center" style="width: 100%">

			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customerOrder.customerOrderDetails}" var="item">
					<tr>
						<td>${item.productName}</td>
						<td>${item.quantity}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${item.price}" /></td>

						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${item.price*item.quantity}" /></td>

					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>


</body>
</html>




