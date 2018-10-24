<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonVendors = '${jsonVendors}';
	var jsonOrderProductStepUp = '${jsonOrderProductStepUp}';
	var jsonProducts = '<spring:escapeBody  javaScriptEscape="true">${jsonProducts}</spring:escapeBody>';
	var csrf = '${_csrf.token}';
</script>



<div ng-app="addCustomerOrder" ng-controller="addCustomerOrder"
	ng-init="init()">
	<h2>Order</h2>

	<table class="table table-sm cus-table-borderless">
		<tbody ng-form="orderProductStepUpForm">
			<tr>
				<td>Vendor</td>
				<td><select required class="form-control form-control-sm"
					ng-model="orderProductStepUp.vendor.id"
					ng-options="item.id as item.fullName for item in vendors">
						<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td title="Most of the time Vendor's Invoice ID">Reference</td>
				<td><input type="number"
					ng-model="orderProductStepUp.referecneInvoiceId"
					class="form-control  form-control-sm"></td>
			</tr>

			<tr>
				<td title="Discount 0-1">Discount</td>
				<td><input type="number" min="0" max="1"
					ng-model="orderProductStepUp.discount"
					class="form-control  form-control-sm"></td>
			</tr>
		</tbody>
	</table>


	<div class="border-top">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>P-Name</td>
					<td>P-Code</td>
					<td>P-Unit Type</td>
					<td>QTY</td>
					<td>EXP-Date</td>
					<td>Bonus-QTY</td>
					<td>Payment</td>
					<td>Note</td>
					<td>&nbsp;</td>
				</tr>
				<tr ng-form="newProductStepUpForm">
					<td><input id="autoselect" ng-change="selectedProduct=null"
						ng-model="productStepUp.product.name"
						class="form-control form-control-sm"></td>
					<td><input ng-blur="getProduct()"
						ng-readonly="selectedProduct" required
						ng-model="productStepUp.product.code"
						class="form-control form-control-sm"></td>
					<td><input readonly="readonly"
						ng-model="productStepUp.product.unitType"
						class="form-control form-control-sm"></td>
					<td><input type="number" min="1" required
						ng-model="productStepUp.quantity"
						class="form-control form-control-sm">
						{{productStepUp.product.packetSize}}</td>
					<td><input id="newProductStepUpExpirationDate" required
						ng-model="productStepUp.expirationDate"
						class="form-control form-control-sm"></td>
					<td><input type="number" min="1"
						ng-model="productStepUp.bonusQuantity"
						class="form-control form-control-sm"></td>
					<td><input type="number" min="1" required
						ng-model="productStepUp.paymentAmount"
						class="form-control form-control-sm"></td>
					<td><input ng-model="productStepUp.note"
						class="form-control form-control-sm"></td>
					<td>
						<button ng-disabled="newProductStepUpForm.$invalid"
							class="btn btn-success bnt-sm rounded-circle"
							ng-click="addProductStepUp()">
							<i class="fa fa-plus"></i>
						</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="item in orderProductStepUp.productStepUps">
					<td>{{item.product.name}}</td>
					<td>{{item.product.code}}</td>
					<td>{{item.expirationDate}}</td>
					<td>{{item.quantity}}</td>
					<td>{{item.bonusQuantity}}</td>
					<td>{{item.paymentAmount|number}}</td>
					<td>{{item.note}}</td>
					<td>
						<button class="btn btn-danger btn-sm rounded-circle"
							ng-click="deleteProductStepUp($index)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<div class="border-top pt-2">
		<div>
			Total Payment&nbsp;<input ng-value="totalPaymentAmount()|number"
				style="width: 200px"
				class="d-inline-block form-control form-control-sm"
				disabled="disabled">
		</div>

		<button
			ng-disabled="!orderProductStepUp||!orderProductStepUp.productStepUps.length>0||orderProductStepUpForm.$invalid"
			class="btn btn-success" ng-click="saveOrderProductStepUp()">
			<i class="fa fa-plus"></i>
		</button>
	</div>

</div>