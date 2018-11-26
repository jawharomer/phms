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

		console.log("jsonProducts=", jsonProducts);
		$scope.products = JSON.parse(jsonProducts);
		console.log("$scope.products=", $scope.products);

		var productAuto = [];

		angular.forEach($scope.products, function(value, key) {
			var obj = {
				label : value.name + " " + value.code,
				value : value.code,
				data : value
			}
			productAuto.push(obj);
		});

		$("#autoselect")
				.autocomplete(
						{
							source : productAuto,
							select : function(event, ui) {
								var item = ui.item.data;
								console.log("selected item =", item);
								
								$scope.product.code = item.code;
								
								$scope.$digest();
							}
						});
		
		if($scope.cusomerOrder.discountAmount){
			$scope.discountPercentage=$scope.cusomerOrder.discountAmount*100;
		}
		
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

	$scope.resetProduct =angular.copy($scope.product);

	$scope.getProduct = function(event) {
		console.log("getProduct->fired");
		if (event.which == 13) {

			console.log($scope.product.code);
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
									$( "#productName" ).focus();
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
			url : $$ContextURL + '/customerOrders/update'
		}).then(function(response) {
			console.log(response);
			history.pushState(null, '',  $$ContextURL+"/customerOrders/edit/"+response.data.etc);
			$("#freeze-div").addClass("cus-disabled-div");
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