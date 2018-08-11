<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div>
	<form action="<c:url value="/doctors/" />${doctorId}/customerOrders">
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
			<td>customerName</td>
			<td>orderTime</td>
			<td>totalPrice</td>
			<td>totalPayment</td>
			<td>discountType</td>
			<td>income</td>
			<td>Detail</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${doctorCustomerOrderDs}" var="item">
			<tr>
				<td>${item.customerName}</td>
				<td>${item.orderTime}</td>
				<td>${item.totalPrice}</td>
				<td>${item.totalPayment}</td>
				<td>${item.discountType}</td>
				<td>${item.income}</td>
				<td>${item.customerOrderId}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div>
	<c:set var="totalIncome" value="${0}" />
	<c:forEach var="item" items="${doctorCustomerOrderDs}">
		<c:set var="totalIncome" value="${totalIncome + item.income}" />
	</c:forEach>
	<span> Total Income </span> <span> ${ totalIncome} </span>
</div>