<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonDoctors = '${jsonDoctors}';
	var jsonDiscountTypes = '${jsonDiscountTypes}';
</script>


<div ng-app="addCustomerOrder" ng-controller="addCustomerOrder"
	ng-init="init()" ng-form name="form">
	<h2>Sale Point</h2>



	<table class="table">
		<tbody>
			<tr>
				<td>Customer Name</td>
				<td><input class="form-control"
					ng-model="cusomerOrder.customerName"></td>
			</tr>

			<tr>
				<td>Discount Type</td>
				<td><select class="form-control"
					ng-model="cusomerOrder.discountId">
						<option ng-repeat="item in discountTypes" value="{{item.id}}"
							value="{{item.id}}">{{item.discountType}}</option>
				</select></td>
			</tr>


			<tr>
				<td>Doctor</td>
				<td><select class="form-control"
					ng-model="cusomerOrder.doctorId">
						<option ng-repeat="item in  doctors" value="{{item.id}}">{{item.fullName}}</option>
				</select></td>
			</tr>
		</tbody>
	</table>

	<table class="table">
		<tbody>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>UnitType</th>
				<th>Function</th>
			</tr>

			<tr>
				<th><input class="form-control" ng-model="product.code"
					ng-keypress="getProduct($event)"></th>
				<th><input class="form-control" ng-model="product.name"
					readonly></th>

				<th><input class="form-control" ng-model="product.quantity"></th>

				<th><input class="form-control" class="form-control"
					ng-model="product.price"></th>
				<th><input class="form-control" ng-model="product.unitType"
					readonly></th>
				<th>
					<button class="btn btn-outline-primary"
						ng-click="addCustomerOrderDetail()">Add</button>
				</th>

			</tr>
		</tbody>
		<tbody>
			<tr ng-repeat="item in cusomerOrder.customerOrderDetailDs">
				<td>{{item.productCode}}</td>
				<td>{{item.productName}}</td>
				<td>{{item.quantity}}</td>
				<td>{{item.price}}</td>
				<td>&nbsp;</td>
				<td>
					<button class="btn btn-outline-danger"
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
					class="form-control" readonly></td>
			</tr>

			<tr>
				<td>Payment Amount</td>
				<td>
					<div>
						<div style="display: inline-block">
							<input ng-value="paymentAmount()| number : 3"
								class="form-control" readonly>
						</div>
						<div style="display: inline-block">
							Discount % <input class="cus-inline-block form-control"
								ng-class={"is-invalid":!form.discountPercentage.$valid} type="number"
								name="discountPercentage" ng-model="discountPercentage" min="0"
								max="100">
						</div>
					</div>
				</td>

			</tr>
		</table>
	</div>
	<button ng-disabled="!form.$valid" class="btn btn-outline-primary"
		ng-click="addCustomerOrder()">Save</button>
</div>