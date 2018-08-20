<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />


<script>
	var csrf = '${_csrf.token}';
</script>


<div ng-app="adminProductCategories"
	ng-controller="adminProductCategories" class="admin-doctors">

	<div class="add-new-doctor-div">
		<button class="btn btn-outline-primary"
			ng-click="getAddingProductCategory()">Add</button>
	</div>

	<table class="table">
		<thead>
			<tr>
				<td>#ID</td>
				<td>Name</td>
				<td>Functions</td>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${productCategories}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>
						<div>
							<button class="btn btn-outline-danger"
								ng-click="deleteProductCategory(${item.id})">Delete</button>
							<button class="btn btn-outline-warning"
								ng-click="editProductCategory(${item.id})">Edit</button>
						</div>
					</td>


				</tr>
			</c:forEach>

		</tbody>

	</table>

</div>





