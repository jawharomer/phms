<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	var reportTitle = '<fmt:formatDate value="${from}" pattern="yyyy-MM-dd"/>_To_<fmt:formatDate value="${to}" pattern="yyyy-MM-dd"/>';
</script>

<div>

	<h4>Product Sold</h4>
	<hr>

	<div>
		<form action="<c:url value="/customerOrders/sold" />">
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
		</form>


	</div>

	<hr>

	<div style="overflow: auto">
		<table id="customerOrdersTable" class="display nowrap">
			<thead>
				<tr>
					<td>#CO</td>
					<td>ProductName</td>
					<td>Product Code</td>
					<td>Payment</td>
					<td>Cost</td>
					<td>Profit</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalPayment" value="${0}" />
				<c:set var="totalCost" value="${0}" />
				<c:set var="totalProfit" value="${0}" />
				<c:forEach items="${customerOrders}" var="order">
					<c:forEach items="${order.customerOrderDetails}" var="orderDetail">
						<tr>
							<td>${order.id}</td>
							<td>${orderDetail.productName}</td>
							<td>${orderDetail.productCode}</td>
							<td><c:set var="payment"
									value="${orderDetail.quantity*orderDetail.price}" /> <c:if
									test="${order.discountAmount!=null}">
									<fmt:formatNumber var="discount" maxFractionDigits="3">
									${order.discountAmount}	
									</fmt:formatNumber>
									<c:set var="payment" value="${payment-payment*discount}" />
								</c:if> <fmt:formatNumber maxFractionDigits="3">${payment}
								</fmt:formatNumber></td>
							<c:set var="totalPayment" value="${totalPayment+payment}" />
							<td><c:set var="cost" value="${0}" /> <c:forEach
									items="${orderDetail.productStepUpIds}" var="stepUp">
									<c:set var="cost"
										value="${cost+(stepUp.paymentAmount/stepUp.quantity)}" />
								</c:forEach> <fmt:formatNumber maxFractionDigits="3">
								 ${cost}
								 </fmt:formatNumber> <c:set var="totalCost" value="${totalCost+cost}" /></td>
							<td><fmt:formatNumber maxFractionDigits="3">
								${payment-cost}
								</fmt:formatNumber></td>
							<c:set var="totalProfit" value="${totalProfit+(payment-cost)}" />
						</tr>
					</c:forEach>

				</c:forEach>
				<tr class="text-info">
					<td>Total</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><fmt:formatNumber maxFractionDigits="3">
								${totalPayment}
								</fmt:formatNumber></td>
					<td><fmt:formatNumber maxFractionDigits="3">
								${totalCost}
								</fmt:formatNumber></td>
					<td><fmt:formatNumber maxFractionDigits="3">
								${totalProfit}
								</fmt:formatNumber></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th>#CO</th>
					<th>ProductName</th>
					<th>Product Code</th>
					<th>Payment</th>
					<th>Cost</th>
					<th>Profit</th>
				</tr>
			</tfoot>

		</table>
	</div>

</div>