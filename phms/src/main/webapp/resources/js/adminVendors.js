appAddCusotmerOrder = angular.module("adminVendors", []);

appAddCusotmerOrder.controller('adminVendors', function($scope, $http) {

	$scope.getAddingVendor = function() {
		console.log("getAddingVendor->fired");
		$http({
			method : 'GET',
			url : $$ContextURL + '/vendors/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.deleteVendor = function(id) {
		console.log("delteVendor->fired");
		$http({
			method : 'POST',
			url : $$ContextURL + '/vendors/delete/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.editVendor = function(id) {
		console.log("editVendor->fired");
		$http({
			method : 'GET',
			url : $$ContextURL + '/vendors/edit/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

});