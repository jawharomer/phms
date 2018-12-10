<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div ng-app="productStepUps" ng-controller="productStepUps">


	<h2>Product Orders</h2>

	<div>

		<table id="productStepUpsTable" class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Vendor</th>
					<th>Time</th>
					<th>Reference</th>
					<th>TotalPayment</th>
					<th>Discount</th>
					<th class="cus-not-export">F</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="sumTotalPayment" value="${0}" />
				<c:forEach items="${orderProductStepUps}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.vendor.fullName}</td>
						<td>${item.orderTime}</td>
						<td>${item.referecneInvoiceId}</td>
						<td><fmt:formatNumber value="${item.totalPaymentAmount}" /></td>
						<td>${item.discount}</td>
						<td>
							<div>
								<button class="btn btn-danger btn-sm"
									ng-click="deleteOrderProductStepUp(${item.id})">
									<i class="fa fa-times"></i>
								</button>

								<a href="<c:url value="/orderProductStepUps/edit/" />${item.id}"
									class="btn btn-warning btn-sm"> <i class="fa fa-edit"></i>
								</a>

							</div>
						</td>
					</tr>
					<c:set var="sumTotalPayment"
						value="${sumTotalPayment+item.totalPaymentAmount}" />

				</c:forEach>

			</tbody>
		</table>

	</div>

</div>