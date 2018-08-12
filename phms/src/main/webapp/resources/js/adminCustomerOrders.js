$(document).ready()
{
	$("#from").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", $("#from").val());

	$("#to").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", $("#to").val());

}

appAddCusotmerOrder = angular.module("customerOrders", []);

appAddCusotmerOrder.controller('customerOrders', function($scope, $http) {
	$scope.deleteCustomerOrder = function(customerOrderId) {
		console.log("deleteCustomerOrder->fired");
		console.log("customerOrderId=", customerOrderId);

		$http.get($$ContextURL + "/customerOrders/delete/" + customerOrderId)
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
