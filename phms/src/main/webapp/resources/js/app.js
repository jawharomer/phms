var $$ContextURL = "/phms";

$.datepicker.setDefaults({
	changeMonth : true,
	changeYear : true
});

$(document).tooltip();

// Prevent Submit Form via enter
$(document).on("keypress", "form input", function(event) {
	return event.keyCode != 13;
});