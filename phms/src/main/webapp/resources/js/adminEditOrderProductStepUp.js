$(document).ready()
{
	console.log("csrf=", csrf);

	$("#newProductStepUpExpirationDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

}

// Angular

appAddCusotmerOrder = angular.module("addCustomerOrder", []);

appAddCusotmerOrder.factory('httpRequestInterceptor', function() {
	return {
		request : function(config) {
			config.headers['X-CSRF-TOKEN'] = csrf;
			return config;
		}
	};
});
appAddCusotmerOrder.config(function($httpProvider) {
	$httpProvider.interceptors.push('httpRequestInterceptor');
});

appAddCusotmerOrder
		.controller(
				'addCustomerOrder',
				function($scope, $http, $q) {

					$scope.isNumber = angular.isNumber;

					$scope.vendors;
					$scope.selectedProduct;
					$scope.productStepUp = {
						product : {
							code : "",
							name : ""
						},
						expirationDate : "",
						quantity : "",
						bonusQuantity : "",
						paymentAmount : ""
					};
					$scope.resetProductStepUp = angular
							.copy($scope.productStepUp);

					$scope.orderProductStepUp = {};

					$scope.products;

					$scope.init = function() {

						console.log("init->fired");
						console.log("jsonVendors=", jsonVendors);
						console.log("jsonOrderProductStepUp=",
								jsonOrderProductStepUp);

						console.log("jsonProducts=", jsonProducts);

						$scope.vendors = JSON.parse(jsonVendors);
						$scope.orderProductStepUp = JSON
								.parse(jsonOrderProductStepUp);

						$scope.products = JSON.parse(jsonProducts);
						console.log("$scope.vendors=", $scope.vendors);
						console.log("$scope.orderProductStepUp=",
								$scope.orderProductStepUp);

						console.log("$scope.products=", $scope.products);

						var productAuto = [];

						angular.forEach($scope.products, function(value, key) {
							var obj = {
								label : value.name + " " + value.code,
								value : value.name,
								data : value
							}
							productAuto.push(obj);
						});

						$("#autoselect").autocomplete({
							source : productAuto,
							select : function(event, ui) {
								var item = ui.item.data;
								console.log("item=", item);
								var product = {
									code : item.code,
									name : item.name
								};
								$scope.selectedProduct = product;
								$scope.productStepUp.product = product;
								$scope.$digest();
							}
						});

					}

					$scope.totalPaymentAmount = function() {
						var totalPayment = 0;
						for (var i = 0; i < $scope.orderProductStepUp.productStepUps.length; i++) {
							totalPayment += $scope.orderProductStepUp.productStepUps[i].paymentAmount;
						}
						return totalPayment;
					}

					$scope.addProductStepUp = function() {
						console.log("addProductStepUp->fired");

						$http
								.get(
										$$ContextURL
												+ "/products/find/code/"
												+ $scope.productStepUp.product.code)
								.then(
										function(response) {

											console.log(response.data);
											$scope.productStepUp.product.id = response.data.productId;
											$scope.productStepUp.product.name = response.data.name;

											$scope.orderProductStepUp.productStepUps
													.push($scope.productStepUp);

											$scope.productStepUp = angular
													.copy($scope.resetProductStepUp);

											$scope.selectedProduct = null;

										},
										function(response) {
											console.error("error occured");
											$("#modal-body")
													.html(response.data);
											$("#modal").modal("show");
										});

					}

					$scope.deleteProductStepUp = function(index) {
						console.log("Delete item index=", index);
						console
								.log(
										"Delete item =",
										$scope.orderProductStepUp.productStepUps[index]);
						$scope.orderProductStepUp.productStepUps.splice(index,
								1);
					}

					$scope.saveOrderProductStepUp = function() {
						console.log("saveOrderProductStepUp->fired");
						$scope.orderProductStepUp.totalPaymentAmount = $scope
								.totalPaymentAmount();
						console.log($scope.orderProductStepUp);
						$http({
							method : 'POST',
							data : $scope.orderProductStepUp,
							url : $$ContextURL + '/orderProductStepUps/add'
						}).then(function(response) {
							console.log(response);
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						}, function(response) {
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						});

					}
				});