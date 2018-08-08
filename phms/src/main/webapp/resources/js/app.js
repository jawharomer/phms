var $$ContextURL = "/phms";

// Prevent Submit Form via enter
$(document).on("keypress", "form", function(event) {
	return event.keyCode != 13;
});