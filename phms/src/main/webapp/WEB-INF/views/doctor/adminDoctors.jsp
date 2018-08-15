<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />


<div ng-app="adminDoctors" ng-controller="adminDoctors"
	class="admin-doctors">
	
		<h2>Doctors</h2>

	<div class="add-new-doctor-div">
		<button class="btn btn-outline-primary" ng-click="getAddingDoctor()">Add</button>
	</div>

	<table class="table">
		<thead>
			<tr>
				<td>Doctor Name</td>
				<td>Doctor Phone</td>
				<td>Profit</td>
				<td>Function</td>
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
							<button class="btn btn-outline-danger"
								ng-click="deleteDoctor(${item.id})">Delete</button>
							<button class="btn btn-outline-warning"
								ng-click="editDoctor(${item.id})">Edit</button>
							<a class="btn  btn-outline-info"
								href="<c:url value="/doctors/"/>${item.id}/customerOrders?from=${currentDate}&to=${currentDate}">Invoice
							</a>
						</div>
					</td>


				</tr>
			</c:forEach>

		</tbody>

	</table>

</div>





