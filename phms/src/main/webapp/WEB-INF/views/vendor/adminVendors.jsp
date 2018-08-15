<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />


<div ng-app="adminVendors" ng-controller="adminVendors"
	class="admin-doctors">

	<div class="add-new-doctor-div">
		<button class="btn btn-outline-primary" ng-click="getAddingVendor()">Add</button>
	</div>

	<table class="table">
		<thead>
			<tr>
				<td>Full Name</td>
				<td>Phone</td>
				<td>Address</td>
				<td>Note</td>
				<td>Function</td>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${vendors}" var="item">
				<tr>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td>${item.address}</td>
					<td>${item.note}</td>
					<td>
						<div>
							<button class="btn btn-outline-danger"
								ng-click="deleteVendor(${item.id})">Delete</button>
							<button class="btn btn-outline-warning"
								ng-click="editVendor(${item.id})">Edit</button>
						</div>
					</td>


				</tr>
			</c:forEach>

		</tbody>

	</table>

</div>





