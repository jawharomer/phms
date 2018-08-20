appAdminProductCategories = angular.module("adminProductCategories", []);

appAdminProductCategories.factory('httpRequestInterceptor', function () {
	  return {
	    request: function (config) {
	      config.headers['X-CSRF-TOKEN'] = csrf;
	      return config;
	    }
	  };
});
appAdminProductCategories.config(function ($httpProvider) {
	  $httpProvider.interceptors.push('httpRequestInterceptor');
});

appAdminProductCategories.controller("adminProductCategories", function($scope, $http) {

	$scope.getAddingProductCategory= function() {
		console.log("getAddingProductCategory->fired");
		$http({
			method : 'GET',
			url : $$ContextURL + '/productCategories/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.deleteProductCategory = function(id) {
		console.log("deleteProductCategory->fired");
		$http({
			method : 'POST',
			url : $$ContextURL + '/productCategories/delete/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.editProductCategory= function(id) {
		console.log("editProductCategory->fired");
		$http({
			method : 'GET',
			url : $$ContextURL + '/productCategories/edit/' + id
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

});