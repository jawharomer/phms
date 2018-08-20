appAdminVendors = angular.module("adminVendors", []);

appAdminVendors.factory('httpRequestInterceptor', function () {
	  return {
	    request: function (config) {
	      config.headers['X-CSRF-TOKEN'] = csrf;
	      return config;
	    }
	  };
});

appAdminVendors.config(function ($httpProvider) {
	  $httpProvider.interceptors.push('httpRequestInterceptor');
});



appAdminVendors.controller('adminVendors', function($scope, $http) {

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
		console.log("deleteVendor->fired");
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