<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />


<div ng-app="adminDoctors" ng-controller="adminDoctors">

	This is doctos page={{1+1}}

	<div>
		<button ng-click="getAddingDoctor()">Add new Doctor</button>

	</div>

	<table>
		<thead>
			<tr>
				<td>Doctor Name</td>
				<td>Doctor Phone</td>
				<td>Profit</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${doctors}" var="item">
				<tr>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td>${item.profit}</td>
					<td>
						<div>
							<button ng-click="deleteDoctor(${item.id})">Delete</button>
							<button ng-click="editDoctor(${item.id})">Edit</button>
							<a
								href="<c:url value="/doctors/"/>${item.id}/customerOrders?from=${currentDate}&to=${currentDate}">Invoice
								</button>
						</div>
					</td>


				</tr>
			</c:forEach>

		</tbody>

	</table>

</div>





