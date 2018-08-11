appAddCusotmerOrder = angular.module("editCustomerOrder", []);

appAddCusotmerOrder.controller('editCustomerOrder', function($scope, $http) {

	$scope.doctors;

	$scope.cusomerOrder = {};

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonDoctors=", jsonDoctors);
		console.log("jsonCustomerOrderD=", jsonCustomerOrderD);

		$scope.doctors = JSON.parse(jsonDoctors);
		$scope.cusomerOrder=JSON.parse(jsonCustomerOrderD);

		console.log("$scope.doctors=", $scope.doctors);
		console.log("$scope.cusomerOrder=", $scope.cusomerOrder);

	};

	$scope.product = {
		productId : "",
		code : "",
		name : "",
		unitType : "",
		stockLevel : "",
		cost : "",
		profit : "",
		price : "",
		quantity : ""
	};
	$scope.addedProduct = [];

	$scope.resetProduct = $scope.product;

	$scope.getProduct = function(event) {
		console.log("getProduct->fired");
		if (event.which == 13) {

			console.log($scope.product.code);
			$scope.product = $scope.resetProduct;
			$http
					.get(
							$$ContextURL + "/products/find/code/"
									+ $scope.product.code).then(
							function(response) {
								$scope.product = response.data;
							}, function(response) {
								console.error("error occured");
								$scope.content = "Something went wrong";
							});

		}
	}

	$scope.addCustomerOrderDetail = function() {
		console.log("addCustomerOrderDetail->fired");

		var customerOrderDetail = {
			productId : $scope.product.productId,
			productCode : $scope.product.code,
			productName : $scope.product.name,
			quantity : $scope.product.quantity,
			price : $scope.product.price
		};
		$scope.cusomerOrder.customerOrderDetailDs.push(customerOrderDetail);
		console.log($scope.cusomerOrder.customerOrderDetailDs);
		$scope.product = $scope.resetProduct;
	}

	$scope.editCustomerOrder = function() {
		console.log("editCustomerOrder->fired");
		
		console.log("$scope.cusomerOrder=", $scope.cusomerOrder);

		$http({
			method : 'POST',
			data : $scope.cusomerOrder,
			url : $$ContextURL + '/customerOrders/update'
		}).then(function(response) {
			console.log(response);
			var outPut = `
			
			<div>${response.data.message}
			</div>
			<div>
			<a target="_blank" href="${$$ContextURL}/customerOrders/${response.data.etc}">View</a></div>
			`;
			console.log("outPut=",outPut);
			
			$("#modal-body").html(outPut);
			$("#modal").modal("show");
		}, function(response) {
			console.error("error occured");
			if (response.data.fieldErrors) {
				console.log(response.data.fieldErrors);
				var outPut = `<table><tbody>`;
				response.data.fieldErrors.forEach(function(element) {
					console.log("element=", element);
					outPut += `<tr><td>${element.message}</td></tr>`;
				});
				outPut += `</tbody></table>`;
				console.log("outPut=", outPut);
				$("#modal-body").html(outPut);
				$("#modal").modal("show");
			} else {
				$("#modal-body").html(response.data.message);
				$("#modal").modal("show");
			}
		});

	}

	$scope.removeCustomerOrderDetail = function(index) {
		console.log("removeCustomerOrderDetail->fired");
		console.log("index=", index);
		$scope.cusomerOrder.customerOrderDetailDs.splice(index, 1);
		console.log("customerOrderDetailDs=",
				$scope.cusomerOrder.customerOrderDetailDs);
	}

});