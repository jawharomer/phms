<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonDoctors = '${jsonDoctors}';
	var jsonDiscountTypes = '${jsonDiscountTypes}';
	var csrf = '${_csrf.token}';
</script>


<div ng-app="addCustomerOrder" ng-controller="addCustomerOrder"
	ng-init="init()" ng-form name="form">
	<h2>Sale Point</h2>

	<button class="btn btn-lg btn-outline-success"
		onClick="window.location.reload()">Refresh</button>

	<table class="table table-sm cus-table-borderless">
		<tbody>
			<tr>
				<td>Customer Name</td>
				<td><input class="form-control form-control-sm"
					ng-model="cusomerOrder.customerName"></td>
			</tr>

			<tr>
				<td>Discount Type</td>
				<td>
					<div class="form-row">
						<div class="col">
							<select class="form-control form-control-sm"
								ng-model="cusomerOrder.discountId"
								ng-options="item.id as item.discountType for item in discountTypes">
								<option value=""></option>
							</select>

						</div>
						<div class="col">
							% <input ng-disabled="!cusomerOrder.discountId"
								name="discountPercentage" type="number" max="100" min="0"
								ng-model="discountPercentage"
								class="w-50 cus-inline-block form-control form-control-sm">
						</div>
					</div>
				</td>
			</tr>


			<tr>
				<td>Doctor</td>
				<td><select class="form-control form-control-sm"
					ng-model="cusomerOrder.doctorId"
					ng-options="item.id as item.fullName for item in doctors">
						<option value=""></option>
				</select></td>
			</tr>
		</tbody>
	</table>

	<table class="table table-bordered table-sm">
		<tbody>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>S-Name</th>
				<th>County</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>UnitType</th>
				<th>Function</th>
			</tr>

			<tr>

				<th><input class="form-control form-control-sm"
					ng-model="product.code" ng-keypress="getProduct($event)"></th>
				<th><input class="form-control form-control-sm"
					ng-model="product.name" readonly></th>
				<th><input class="form-control form-control-sm"
					ng-model="product.scientificName" readonly></th>
				<th><input class="form-control form-control-sm"
					ng-model="product.country" readonly></th>

				<th><input type="number" class="form-control form-control-sm"
					ng-model="product.quantity" placeholder={{product.stockLevel}}></th>

				<th><input class="form-control form-control-sm"
					class="form-control form-control-sm" ng-model="product.price"
					readonly></th>
				<th><input class="form-control form-control-sm"
					ng-model="product.unitType" readonly></th>
				<th>
					<button
						ng-disabled="!product.quantity||product.quantity<0||!product.name"
						class="btn btn-sm btn-outline-primary"
						ng-click="addCustomerOrderDetail()">Add</button>
				</th>

			</tr>
		</tbody>
		<tbody>
			<tr ng-repeat="item in cusomerOrder.customerOrderDetailDs">
				<td>{{item.productCode}}</td>
				<td>{{item.productName}}</td>
				<td>{{item.scientificName}}</td>
				<td>{{item.country}}</td>
				<td>{{item.quantity}} <span class="text-info"
					ng-if="discountPercentage>0"> %{{discountPercentage}} </span>
				</td>
				<td>{{item.price}}</td>
				<td>&nbsp;</td>
				<td>
					<button class="btn btn-sm btn-outline-danger"
						ng-click="removeCustomerOrderDetail($index)">Delete</button>
				</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<div>
		<table style="width: 800px">
			<tr>
				<td>Total Price</td>
				<td><input ng-value="totalPrice()|number : 3"
					class="form-control form-control-sm" readonly></td>
			</tr>

			<tr ng-if="discountPercentage">
				<td>Total Price(Discount)</td>
				<td><input ng-value="totalPriceWithDiscount()|number : 3"
					class="form-control form-control-sm" readonly></td>
			</tr>
		</table>
	</div>
	<button ng-disabled="!form.$valid"
		class="btn btn-sm btn-outline-primary" ng-click="addCustomerOrder()">Save</button>
</div>