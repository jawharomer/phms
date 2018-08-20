<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div ng-app="customerOrders" ng-controller="customerOrders">

	<h4>CustomerOrders</h4>
	<hr>

	<div>
		<form action="<c:url value="/customerOrders" />">
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
					<td>CustomerName</td>
					<td>Time</td>
					<td>TotalPrice</td>
					<td>TotalPayment</td>
					<td>Discount</td>
					<td>Doctor</td>
					<td>DiscountType</td>
					<td class="cus-not-export">Function</td>
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
						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${item.totalPrice}" /></td>
						<td><c:if test="${item.discountAmount==null}">
								<fmt:formatNumber type="number" maxFractionDigits="3"
									value="${item.totalPrice}" />
							</c:if> <c:if test="${item.discountAmount!=null}">
								<fmt:formatNumber type="number" maxFractionDigits="3"
									value="${item.totalPrice*item.discountAmount}" />
							</c:if></td>
						<td>${item.discountAmount}</td>
						<td>${item.doctor.fullName}</td>
						<td>${item.discountType.discountType}</td>
						<td>
							<!--  
					<a class="btn btn-sm btn-outline-warning"
						href="<c:url value="/customerOrders/edit/" />${item.id}">E</a>
						-->
							<button class="btn btn-sm btn-outline-danger"
								ng-click="deleteCustomerOrder(${item.id})">D</button> <a
							target="_blank" class="btn btn-sm btn-outline-info"
							href="<c:url value="/customerOrders/" />${item.id}">V</a>
						</td>
					</tr>

					<c:set var="sumTotalPayment"
						value="${sumTotalPayment+totalPayment}" />

				</c:forEach>

			</tbody>

			<tfoot>
				<tr>
					<th>CustomerName</th>
					<th>Time</th>
					<th>TotalPrice</th>
					<th>TotalPayment</th>
					<th>Discount</th>
					<th>Doctor</th>
					<th>DiscountType</th>
					<th class="cus-not-search"></th>
				</tr>
			</tfoot>

		</table>
	</div>

	<hr>

	<div>
		<span>Sum Total Payment=</span> <span> <fmt:formatNumber
				type="number" maxFractionDigits="3" value="${sumTotalPayment}" />

		</span>
	</div>

</div>