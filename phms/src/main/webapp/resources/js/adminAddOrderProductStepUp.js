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
					$scope.productStepUp = {
						product : {
							code : ""
						},
						expirationDate : "",
						quantity : "",
						bonusQuantity : "",
						paymentAmount : ""
					};
					$scope.resetProductStepUp = angular
							.copy($scope.productStepUp);

					$scope.orderProductStepUp = {};

					$scope.init = function() {

						console.log("init->fired");
						console.log("jsonVendors=", jsonVendors);
						console.log("jsonOrderProductStepUp=",
								jsonOrderProductStepUp);

						$scope.vendors = JSON.parse(jsonVendors);
						$scope.orderProductStepUp = JSON
								.parse(jsonOrderProductStepUp);
						console.log("$scope.vendors=", $scope.vendors);
						console.log("$scope.orderProductStepUp=",
								$scope.orderProductStepUp);
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