<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div>
	<form action="<c:url value="/doctors/" />${doctorId}/customerOrders">
		<table>
			<tr>
				<td class="text-left">From</td>
				<td><input class="form-control" id="from" name="from"
					value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
			</tr>

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
		<hr />
	</form>

</div>
<div>

	<table id="customerOrdersTable" class="display nowrap">
		<thead>
			<tr>
				<th>Customer Name</th>
				<th>Order Time</th>
				<th>Total Price</th>
				<th>Total Payment</th>
				<th>Discount Type</th>
				<th>Income</th>
				<th>#Invoice</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doctorCustomerOrderDs}" var="item">
				<tr>
					<td>${item.customerName}</td>
					<td>${item.orderTime}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${item.totalPrice}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${item.totalPayment}" /></td>
					<td>${item.discountType}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${item.income}" /></td>
					<td>${item.customerOrderId}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>Customer Name</th>
				<th>Order Time</th>
				<th>Total Price</th>
				<th>Total Payment</th>
				<th>Discount Type</th>
				<th>Income</th>
				<th>#Invoice</th>
			</tr>
		</tfoot>
	</table>
</div>
<hr />

<div>
	<c:set var="totalIncome" value="${0}" />
	<c:forEach var="item" items="${doctorCustomerOrderDs}">
		<c:set var="totalIncome" value="${totalIncome + item.income}" />
	</c:forEach>
	<h6 class="text-info">
		<span> Total Income: </span> <span> <fmt:formatNumber
				type="number" maxFractionDigits="3" value="${totalIncome}" />
		</span>
	</h6>
</div>