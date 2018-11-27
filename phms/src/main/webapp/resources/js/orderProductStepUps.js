console.log("orderProductStepUps-");

$(document).ready()
{
	$("#from").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", $("#from").val());

	$("#to").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", $("#to").val());

	// S-DataTable
	$('#productStepUpsTable tfoot th:not(.cus-not-search)')
			.each(
					function() {
						var title = $(this).text();
						$(this)
								.html(
										'<input class="form-control fomt-control-sm cus-inline" type="text" />');
					});

	var table = $('#productStepUpsTable').DataTable({
		paginate : false,
		dom : 'Bfrtip',
		buttons : [ {
			extend : "excel",
			messageTop : reportTitle,
			filename : reportTitle,
			className : "btn btn-sm  btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "csv",
			messageTop : reportTitle,
			filename : reportTitle,
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "pdf",
			messageTop : reportTitle,
			filename : reportTitle,
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		} ],
		bInfo : false
	});
	// E-DataTable

}

appAddCusotmerOrder = angular.module("productStepUps", []);

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

appAddCusotmerOrder.controller('productStepUps', function($scope, $http) {
	console.log("productStepUps->controller->fired");

	$scope.deleteOrderProductStepUp = function(orderId) {
		console.log("deleteOrderProductStepUp->fired");
		console.log("orderId=", orderId);

		$http.post($$ContextURL + "/orderProductStepUps/delete/" + orderId)
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
