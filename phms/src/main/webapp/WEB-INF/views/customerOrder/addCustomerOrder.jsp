<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

Adding new Customer Orders this is p
<div>Testing =${jsongCustomerOrderDetailD}</div>



<script type="text/javascript">
	var jsonDoctors = '${jsonDoctors}';
	var jsonDiscountTypes = '${jsonDiscountTypes}';
</script>


<div ng-app="addCustomerOrder" ng-controller="addCustomerOrder"
	ng-init="init()">

	<div>
		{{cusomerOrder|json}} <br> {{product|json}}
		</pre>

		<table>
			<tbody>
				<tr>
					<td>Customer Name</td>
					<td><input ng-model="cusomerOrder.customerName"></td>
				</tr>

				<tr>
					<td>Discount Type</td>
					<td><select ng-model="cusomerOrder.discountId">
							<option ng-repeat="item in discountTypes" value="{{item.id}}"
								value="{{item.id}}">{{item.discountType}}</option>
					</select></td>
				</tr>


				<tr>
					<td>Doctor</td>
					<td><select ng-model="cusomerOrder.doctorId">
							<option ng-repeat="item in  doctors" value="{{item.id}}">{{item.fullName}}</option>
					</select></td>
				</tr>
			</tbody>
		</table>

		<table>
			<tbody>
				<tr>
					<th>ProductCode</th>
					<th>ProductName</th>
					<th>Quantity</th>
					<th>Price</th>

					<th>UnitType</th>
				</tr>

				<tr>
					<th><input ng-model="product.code"
						ng-keypress="getProduct($event)"></th>
					<th><input ng-model="product.name"></th>

					<th><input ng-model="product.quantity"></th>

					<th><input ng-model="product.price">
						<button ng-click="addCustomerOrderDetail()">Add</button></th>
					<th><input ng-model="product.unitType"></th>
				</tr>
			</tbody>
			<tbody>
				<tr ng-repeat="item in cusomerOrder.customerOrderDetailDs">
					<td>{{item.productCode}}</td>
					<td>{{item.productName}}</td>
					<td>{{item.quantity}}</td>
					<td>{{item.price}}</td>
					<td>
						<button ng-click="removeCustomerOrderDetail($index)">D</button>
					</td>
				</tr>
			</tbody>
		</table>
		<button ng-click="addCustomerOrder()">Save</button>





	</div>