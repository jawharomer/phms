console.log("csrf=",csrf);

$(document).ready(function() {
	
	// S-DataTable
	$('#stockTable tfoot th:not(.cus-not-search)')
			.each(
					function() {
						var title = $(this).text();
						$(this)
								.html(
										'<input class="form-control fomt-control-sm cus-inline" type="text" />');
					});

	var table = $('#stockTable').DataTable({
		sScrollX : "98%",
		paginate : false,
		dom : 'Bfrtip',
		buttons : [ {
			extend : "excel",
			className : "btn btn-sm  btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "csv",
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "pdf",
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		} ],
		bInfo : false,
	});

	// Apply the search
	table.columns().every(function() {
		var that = this;
		console.log("that=", that);
		console.log("that.search()=", that.search());

		$('input', this.footer()).on('keyup change', function() {
			if (that.search() !== this.value) {
				that.search(this.value).draw();
			}
		});
	});
	// E-DataTable
	
	
});

function getAddProduct() {
	console.log("getAddProduct->fired");
	$.get($$ContextURL + "/products/add", function(response) {
		console.log("response=", response);
		$("#modal-body").html(response);
	});

}

function deleteProduct(_this) {
	console.log(_this);
	var id = $(_this).data("product-id");
	console.log("id=", id);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/products/delete/" + id,
		headers:{
			"X-CSRF-TOKEN":csrf
		},
		contentType : "application/json",
		success : function(data) {
			if (data == "success") {
				location.reload();
			}
		},
		error : function(request, status, error) {
			// alert(request.responseText);
			$("#modal-body").html(request.responseText);
			$("#modal").modal("show");
		}
	});
}

function editProduct(_this) {
	console.log("editProduct->fired");

	var id = $(_this).data("product-id");
	console.log("id=", id);
	$.get($$ContextURL + "/products/edit/" + id, function(response) {
		console.log("response=", response);
		$("#modal-body").html(response);
		$("#modal").modal("show");
	});

}

function productStepUp(_this) {
	console.log("productStepUp->fired");
	var id = $(_this).data("product-id");

	console.log("id=", id);
	$.get($$ContextURL + "/productStepUps/add/product/" + id,
			function(response) {
				console.log("response=", response);
				$("#modal-body").html(response);
				$("#modal").modal("show");
			});

}
