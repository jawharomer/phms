appAddCusotmerOrder = angular.module("adminDoctors", []);

appAddCusotmerOrder.controller('adminDoctors', function($scope, $http) {

	$scope.getAddingDoctor = function() {
		console.log("getAddingDoctor->fired");
		$http({
			method : 'GET',
			data : $scope.cusomerOrder,
			url : $$ContextURL + '/doctors/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.deleteDoctor = function(id) {
		console.log("deleteDoctor->fired");
		$http({
			method : 'POST',
			data : $scope.cusomerOrder,
			url : $$ContextURL + '/doctors/delete/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.editDoctor = function(id) {
		console.log("editDoctor->fired");
		$http({
			method : 'GET',
			data : $scope.cusomerOrder,
			url : $$ContextURL + '/doctors/edit/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

});