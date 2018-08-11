$(document).ready()
{
	$("#from").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

	$("#to").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

}

appAddCusotmerOrder = angular.module("productStepUps", []);

appAddCusotmerOrder.controller('productStepUps', function($scope, $http) {
	console.log("productStepUps->controller->fired");
	$scope.deleteProductStepUp = function(productStepUpId) {
		console.log("deleteProductStepUp->fired");
		console.log("productStepUpId=", productStepUpId);

		$http.get($$ContextURL + "/productStepUps/delete/" + productStepUpId)
				.then(function(response) {

					$("#modal-body").html(response.data);
					$("#modal").modal("show");

				}, function(response) {
					console.error("error occured");
					$("#modal-body").html(response.data);
					$("#modal").modal("show");
				});

	}
});
