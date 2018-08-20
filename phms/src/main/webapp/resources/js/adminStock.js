console.log("csrf=",csrf);

$(document).ready(function() {
	console.log("Activate data table");
	$("#studentTable").DataTable({
		responsive : true
	});
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
