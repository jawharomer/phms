$(document).ready()
{
	console.log("csrf=",csrf);
}

// Angular


editCustomerOrder = angular.module("editCustomerOrder", []);


editCustomerOrder.factory('httpRequestInterceptor', function () {
	  return {
	    request: function (config) {
	      config.headers['X-CSRF-TOKEN'] = csrf;
	      return config;
	    }
	  };
});
editCustomerOrder.config(function ($httpProvider) {
	  $httpProvider.interceptors.push('httpRequestInterceptor');
});

editCustomerOrder.controller('editCustomerOrder', function($scope, $http) {

	
	$scope.doctors;
	$scope.discountTypes;
	$scope.discountPercentage=0;
	
	
	
	$scope.cusomerOrder = {
			customerOrderId:"",
			customerName : "",
			doctorId : "",
			discountId:"",
			discountAmount:"",
			customerOrderDetailDs : [],
		};
	
   $scope.$watch('discountPercentage',function(discountPercentage){
	   $scope.cusomerOrder.discountAmount="";
		if(discountPercentage>0&&discountPercentage<=100){
			$scope.cusomerOrder.discountAmount=discountPercentage/100;
			console.log($scope.cusomerOrder.discountAmount);
		}
	});

		
	$scope.totalPrice=function(){
		var totalPrice=0;
		for(var i = 0; i < $scope.cusomerOrder.customerOrderDetailDs.length; i++){
			var quantity= $scope.cusomerOrder.customerOrderDetailDs[i].quantity;
			var price= $scope.cusomerOrder.customerOrderDetailDs[i].price;
			totalPrice+=quantity*price;
		}

        return totalPrice;
	};
	
	$scope.totalPriceWithDiscount=function(){
	return $scope.totalPrice()-($scope.totalPrice()*($scope.discountPercentage/100));
	}

	

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonDoctors=", jsonDoctors);
		console.log("jsonDiscountTypes=", jsonDiscountTypes);
		console.log("jsonCustomerOrderD=", jsonCustomerOrderD);
		
		

		$scope.doctors = JSON.parse(jsonDoctors);
		
		$scope.discountTypes = JSON.parse(jsonDiscountTypes);
		$scope.cusomerOrder=JSON.parse(jsonCustomerOrderD);

		console.log("$scope.cusomerOrder=", $scope.cusomerOrder);
		console.log("$scope.doctors=", $scope.doctors);
		console.log("$scope. $discountTypes=", $scope.discountTypes);
		
	};

	$scope.product = {
		productId : "",
		code : "",
		name : "",
		scientifiName : "",
		unitType : "",
		stockLevel : "",
		cost : "",
		profit : "",
		price : "",
		quantity : "",
		country:"",
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
								console.log("success");
								console.log("response=",response);
							
								if(response.data.stockLevel==0){
									$("#modal-body").html("Out of the stock");
									$("#modal").modal("show");
								}
								else{
									$scope.product = response.data;
								}
							}, function(response) {
								console.error("failed");
								console.error("error occured");
								$scope.content = "Something went wrong";
								$("#modal-body").html(response.data);
								$("#modal").modal("show");
							});

		}
		
	}

	$scope.addCustomerOrderDetail = function() {
		console.log("addCustomerOrderDetail->fired");

		var customerOrderDetail = {
			productId : $scope.product.productId,
			productCode : $scope.product.code,
			productName : $scope.product.name,
			scientificName : $scope.product.scientificName,
			quantity : $scope.product.quantity,
			price : $scope.product.price
		};
		$scope.cusomerOrder.customerOrderDetailDs.push(customerOrderDetail);
		console.log($scope.cusomerOrder.customerOrderDetailDs);
		$scope.product = $scope.resetProduct;
	}

	$scope.addCustomerOrder = function() {
		console.log("addCustomerOrder->fired");
		console.log("$scope.cusomerOrder=", $scope.cusomerOrder);

		$http({
			method : 'POST',
			data : $scope.cusomerOrder,
			url : $$ContextURL + '/customerOrders/add'
		}).then(function(response) {
			console.log(response);
			var outPut = `
			
			<div>${response.data.message}
			</div>
			<div>
			<a class="btn btn-info" target="_blank" href="${$$ContextURL}/customerOrders/${response.data.etc}">View</a></div>
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
				if(response.data.message){
					$("#modal-body").html(response.data.message);
					$("#modal").modal("show");
				}
				else{
					$("#modal-body").html(response.data);
					$("#modal").modal("show");
				}
				
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