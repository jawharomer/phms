<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>

	<h2>Stock</h2>

	<div id="cus-add-div">
		<button id="cus-btn-addstudent" onclick="getAddProduct()"
			type="button" class="btn btn-outline-primary" data-toggle="modal"
			data-target="#modal">ِAdd</button>
	</div>


	<table class="table">
		<thead>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>UnitType</th>
				<th>StockLevel</th>
				<th>Cost</th>
				<th>Price</th>
				<th>Function</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productDs}" var="productD">
				<tr>
					<td>${productD.code}</td>
					<td>${productD.name}</td>
					<td>${productD.unitType}</td>
					<td>${productD.stockLevel}</td>
					<td>${productD.cost}</td>
					<td>${productD.price}</td>
					<td>
						<div class="cus-table-function-div">
							<button class="btn btn-outline-danger" data-product-id="${productD.productId}"
								onclick="deleteProduct(this)">delete</button>
							<button class="btn btn-outline-warning" data-product-id="${productD.productId}"
								onclick="editProduct(this)">edit</button>

							<button class="btn btn-outline-primary" data-product-id="${productD.productId}"
								onclick="productStepUp(this)">StepUp</button>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>


	</table>


</div>




