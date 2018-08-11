$(document).ready()
{
	$("#from").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

	$("#to").datepicker({
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

}