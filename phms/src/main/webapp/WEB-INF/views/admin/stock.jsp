<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

this is stock

<div>
	<!-- Button trigger modal -->
	<button id="cus-btn-addstudent" onclick="getAddProduct()" type="button"
		class="btn btn-primary" data-toggle="modal" data-target="#modal">ِAdding
		new product</button>
</div>


<table id="studentTable" class="display nowrap">
	<thead>
		<tr>
			<td>Product Code</td>
			<td>Product Name</td>
			<td>Unit Type</td>
			<td>StockLevel</td>
			<td>Cost</td>
			<td>Price</td>
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
					<div>
						<button data-product-id="${productD.productId}"
							onclick="deleteProduct(this)">delete</button>
						<button data-product-id="${productD.productId}"
							onclick="editProduct(this)">edit</button>

						<button data-product-id="${productD.productId}"
							onclick="productStepUp(this)">StepUp</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>


</table>





